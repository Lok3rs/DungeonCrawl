package com.codecool.dungeoncrawl.engine.database;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Connection {

    Map<String, String> env = System.getenv();

    private Statement stat;

    public Connection() {
        final String DRIVER = "org.postgresql.Driver";
        final String DB_URL = String.format("jdbc:postgresql://%s/%s?user=%s&password=%s",
                env.get("PSQL_HOST"), env.get("PSQL_DBNAME"), env.get("PSQL_USERNAME"), env.get("PSQL_PASSWORD"));
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("No JDBC driver found");
            e.printStackTrace();
        }

        try {
            java.sql.Connection conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Problem with database connection.");
            e.printStackTrace();
        }
        createTables();
    }

    public void executeQuery(String query) {
        try {
            stat.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet(String query) {
        try {
            return stat.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createTables() {
        createItemsTable();
        createPlayersTable();
        createInventoryTable();
        createMonstersTable();
        createSavedMonstersTable();
    }

    private void executeCreateTableQuery(String tableName, Map<String, String> columns){
        StringBuilder columnsAsStrings = new StringBuilder();
        int counter = 1;
        for (Map.Entry<String, String> entry : columns.entrySet()){
            columnsAsStrings.append(String.format("%s %s", entry.getKey(), entry.getValue()));
            if (counter != columns.size()) columnsAsStrings.append(", ");
            counter++;
        }
        String createQuery = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", tableName, columnsAsStrings);
        executeQuery(createQuery);
    }

    private void createItemsTable(){
        String tableName = "items";
        Map<String, String> columns = new LinkedHashMap<>();
        columns.put("item_id", "serial primary key");
        columns.put("name", "VARCHAR(255)");
        executeCreateTableQuery(tableName, columns);
        insertItems();
    }

    private void createPlayersTable(){
        String tableName = "players";
        Map<String, String> columns = new LinkedHashMap<>();
        columns.put("player_id", "serial primary key");
        columns.put("name", "VARCHAR(255)");
        columns.put("level", "int");
        columns.put("health", "int");
        columns.put("experience", "int");
        columns.put("attack", "int");
        columns.put("armor", "int");
        columns.put("cheat_mode", "boolean");
        columns.put("map", "VARCHAR(255)");
        columns.put("y_coordinate", "int");
        columns.put("x_coordinate", "int");
        columns.put("save_id", "serial");
        columns.put("created_at", "TIMESTAMP NOT NULL DEFAULT NOW()");
        executeCreateTableQuery(tableName, columns);
    }

    private void createInventoryTable() {
        String tableName = "inventory";
        Map<String, String> columns = new LinkedHashMap<>();
        columns.put("player_id", "int");
        columns.put("item_id", "int");
        executeCreateTableQuery(tableName, columns);
        try{
            ResultSet constraints = getResultSet("SELECT COUNT(*) FROM pg_catalog.pg_constraint WHERE conname='item_id';");
            String alterQuery = "ALTER TABLE inventory ADD CONSTRAINT player_id FOREIGN KEY(player_id) REFERENCES players(player_id);" +
                    "ALTER TABLE inventory ADD CONSTRAINT item_id FOREIGN KEY(item_id) REFERENCES items(item_id)";
            constraints.next();
            if (constraints.getInt("count") == 0) executeQuery(alterQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void createMonstersTable(){
        String tableName = "monsters";
        Map<String, String> columns = new LinkedHashMap<>();
        columns.put("monster_id", "serial primary key");
        columns.put("name", "VARCHAR(255)");
        executeCreateTableQuery(tableName, columns);
        insertMonsters();
    }

    private void createSavedMonstersTable(){
        String tableName = "saved_monsters";
        Map<String, String> columns = new LinkedHashMap<>();
        columns.put("monster_id", "int");
        columns.put("player_id", "int");
        columns.put("health", "int");
        columns.put("map", "VARCHAR(255)");
        columns.put("y_coordinate", "int");
        columns.put("x_coordinate", "int");
        executeCreateTableQuery(tableName, columns);
        try{
            ResultSet constraints = getResultSet("SELECT COUNT(*) FROM pg_catalog.pg_constraint WHERE conname='monster_id';");
            String alterQuery = "ALTER TABLE saved_monsters ADD CONSTRAINT player_id FOREIGN KEY(player_id) REFERENCES players(player_id);" +
                    "ALTER TABLE saved_monsters ADD CONSTRAINT monster_id FOREIGN KEY(monster_id) REFERENCES monsters(monster_id)";
            constraints.next();
            if (constraints.getInt("count") == 0) executeQuery(alterQuery);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void insertMonsters(){
        List<String> monstersNames = Arrays.asList("Skeleton", "Ghost", "Ghoul", "Shaman");
        insertValuesWithName(monstersNames, "monsters");
    }

    private void insertItems(){
        List<String> itemsNames = Arrays.asList("Silver key", "Golden key", "Sword", "Potion");
        insertValuesWithName(itemsNames, "items");
    }

    private void insertValuesWithName(List<String> values, String tableName){
        ResultSet alreadyExistingValues = getResultSet(String.format("SELECT * FROM %s;", tableName));
        String insertQuery;
        try{
            if (!alreadyExistingValues.next()){
                for (String value : values) {
                    insertQuery = String.format("INSERT INTO %s (name) VALUES ('%s') ", tableName, value);
                    executeQuery(insertQuery);
                }
            }
        } catch (SQLException e){
            System.out.println("Something went wrong while inserting values!");
            e.printStackTrace();
        }
    }

}