package com.github.aoxter.civ6moddingtools.ui.controller;

import com.github.aoxter.civ6moddingtools.core.model.ModData;
import com.github.aoxter.civ6moddingtools.core.model.Unit;
import com.github.aoxter.civ6moddingtools.ui.config.StageManager;
import com.github.aoxter.civ6moddingtools.ui.event.OpenProjectEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class WorkspaceController implements Initializable {
    private File modDirectory;
    private final StageManager stageManager;
    private ModData modData;

    @FXML
    public ListView<Unit> unitListView;
    @FXML
    private  Label testLabel;

    StringProperty modDirectoryPathProperty = new SimpleStringProperty();

    @Lazy
    public WorkspaceController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        testLabel.textProperty().bind(modDirectoryPathProperty);
        try {
            modData = ModData.getInstance();
            modData.loadModData(modDirectory);
            unitListView.getItems().addAll(modData.getUnits());
            unitListView.setCellFactory(new UnitCellFactory());
            unitListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Unit>() {
                @Override
                public void changed(ObservableValue<? extends Unit> observableValue, Unit unit, Unit t1) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventListener
    public void handleOpenProjectEvent(OpenProjectEvent event) {
        modDirectory = event.getModDirectory();
        if(modDirectory != null) {
            modDirectoryPathProperty.setValue(modDirectory.getAbsolutePath());
        }
    }
}
