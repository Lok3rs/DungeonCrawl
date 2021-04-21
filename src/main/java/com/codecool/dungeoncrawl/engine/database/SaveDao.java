package com.codecool.dungeoncrawl.engine.database;


public interface SaveDao {
    void saveGame();
    void savePlayer();
    void saveInventory();
    void saveMonsters();
}
