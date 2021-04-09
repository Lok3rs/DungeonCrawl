package com.codecool.dungeoncrawl.engine.eventhandlers;

import com.codecool.dungeoncrawl.engine.gui.MainController;
import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.logic.Items.inventory.InventoryController;
import javafx.scene.input.KeyEvent;

public class KeyboardEventHandler {
    private final MainController mainController;

    private  GameMap map;


    public KeyboardEventHandler(MainController mainController, GameMap map){
        this.map = map;
        this.mainController = mainController;
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W -> map.getPlayer().move(0, -1);
            case S -> map.getPlayer().move(0, 1);
            case A -> map.getPlayer().move(-1, 0);
            case D -> map.getPlayer().move(1, 0);
            case I -> mainController.switchInventory();
        }
        mainController.refresh();
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

}
