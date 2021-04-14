package com.codecool.dungeoncrawl.engine.database;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;

import java.util.List;

public interface SaveDao {
    void saveGame();
    List<Monster> getMonsters(GameMap map);
    Player getPlayer(GameMap map);
    Integer getMapNumber();
    List<Item> getMapItems(GameMap map);
    List<Item> getInventory(Player player);
}
