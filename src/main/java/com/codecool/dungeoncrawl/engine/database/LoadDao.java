package com.codecool.dungeoncrawl.engine.database;

import java.sql.ResultSet;

public interface LoadDao {
    void loadGame();
    ResultSet loadPlayer();
    ResultSet loadMonsters();
    ResultSet loadInventory();
}
