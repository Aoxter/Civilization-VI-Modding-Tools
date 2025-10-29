package com.github.aoxter.civ6moddingtools.ui.config;

public enum FxmlView {
    WELCOME {
        @Override
        public String getFxmlPath() {
            return "/fxml/Welcome.fxml";
        }

        @Override
        public Double getPrefWidth() {
            return 600.0;
        }

        @Override
        public Double getPrefHeight() {
            return 400.0;
        }
    },

    WORKSPACE {
        @Override
        public String getFxmlPath() {
            return "/fxml/Workspace.fxml";
        }

        @Override
        public Double getPrefWidth() {
            return 1920.0;
        }

        @Override
        public Double getPrefHeight() {
            return 1080.0;
        }
    };

    public abstract String getFxmlPath();
    public abstract Double getPrefWidth();
    public abstract Double getPrefHeight();

}
