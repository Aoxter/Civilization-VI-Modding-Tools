package com.github.aoxter.civ6moddingtools.ui.controller;

import com.github.aoxter.civ6moddingtools.ui.config.FxmlView;
import com.github.aoxter.civ6moddingtools.ui.config.StageManager;
import com.github.aoxter.civ6moddingtools.ui.event.OpenProjectEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WelcomeController implements Initializable {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final StageManager stageManager;

    @FXML
    private Button openProjectButton;

    @Lazy
    public WelcomeController(StageManager stageManager, ApplicationEventPublisher applicationEventPublisher) {
        this.stageManager = stageManager;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void openProject(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory with mod files");
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File modDirectory = directoryChooser.showDialog(openProjectButton.getScene().getWindow());
        if(modDirectory != null){
            applicationEventPublisher.publishEvent(new OpenProjectEvent(this, modDirectory));
            stageManager.switchScene(FxmlView.WORKSPACE);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
