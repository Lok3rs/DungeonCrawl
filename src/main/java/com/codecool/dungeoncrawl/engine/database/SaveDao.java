package com.codecool.dungeoncrawl.engine.database;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;

import java.util.List;

public interface SaveDao {
    void saveGame();
    void savePlayer();
    void saveInventory();
    void saveMonsters();
    List<Monster> getMonsters();
    Player getPlayer();
    String getMapName();
    List<Item> getMapItems();
    List<Item> getInventory();
}
