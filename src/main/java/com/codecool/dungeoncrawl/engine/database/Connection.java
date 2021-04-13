package com.codecool.dungeoncrawl.engine.database;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

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
        columns.put("id", "serial primary key");
        columns.put("name", "VARCHAR(255)");
        executeCreateTableQuery(tableName, columns);
    }

}