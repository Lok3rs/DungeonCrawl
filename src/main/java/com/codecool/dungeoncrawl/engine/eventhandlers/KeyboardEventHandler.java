package com.codecool.dungeoncrawl.engine.eventhandlers;

import com.codecool.dungeoncrawl.engine.gui.MainScene;
import com.codecool.dungeoncrawl.engine.map.GameMap;
import javafx.scene.input.KeyEvent;

public class KeyboardEventHandler {
    private final MainScene mainScene;
    private final GameMap map;

    public KeyboardEventHandler(MainScene mainScene, GameMap map){
        this.map = map;
        this.mainScene = mainScene;
    }
    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W -> map.getPlayer().move(0, -1);
            case S -> map.getPlayer().move(0, 1);
            case A -> map.getPlayer().move(-1, 0);
            case D -> map.getPlayer().move(1, 0);
        }
        mainScene.refresh();
    }
}
