package com.codecool.dungeoncrawl.engine.gui;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.engine.eventhandlers.KeyboardEventHandler;
import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.engine.menu.GameOverMenu;
import com.codecool.dungeoncrawl.engine.menu.MainMenu;
import com.codecool.dungeoncrawl.logic.Items.inventory.InventoryController;
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

public class MainController {
    private GameMap map = MapLoader.loadMap();
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
    private final InventoryController inventoryController = new InventoryController(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH,
            map.getPlayer().getInventory());
    private boolean isInventoryOn = false;


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

    public Scene createScene() {
        ImageCursor cursor = new ImageCursor(new Image("/cursor.png"));
        this.borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        this.map = MapLoader.loadMap();
        this.rightGridPane = new RightGridPane(map);
        this.logPane = new LogPane(map);
        this.keyboardEventHandler = new KeyboardEventHandler(this, map);
        rightGridPane.setGridPane(borderPane);
        logPane.setGridPane(borderPane);
        Scene scene = new Scene(borderPane);
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

}
