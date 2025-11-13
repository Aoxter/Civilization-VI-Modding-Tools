package com.github.aoxter.civ6moddingtools.core.model;

import org.dom4j.Element;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LeaderArtdefElement {
    protected String name;
    protected Set<AnimationArtdefElement> animations;

    public LeaderArtdefElement(Element leaderElement) {
        loadFromXmlElement(leaderElement);
    }

    protected void loadFromXmlElement(Element leaderElement) {
        this.animations = new HashSet<>();
        List<Element> leaderChildCollectionElementsList = leaderElement.elements(XmlConstants.AD_NODE_TAG_CHILD_COLLECTION);
        List<Element> leaderChildCollectionElementElementsList = leaderChildCollectionElementsList.getFirst().elements(XmlConstants.AD_NODE_TAG_ELEMENT);
        Element firstLeaderChildCollectionElement = leaderChildCollectionElementElementsList.getFirst();
        List<Element> leaderChildCollectionNameElementsList = firstLeaderChildCollectionElement.elements(XmlConstants.AD_NODE_TAG_COLLECTION_NAME);
        if("Animations".equals(leaderChildCollectionNameElementsList.getFirst().attributeValue(XmlConstants.ATTRIBUTE_TEXT))) {
            List<Element> leaderAnimationElementsList = firstLeaderChildCollectionElement.elements(XmlConstants.AD_NODE_TAG_ELEMENT);
            for (Element leaderAnimationElement: leaderAnimationElementsList) {
                this.animations.add(new AnimationArtdefElement(leaderAnimationElement));
            }
        }
        List<Element> leaderNameElementsList = leaderElement.elements(XmlConstants.AD_NODE_TAG_NAME);
        this.name = leaderNameElementsList.getFirst().attributeValue(XmlConstants.ATTRIBUTE_TEXT);
    }

    public String getName() {
        return name;
    }

    public Set<AnimationArtdefElement> getAnimations() {
        return animations;
    }

    @Override
    public String toString() {
        return "[Leader] " + name + '\'' + animations.stream().map(AnimationArtdefElement::toString).collect(Collectors.joining( "\n"));
    }
}
