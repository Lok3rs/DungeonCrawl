package com.codecool.dungeoncrawl.engine.database;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.engine.eventhandlers.KeyboardEventHandler;
import com.codecool.dungeoncrawl.engine.gui.LogPane;
import com.codecool.dungeoncrawl.engine.gui.MainController;
import com.codecool.dungeoncrawl.engine.gui.RightGridPane;
import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.logic.Items.inventory.InventoryController;
import com.codecool.dungeoncrawl.logic.actors.Player;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Load implements LoadDao{
    private GameMap map = MapLoader.loadMap(false, "/map.txt", "random");
    private final Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);

    private static final Connection conn = new Connection();
    private final Integer playerId;
    private final Stage stage;
    private final MainController mainController;

    public Load(Integer playerId, Stage stage, MainController mainController){
        this.playerId = playerId;
        this.stage = stage;
        this.mainController = mainController;
    }

    public static ResultSet getSaves(){
        String query = "SELECT * FROM players";
        return conn.getResultSet(query);
    }

    @Override
    public Scene loadGame() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        Scene scene = null;
        ResultSet loadedMonsters = loadMonsters();


        try {
            ResultSet loadedPlayer = loadPlayer();

            loadedPlayer.next();
            String mapName = loadedPlayer.getString("map");

            String playerName = loadedPlayer.getString("name");
            boolean cheatMode = loadedPlayer.getBoolean("cheat_mode");
            scene = mainController.createScene(cheatMode, playerName);
            MapLoader.monsters.clear();
            map = MapLoader.loadMap(cheatMode, mapName, playerName);

            Player player = map.getPlayer();

            while(player.getLevel() < loadedPlayer.getInt("level")){
                player.levelUp();
            }

            player.setHealth(loadedPlayer.getInt("health"));
            player.setCurrentExp(loadedPlayer.getInt("experience"));
            player.setAttack(loadedPlayer.getInt("attack"));
            player.getCell().setActor(null);
            map.getCell(loadedPlayer.getInt("x_coordinate"), loadedPlayer.getInt("y_coordinate"))
                    .setActor(player);
        } catch (SQLException e){
            e.printStackTrace();
        }


        return scene;
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
