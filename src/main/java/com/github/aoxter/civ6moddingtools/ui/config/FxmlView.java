package com.github.aoxter.civ6moddingtools.ui.config;

public enum FxmlView {
    WELCOME {
        @Override
        public String getFxmlPath() {
            return "/fxml/welcome.fxml";
        }
    };

    public abstract  String getFxmlPath();
}
