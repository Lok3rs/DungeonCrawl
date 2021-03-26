package com.codecool.dungeoncrawl.engine.gui;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

abstract class BorderGridPane {

    protected GameMap map;
    protected GridPane ui = new GridPane();

    public BorderGridPane(GameMap map){
        this.map = map;
    }
    public void setGridPane(BorderPane parentPane){}
}
