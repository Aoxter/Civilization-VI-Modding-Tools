package com.github.aoxter.civ6moddingtools.ui.controller;

import com.github.aoxter.civ6moddingtools.core.model.Civilization;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CivilizationCellFactory implements Callback<ListView<Civilization>, ListCell<Civilization>> {
    @Override
    public ListCell<Civilization> call(ListView<Civilization> civilizationListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(Civilization civilization, boolean empty) {
                super.updateItem(civilization, empty);
                if (empty || civilization == null) {
                    setText(null);
                } else {
                    setText(civilization.getCivilizationType());
                }
            }
        };
    }
}
