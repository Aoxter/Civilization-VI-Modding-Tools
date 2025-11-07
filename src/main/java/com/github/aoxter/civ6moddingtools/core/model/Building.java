package com.github.aoxter.civ6moddingtools.core.model;

import java.io.File;

public class Building {
    protected String buildingType;
    protected final File sourceFile;

    public Building(File sourceFile, String buildingType) {
        this.sourceFile = sourceFile;
        this.buildingType = buildingType;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public String getSourceFileName() {
        return sourceFile.getName();
    }

    @Override
    public String toString() {
        return buildingType + "(" + getSourceFileName() + ")";
    }
}
