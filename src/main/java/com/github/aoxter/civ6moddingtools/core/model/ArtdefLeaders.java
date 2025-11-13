package com.github.aoxter.civ6moddingtools.core.model;

import org.dom4j.Element;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArtdefLeaders extends Artdef{
    protected Set<String> leaders;

    public ArtdefLeaders(String templateName, Element versionElement, Element rootCollectionElement) {
        super(templateName, versionElement, rootCollectionElement);
    }

    @Override
    protected void loadFromXmlElement(Element rootCollectionElement) {
        this.leaders = new HashSet<>();
        List<Element> leadersCollectionElementsList = rootCollectionElement.elements(XmlConstants.AD_NODE_TAG_ELEMENT);
        List<Element> leaderElementElementsList = leadersCollectionElementsList.getFirst().elements(XmlConstants.AD_NODE_TAG_ELEMENT);
        for (Element leaderElementElement : leaderElementElementsList) {
            List<Element> leaderNameElementsList = leaderElementElement.elements(XmlConstants.AD_NODE_TAG_NAME);
            this.leaders.add(leaderNameElementsList.getFirst().attributeValue(XmlConstants.ATTRIBUTE_TEXT));
        }
    }

    public Set<String> getLeaders() {
        return leaders;
    }

    @Override
    public String toString() {
        return "[ArtdefLeaders]:\n" + String.join("\n", leaders);
    }
}
