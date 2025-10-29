package com.github.aoxter.civ6moddingtools.ui.config;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageManager {
    private final Stage primaryStage;
    private final FxmlLoader fxmlLoader;
    private final String applicationTitle;
    private final ApplicationEventPublisher applicationEventPublisher;

    public StageManager(Stage primaryStage, FxmlLoader fxmlLoader, String applicationTitle, ApplicationEventPublisher applicationEventPublisher) {
        this.primaryStage = primaryStage;
        this.fxmlLoader = fxmlLoader;
        this.applicationTitle = applicationTitle;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void openFirstScene(final FxmlView view) {
        primaryStage.setTitle(applicationTitle);
        switchScene(view);
    }

    public void switchScene(final FxmlView view) {
        primaryStage.setMinWidth(view.getPrefWidth());
        primaryStage.setMinHeight(view.getPrefHeight());
        Parent rootNode = loadRootNode(view.getFxmlPath());
        if(primaryStage.getScene() == null) {
            primaryStage.setScene(new Scene(rootNode));
        }
        else {
            primaryStage.getScene().setRoot(rootNode);
        }
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    private Parent loadRootNode(String fxmlPath) {
        Parent rootNode;
        try {
            rootNode = fxmlLoader.load(fxmlPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rootNode;
    }

}
