package com.github.aoxter.civ6moddingtools.ui.controller;

import com.github.aoxter.civ6moddingtools.core.model.Unit;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class UnitCellFactory implements Callback<ListView<Unit>, ListCell<Unit>> {
    @Override
    public ListCell<Unit> call(ListView<Unit> unitListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(Unit unit, boolean empty) {
                super.updateItem(unit, empty);
                if (empty || unit == null) {
                    setText(null);
                } else {
                    setText(unit.getUnitType());
                }
            }
        };
    }
}
