package com.github.aoxter.civ6moddingtools.core.model;

import java.io.File;

public class Unit {
    protected String unitType;
    protected final File sourceFile;

    public Unit(File sourceFile, String unitType) {
        this.sourceFile = sourceFile;
        this.unitType = unitType;
    }

    public String getUnitType() {
        return unitType;
    }

    public String getSourceFileName() {
        return sourceFile.getName();
    }
}
