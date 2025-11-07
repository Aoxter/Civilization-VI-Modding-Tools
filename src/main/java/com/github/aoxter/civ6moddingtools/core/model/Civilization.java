package com.github.aoxter.civ6moddingtools.core.model;

import java.io.File;

public class Civilization {
    protected String civilizationType;
    protected final File sourceFile;

    public Civilization(File sourceFile, String civilizationType) {
        this.sourceFile = sourceFile;
        this.civilizationType = civilizationType;
    }

    public String getCivilizationType() {
        return civilizationType;
    }

    public String getSourceFileName() {
        return sourceFile.getName();
    }

    @Override
    public String toString() {
        return civilizationType + "(" + getSourceFileName() + ")";
    }
}
