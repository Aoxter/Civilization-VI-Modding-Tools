package com.github.aoxter.civ6moddingtools.core.model;

import org.dom4j.Element;

public abstract class Artdef {
    public final String templateName;
    protected String versionMajor;
    protected String versionMinor;
    protected String versionBuild;
    protected String versionRevision;
    protected boolean replaceMergedCollectionElements;

    public Artdef(String templateName, Element versionElement, Element rootCollectionElement) {
        this.templateName = templateName;
        this.replaceMergedCollectionElements = false;

        this.versionMajor = versionElement.elementText(XmlConstants.AD_NODE_TAG_MAJOR);
        this.versionMinor = versionElement.elementText(XmlConstants.AD_NODE_TAG_MINOR);
        this.versionBuild = versionElement.elementText(XmlConstants.AD_NODE_TAG_BUILD);
        this.versionRevision = versionElement.elementText(XmlConstants.AD_NODE_TAG_REVISION);
        loadFromXmlElement(rootCollectionElement);
    }

    protected abstract void loadFromXmlElement(Element rootCollectionElement);

}
