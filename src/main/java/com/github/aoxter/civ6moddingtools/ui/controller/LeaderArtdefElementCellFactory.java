package com.github.aoxter.civ6moddingtools.ui.controller;

import com.github.aoxter.civ6moddingtools.core.model.LeaderArtdefElement;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class LeaderArtdefElementCellFactory implements Callback<ListView<LeaderArtdefElement>, ListCell<LeaderArtdefElement>>  {
    @Override
    public ListCell<LeaderArtdefElement> call(ListView<LeaderArtdefElement> leaderArtdefElementListView) {
        return new LeaderArtdefElementCell();
    }
}
