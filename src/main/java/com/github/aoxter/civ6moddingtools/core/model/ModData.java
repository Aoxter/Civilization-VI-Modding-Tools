package com.github.aoxter.civ6moddingtools.core.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ModData {
    private static ModData INSTANCE;

    private File modDirectory;
    private Set<Unit> units;

    private ModData() {
    }

    public static ModData getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ModData();
            INSTANCE.units = new HashSet<>();
        }
        return INSTANCE;
    }

    public void loadModData(File modDirectory) {
        this.modDirectory = modDirectory;
        loadDataFromDirectory(modDirectory);
        System.out.println(units.stream().map(Unit::toString).collect(Collectors.joining( "\n")));
    }

    protected void loadDataFromDirectory(File directory) {
        File[] directoryFiles = directory.listFiles();
        if(directoryFiles != null) {
            for (File file : directoryFiles) {
                if (file.isDirectory()) {
                    loadDataFromDirectory(file);
                }
                else if (file.getName().endsWith(".xml") || file.getName().endsWith(".XML")) {
                    loadDataFromXmlFile(file);
                }
            }
        }
    }

    protected void loadDataFromXmlFile(File xmlFile) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            Element rootElement = document.getDocumentElement();
            if(XmlStrings.ROOT_TAG_GAME_DATA.equals(rootElement.getNodeName()) || XmlStrings.ROOT_TAG_GAME_INFO.equals(rootElement.getNodeName())) {
                parseDataFile(xmlFile, rootElement);
            }
            else if(XmlStrings.ROOT_TAG_ART_DEF_SET.equals(rootElement.getNodeName())) {
                parseArtDefFile(rootElement);
            }
            else if(XmlStrings.ROOT_TAG_XLP.equals(rootElement.getNodeName())) {
                parseXlpFile(rootElement);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void parseDataFile(File sourceXmlFile, Element rootElement) {
        NodeList typesNodeList = rootElement.getElementsByTagName(XmlStrings.NODE_TAG_TYPES);
        for(int typesPos=0; typesPos < typesNodeList.getLength(); typesPos++) {
            Node typeNode = typesNodeList.item(typesPos);
            if(Node.ELEMENT_NODE == typeNode.getNodeType()) {
                Element typeElement = (Element) typeNode;
                NodeList typeRowsNodesList = typeElement.getElementsByTagName(XmlStrings.ROW_TAG);
                for(int rowsPos=0; rowsPos < typeRowsNodesList.getLength(); rowsPos++) {
                    Node rowNode = typeRowsNodesList.item(rowsPos);
                    if(Node.ELEMENT_NODE == rowNode.getNodeType()) {
                        Element rowElement = (Element) rowNode;
                        String kind = rowElement.getAttribute(XmlStrings.ATTRIBUTE_KIND);
                        if(kind == null || kind.isEmpty()) {
                            continue;
                        }
                        if(XmlStrings.VALUE_KIND_UNIT.equals(kind)) {
                            String type = rowElement.getAttribute(XmlStrings.ATTRIBUTE_TYPE);
                            if(type != null || !type.isEmpty()) {
                                Unit unit = new Unit(sourceXmlFile, type);
                                units.add(unit);
                            }
                        }
                    }
                }
            }
        }
    }

    protected void parseArtDefFile(Element rootElement) {
        NodeList nodeList = rootElement.getChildNodes();
        for(int i=0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

        }
    }

    protected void parseXlpFile(Element rootElement) {
        NodeList nodeList = rootElement.getChildNodes();
        for(int i=0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

        }
    }

    public Set<Unit> getUnits() {
        return units;
    }
}
