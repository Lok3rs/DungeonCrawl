package com.codecool.dungeoncrawl.engine.database;

import javafx.scene.Scene;

import java.sql.ResultSet;

public interface LoadDao {
    Scene loadGame();
    ResultSet loadPlayer();
    ResultSet loadMonsters();
    ResultSet loadInventory();
}
