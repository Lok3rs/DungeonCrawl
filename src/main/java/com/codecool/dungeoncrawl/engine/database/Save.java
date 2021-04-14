package com.codecool.dungeoncrawl.engine.database;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;

import java.util.List;

public class Save implements SaveDao{
    @Override
    public List<Monster> getMonsters(GameMap map) {
        return null;
    }

    @Override
    public Player getPlayer(GameMap map) {
        return null;
    }

    @Override
    public Integer getMapNumber() {
        return null;
    }

    @Override
    public List<Item> getMapItems(GameMap map) {
        return null;
    }

    @Override
    public List<Item> getInventory(Player player) {
        return null;
    }
}
