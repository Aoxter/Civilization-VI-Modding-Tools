package com.github.aoxter.civ6moddingtools.ui.controller;

import com.github.aoxter.civ6moddingtools.core.model.Building;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class BuildingCellFactory implements Callback<ListView<Building>, ListCell<Building>> {
    @Override
    public ListCell<Building> call(ListView<Building> buildingListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(Building building, boolean empty) {
                super.updateItem(building, empty);
                if (empty || building == null) {
                    setText(null);
                } else {
                    setText(building.getBuildingType());
                }
            }
        };
    }
}
