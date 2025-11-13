package com.github.aoxter.civ6moddingtools.core.model;

import org.dom4j.Element;

import java.util.List;

public class AnimationArtdefElement {
    protected String name;
    protected String blpEntry;

    public AnimationArtdefElement(Element animationElement) {
        loadFromXmlElement(animationElement);
    }

    protected void loadFromXmlElement(Element leaderElement) {
        List<Element> nameElementsList = leaderElement.elements(XmlConstants.AD_NODE_TAG_NAME);
        this.name = nameElementsList.getFirst().attributeValue(XmlConstants.ATTRIBUTE_TEXT);

        List<Element> fieldElementsList = leaderElement.elements(XmlConstants.AD_NODE_TAG_FIELDS);
        List<Element> valueElementsList = fieldElementsList.getFirst().elements(XmlConstants.AD_NODE_TAG_VALUES);
        List<Element> valueElementElementsList = valueElementsList.getFirst().elements(XmlConstants.AD_NODE_TAG_ELEMENT);
        Element firstValueElementElement = valueElementElementsList.getFirst();
        if("AssetObjects..BLPEntryValue".equals(firstValueElementElement.attributeValue(XmlConstants.ATTRIBUTE_CLASS))) {
            this.blpEntry = firstValueElementElement.element(XmlConstants.AD_NODE_TAG_ENTRY_NAME).attributeValue(XmlConstants.ATTRIBUTE_TEXT);
        }
    }

    public String getBlpEntry() {
        return blpEntry;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[Animation] " + name + " -> " + blpEntry;
    }
}
