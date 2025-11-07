package com.github.aoxter.civ6moddingtools.core.model;

import java.io.File;

public class Leader {
    protected String leaderType;
    protected final File sourceFile;

    public Leader(File sourceFile, String leaderType) {
        this.sourceFile = sourceFile;
        this.leaderType = leaderType;
    }

    public String getLeaderType() {
        return leaderType;
    }

    public String getSourceFileName() {
        return sourceFile.getName();
    }

    @Override
    public String toString() {
        return leaderType + "(" + getSourceFileName() + ")";
    }
}
