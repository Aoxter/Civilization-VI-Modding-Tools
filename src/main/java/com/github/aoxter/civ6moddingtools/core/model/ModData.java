package com.github.aoxter.civ6moddingtools.core.model;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModData {
    private static ModData INSTANCE;

    private File modDirectory;
    private Set<Building> buildings;
    private Set<Civilization> civilizations;
    private Set<Leader> leaders;
    private Set<Unit> units;
    private Set<Artdef> artdefs;

    private ModData() {
    }

    public static ModData getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ModData();
            INSTANCE.buildings = new HashSet<>();
            INSTANCE.civilizations = new HashSet<>();
            INSTANCE.leaders = new HashSet<>();
            INSTANCE.units = new HashSet<>();
            INSTANCE.artdefs = new HashSet<>();
        }
        return INSTANCE;
    }

    public void loadModData(File modDirectory) {
        this.modDirectory = modDirectory;
        loadDataFromDirectory(modDirectory);
        System.out.println("--------------------------------------------------");
        System.out.println("BUILDINGS");
        System.out.println("--------------------------------------------------");
        System.out.println(buildings.stream().map(Building::toString).collect(Collectors.joining( "\n")));
        System.out.println("--------------------------------------------------");
        System.out.println("CIVILIZATIONS");
        System.out.println("--------------------------------------------------");
        System.out.println(civilizations.stream().map(Civilization::toString).collect(Collectors.joining( "\n")));
        System.out.println("--------------------------------------------------");
        System.out.println("LEADERS");
        System.out.println("--------------------------------------------------");
        System.out.println(leaders.stream().map(Leader::toString).collect(Collectors.joining( "\n")));
        System.out.println("--------------------------------------------------");
        System.out.println("UNITS");
        System.out.println("--------------------------------------------------");
        System.out.println(units.stream().map(Unit::toString).collect(Collectors.joining( "\n")));
        System.out.println("--------------------------------------------------");
        System.out.println("ARTDEFS");
        System.out.println("--------------------------------------------------");
        System.out.println(artdefs.stream().map(Artdef::toString).collect(Collectors.joining( "\n")));
    }

    protected void loadDataFromDirectory(File directory) {
        File[] directoryFiles = directory.listFiles();
        if(directoryFiles != null) {
            for (File file : directoryFiles) {
                if (file.isDirectory()) {
                    loadDataFromDirectory(file);
                }
                else if(file.getName().toLowerCase().endsWith("art.xml")) {
                    //TODO temporary solution, dom4j doesn't read :: at root AssetObjects::GameArtSpecification tag
                    return;
                }
                else if (file.getName().toLowerCase().endsWith(".xml")) {
                    parseDataFile(file);
                }
                else if (file.getName().toLowerCase().endsWith(".artdef")) {
                    parseArtDefFile(file);
                }
                else if (file.getName().toLowerCase().endsWith(".xlp")) {
                    parseXlpFile(file);
                }
            }
        }


    }

    protected Element getXmlFileRootElement(File sourceXmlFile) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(sourceXmlFile);
        return document.getRootElement();
    }

    protected void parseDataFile(File sourceXmlFile) {
        try {
            Element rootElement = getXmlFileRootElement(sourceXmlFile);
            if(XmlConstants.ROOT_TAG_GAME_DATA.equals(rootElement.getName()) || XmlConstants.ROOT_TAG_GAME_INFO.equals(rootElement.getName())) {
                List<Element> typeElementsList = rootElement.elements(XmlConstants.NODE_TAG_TYPES);
                for (Element typeElement : typeElementsList) {
                    List<Element> typeRowElementsList = typeElement.elements(XmlConstants.TAG_ROW);
                    for (Element typeRowElement : typeRowElementsList) {
                        String kind = typeRowElement.attributeValue(XmlConstants.ATTRIBUTE_KIND);
                        String type = typeRowElement.attributeValue(XmlConstants.ATTRIBUTE_TYPE);
                        if (kind == null || type == null) {
                            continue;
                        }

                        if (XmlConstants.VALUE_KIND_BUILDING.equals(kind)) {
                            Building building = new Building(sourceXmlFile, type);
                            buildings.add(building);
                        } else if (XmlConstants.VALUE_KIND_CIVILIZATION.equals(kind)) {
                            Civilization civilization = new Civilization(sourceXmlFile, type);
                            civilizations.add(civilization);
                        } else if (XmlConstants.VALUE_KIND_LEADER.equals(kind)) {
                            Leader leader = new Leader(sourceXmlFile, type);
                            leaders.add(leader);
                        } else if (XmlConstants.VALUE_KIND_UNIT.equals(kind)) {
                            Unit unit = new Unit(sourceXmlFile, type);
                            units.add(unit);
                        }
                    }
                }
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    protected void parseArtDefFile(File sourceXmlFile) {
        try {
            Element rootElement = getXmlFileRootElement(sourceXmlFile);
            if(XmlConstants.ROOT_TAG_ART_DEF_SET.equals(rootElement.getName())) {
                List<Element> templateElementsList = rootElement.elements(XmlConstants.AD_NODE_TAG_TEMPLATE);
                String templateName = templateElementsList.getFirst().attributeValue(XmlConstants.ATTRIBUTE_TEXT);

                List<Element> versionElementsList = rootElement.elements(XmlConstants.AD_NODE_TAG_VERSION);
                Element versionElement = versionElementsList.getFirst();

                List<Element> collectionRootElementsList = rootElement.elements(XmlConstants.AD_NODE_TAG_COLLECTION_ROOT);
                Element collectionRootElement = collectionRootElementsList.getFirst();

                if(templateName != null && collectionRootElement != null && versionElement != null) {
                    if(artdefTemplateAlreadyExists(templateName)) {
                    }
                    else {
                        switch (templateName) {
                            case "Leaders"-> artdefs.add(new ArtdefLeaders(templateName, versionElement, collectionRootElement));
                            case "LeaderFallback" -> artdefs.add(new ArtdefLeadersFallback(templateName, versionElement, collectionRootElement));
                        }
                    }
                }
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    protected void parseXlpFile(File sourceXmlFile) {
        try {
            Element rootElement = getXmlFileRootElement(sourceXmlFile);
            if(XmlConstants.ROOT_TAG_XLP.equals(rootElement.getName())) {
            }
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean artdefTemplateAlreadyExists(String templateName) {
        for(Artdef artdef : getArtdefs()) {
            if(artdef.templateName.equals(templateName)) {
                return true;
            }
        }
        return false;
    }

    public Set<Building> getBuildings() {
        return buildings;
    }

    public Set<Civilization> getCivilizations() {
        return civilizations;
    }

    public Set<Leader> getLeaders() {
        return leaders;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public Set<Artdef> getArtdefs() {
        return artdefs;
    }

    public Artdef getArtdefByTemplate(ArtdefTemplate template) {
        return artdefs.stream().filter(artdef -> artdef.templateName.equals(template.templateName)).findFirst().orElse(null);
    }
}
