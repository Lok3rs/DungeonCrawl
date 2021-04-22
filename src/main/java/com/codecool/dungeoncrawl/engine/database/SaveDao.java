package com.codecool.dungeoncrawl.engine.database;

import com.codecool.dungeoncrawl.logic.actors.Player;

public interface SaveDao {
    void savePlayer(Player player);

    int getLastPlayerId();

    void updatePlayer(Player player);

}
