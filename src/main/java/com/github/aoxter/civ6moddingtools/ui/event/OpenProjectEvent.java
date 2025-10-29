package com.github.aoxter.civ6moddingtools.ui.event;

import org.springframework.context.ApplicationEvent;

import java.io.File;

public class OpenProjectEvent extends ApplicationEvent {
    private File modDirectory;

    public OpenProjectEvent(Object source, File modDirectory) {
        super(source);
        this.modDirectory = modDirectory;
    }

    public File getModDirectory() {
        return modDirectory;
    }
}
