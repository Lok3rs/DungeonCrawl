package com.codecool.dungeoncrawl.engine.database;

import java.sql.ResultSet;

public class Load implements LoadDao{
    private static final Connection conn = new Connection();

    public static ResultSet getSaves(){
        String query = "SELECT * FROM players";
        return conn.getResultSet(query);
    }

    @Override
    public void loadGame() {
        
    }

    @Override
    public ResultSet loadPlayer() {
        return null;
    }

    @Override
    public ResultSet loadMonsters() {
        return null;
    }

    @Override
    public ResultSet loadInventory() {
        return null;
    }
}