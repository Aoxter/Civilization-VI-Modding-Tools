package com.github.aoxter.civ6moddingtools.core.model;

import org.dom4j.Element;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ArtdefLeadersFallback extends Artdef {
    protected Set<LeaderArtdefElement> leaderElements;

    public ArtdefLeadersFallback(String templateName, Element versionElement, Element rootCollectionElement) {
        super(templateName, versionElement, rootCollectionElement);
    }

    @Override
    protected void loadFromXmlElement(Element rootCollectionElement) {
        this.leaderElements = new HashSet<>();
        List<Element> leadersCollectionElementsList = rootCollectionElement.elements(XmlConstants.AD_NODE_TAG_ELEMENT);
        List<Element> leaderElementElementsList = leadersCollectionElementsList.getFirst().elements(XmlConstants.AD_NODE_TAG_ELEMENT);
        for (Element leaderElementElement : leaderElementElementsList) {
            this.leaderElements.add(new LeaderArtdefElement(leaderElementElement));
        }
    }

    public Set<LeaderArtdefElement> getLeaderElements() {
        return leaderElements;
    }

    @Override
    public String toString() {
        return "[ArtdefLeadersFallback]:" + leaderElements.stream().map(LeaderArtdefElement::toString).collect(Collectors.joining( "\n"));
    }
}
