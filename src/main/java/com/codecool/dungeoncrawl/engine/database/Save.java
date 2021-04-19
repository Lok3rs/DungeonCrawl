package com.codecool.dungeoncrawl.engine.database;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;

import java.util.List;

public class Save implements SaveDao{

    private final GameMap map;

    public Save(GameMap map) {
        this.map = map;
    }

    @Override
    public void saveGame() {

    }

    @Override
    public void savePlayer() {

    }

    @Override
    public void saveInventory() {

    }

    @Override
    public void saveMapWithMonsters() {

    }

    @Override
    public List<Monster> getMonsters() {
        return MapLoader.monsters;
    }

    @Override
    public String getMapName() {
        return map.getName();
    }

    @Override
    public Player getPlayer() {
        return null;
    }



    @Override
    public List<Item> getMapItems() {
        return null;
    }

    @Override
    public List<Item> getInventory() {
        return null;
    }
}
