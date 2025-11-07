package com.github.aoxter.civ6moddingtools.ui.controller;

import com.github.aoxter.civ6moddingtools.core.model.Leader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class LeaderCellFactory implements Callback<ListView<Leader>, ListCell<Leader>> {
    @Override
    public ListCell<Leader> call(ListView<Leader> leaderListView) {
        return new ListCell<>(){
            @Override
            public void updateItem(Leader leader, boolean empty) {
                super.updateItem(leader, empty);
                if (empty || leader == null) {
                    setText(null);
                } else {
                    setText(leader.getLeaderType());
                }
            }
        };
    }
}
