package com.github.aoxter.civ6moddingtools.ui.controller;

import com.github.aoxter.civ6moddingtools.core.model.AnimationArtdefElement;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class AnimationArtdefElementCellFactory implements Callback<ListView<AnimationArtdefElement>, ListCell<AnimationArtdefElement>> {
    @Override
    public ListCell<AnimationArtdefElement> call(ListView<AnimationArtdefElement> leaderArtdefElementListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(AnimationArtdefElement animationArtdefElement, boolean empty) {
                super.updateItem(animationArtdefElement, empty);
                if (empty || animationArtdefElement == null) {
                    setText(null);
                } else {
                    setText(animationArtdefElement.getName() + ": " + animationArtdefElement.getBlpEntry());
                }

            }
        };
    }
}
