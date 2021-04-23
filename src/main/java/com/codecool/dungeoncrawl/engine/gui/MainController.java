package com.codecool.dungeoncrawl.engine.gui;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.engine.database.Load;
import com.codecool.dungeoncrawl.engine.eventhandlers.KeyboardEventHandler;
import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.engine.menu.GameOverMenu;
import com.codecool.dungeoncrawl.engine.menu.MainMenu;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Items.inventory.InventoryController;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    private GameMap map = MapLoader.loadMap(false, "/map.txt", "random");
    private final Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    private KeyboardEventHandler keyboardEventHandler;
    private RightGridPane rightGridPane;
    private LogPane logPane;
    private final GraphicsContext context = canvas.getGraphicsContext2D();
    private Stage stage;
    private final Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(500),
                    event -> refresh()));
    private GameOverMenu gameOverMenu;
    private BorderPane borderPane;
    private InventoryController inventoryController;
    private boolean isInventoryOn = false;
    private boolean cheatMode = false;


    public void run(Stage stage) {
        this.stage = stage;
        MainMenu menu = new MainMenu(this);
        this.gameOverMenu = new GameOverMenu(this);
        menu.handleMenu();
        stage.setTitle("Dungeon Crawl");
    }

    public KeyboardEventHandler getKeyboardEventHandler() {
        return this.keyboardEventHandler;
    }

    public RightGridPane getRightGridPane() {
        return this.rightGridPane;
    }

    public LogPane getLogPane() {
        return this.logPane;
    }


    public Stage getStage() {
        return this.stage;
    }

    public Scene createScene(boolean cheatMode, String playerName) {
        this.cheatMode = cheatMode;
        ImageCursor cursor = new ImageCursor(new Image("/cursor.png"));
        this.borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        this.map = MapLoader.loadMap(cheatMode, "/map.txt", playerName);
        return getScene(cursor);
    }

    private Scene getScene(ImageCursor cursor) {
        this.rightGridPane = new RightGridPane(map, stage);
        this.logPane = new LogPane(map);
        this.keyboardEventHandler = new KeyboardEventHandler(this, map);
        rightGridPane.setGridPane(borderPane);
        logPane.setGridPane(borderPane);
        Scene scene = new Scene(borderPane);
        this.inventoryController = new InventoryController(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH,
                map.getPlayer().getInventory());
        scene.setCursor(cursor);
        scene.setOnKeyPressed(keyboardEventHandler::onKeyPressed);
        keepRefreshing();
        return scene;
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (map.getPlayer().getHealth() > 0) map.refreshMap(context);
        else {
            gameOverMenu.handleMenu();
        }
        if(map.getPlayer().getCell().getCellType() == CellType.STAIRWAY){
            this.map = MapLoader.loadMap(this.cheatMode, "/map2.txt", map.getPlayer().getName());
            this.keyboardEventHandler.setMap(this.map);
        }
        rightGridPane.refreshLabels();
    }

    public void keepRefreshing() {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void stopRefreshing() {
        timeline.stop();
    }

    public GraphicsContext getContext() {
        return context;
    }

    public GameMap getMap() {
        return map;
    }

    public BorderPane getMainPane() {
        return borderPane;
    }

    public Canvas getCanvas() {
        return canvas;
    }


    public void switchInventory() {
        if (isInventoryOn) {
            isInventoryOn = false;
            getMainPane().setCenter(canvas);
        } else {
            isInventoryOn = true;
            inventoryController.run(borderPane);
        }
    }

    public Scene loadGame(Load load){
        String mapName;
        String playerName;
        int loadedPlayerLevel = 0;
        int loadedPlayerHealth = 0;
        int loadedPlayerExperience = 0;
        int loadedPlayerAttack = 0;
        int loadedPlayerArmor = 0;
        int loadedPlayerYCord = 0;
        int loadedPlayerXCord = 0;

        try{
            ResultSet loadedPlayer = load.loadPlayer();
            loadedPlayer.next();
            mapName = loadedPlayer.getString("map");
            playerName = loadedPlayer.getString("name");
            loadedPlayerLevel = loadedPlayer.getInt("level");
            loadedPlayerHealth = loadedPlayer.getInt("health");
            loadedPlayerExperience = loadedPlayer.getInt("experience");
            loadedPlayerAttack = loadedPlayer.getInt("attack");
            loadedPlayerArmor = loadedPlayer.getInt("armor");
            loadedPlayerYCord = loadedPlayer.getInt("y_coordinate");
            loadedPlayerXCord = loadedPlayer.getInt("x_coordinate");
            this.cheatMode = loadedPlayer.getBoolean("cheat_mode");
            this.map = MapLoader.loadMap(cheatMode, mapName, playerName);

        } catch(SQLException e){
            System.out.println("Something very bad happened");
            e.printStackTrace();
        }
        for (Monster monster : MapLoader.monsters) {
            monster.getCell().setActor(null);
        }
        MapLoader.monsters.clear();
        Player player = map.getPlayer();

        while(player.getLevel() < loadedPlayerLevel){
            player.levelUp();
        }

        player.setHealth(loadedPlayerHealth);
        player.setCurrentExp(loadedPlayerExperience);
        player.setAttack(loadedPlayerAttack);
        player.setArmor(loadedPlayerArmor);
        player.getCell().setActor(null);
        map.getCell(loadedPlayerXCord, loadedPlayerYCord)
                .setActor(player);
        ImageCursor cursor = new ImageCursor(new Image("/cursor.png"));
        this.borderPane = new BorderPane();
        borderPane.setCenter(canvas);

        return getScene(cursor);
    }

}
