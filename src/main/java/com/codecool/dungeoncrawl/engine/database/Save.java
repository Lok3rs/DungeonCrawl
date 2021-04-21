package com.codecool.dungeoncrawl.engine.database;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;

import java.util.List;

public class Save implements SaveDao{

    private final GameMap map;
    private final Player player;
    private final Connection conn = new Connection();

    public Save(GameMap map) {
        this.map = map;
        this.player = map.getPlayer();
    }

    @Override
    public void saveGame() {
        savePlayer();
    }

    @Override
    public void savePlayer() {
        String query = String.format("INSERT INTO players " +
                "(name, level, health, experience, attack, armor, cheat_mode, map, y_coordinate, x_coordinate)" +
                "VALUES" +
                "(%s, %d, %d, %d, %d, %d, %d, %s, %d, %d)",
                player.getName(), player.getLevel(), player.getHealth(), player.getCurrentExp(), player.getAttack(),
                player.getArmor(), player.isCheater() ? 1 : 0, map.getName(), player.getCell().getY(), player.getCell().getX());
        conn.executeQuery(query);
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
