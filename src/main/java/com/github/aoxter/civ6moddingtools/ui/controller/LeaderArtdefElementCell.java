package com.github.aoxter.civ6moddingtools.ui.controller;

import com.github.aoxter.civ6moddingtools.core.model.AnimationArtdefElement;
import com.github.aoxter.civ6moddingtools.core.model.LeaderArtdefElement;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LeaderArtdefElementCell extends ListCell<LeaderArtdefElement> {
    private final Label header = new Label();
    private final ListView<AnimationArtdefElement> animationListView = new ListView<>();
    private final VBox container = new VBox();
    private boolean expanded = false;
    private final Label arrow = new Label("▶");

    public LeaderArtdefElementCell() {
        HBox headerBox = new HBox(arrow, header);
        headerBox.setSpacing(5);
        headerBox.setOnMouseClicked(e -> toggle());
        container.getChildren().setAll(headerBox, animationListView);
    }

    private void toggle() {
        expanded = !expanded;
        arrow.setText(expanded ? "▼" : "▶");
        animationListView.setVisible(expanded);
        animationListView.setManaged(expanded);
    }

    @Override
    protected void updateItem(LeaderArtdefElement item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
            return;
        }
        header.setText(item.getName());
        animationListView.getItems().setAll(item.getAnimations());
        animationListView.setCellFactory(new AnimationArtdefElementCellFactory());

        if (!expanded) {
            animationListView.setVisible(false);
            animationListView.setManaged(false);
        }
        setGraphic(container);
    }
}
