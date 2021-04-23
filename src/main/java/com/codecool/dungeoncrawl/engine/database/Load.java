package com.codecool.dungeoncrawl.engine.database;

import javax.xml.transform.Result;
import java.sql.ResultSet;

public class Load implements LoadDao{
    private static final Connection conn = new Connection();
    private final Integer playerId;

    public Load(Integer playerId){
        this.playerId = playerId;
    }

    public static ResultSet getSaves(){
        String query = "SELECT * FROM players";
        return conn.getResultSet(query);
    }

    @Override
    public void loadGame() {
        ResultSet loadedPlayer = loadPlayer();
        ResultSet loadedMonsters = loadMonsters();
    }

    @Override
    public ResultSet loadPlayer() {
        String query = String.format("SELECT * FROM players WHERE player_id=%d", this.playerId);
        return conn.getResultSet(query);
    }

    @Override
    public ResultSet loadMonsters() {
        String query = String.format("SELECT * FROM saved_monsters WHERE player_id=%d", this.playerId);
        return conn.getResultSet(query);
    }

    @Override
    public ResultSet loadInventory() {
        return null;
    }
}
