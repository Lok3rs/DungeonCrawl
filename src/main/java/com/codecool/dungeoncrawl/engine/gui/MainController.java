package com.codecool.dungeoncrawl.engine.gui;

import com.codecool.dungeoncrawl.engine.eventhandlers.KeyboardEventHandler;
import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.engine.menu.GameOverMenu;
import com.codecool.dungeoncrawl.engine.menu.MainMenu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {
    public Canvas canvas;
    public GameMap map;
    public KeyboardEventHandler keyboardEventHandler;
    public RightGridPane rightGridPane;
    public LogPane logPane;
    public GraphicsContext context;
    public Stage stage;
    final Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(500),
                    event -> refresh()));
    GameOverMenu gameOverMenu;

    public MainController(Canvas canvas, GameMap map){
        this.canvas = canvas;
        this.map = map;
        this.keyboardEventHandler = new KeyboardEventHandler(this, map);
        this.rightGridPane = new RightGridPane(map);
        this.logPane = new LogPane(map);
        this.context = canvas.getGraphicsContext2D();
        this.gameOverMenu = new GameOverMenu(this);
    }

    public void run(Stage stage){
        this.stage = stage;
        MainMenu menu = new MainMenu(this);
        menu.handleMenu();
        stage.setTitle("Dungeon Crawl");
    }

    public Canvas getCanvas(){
        return this.canvas;
    }

    public KeyboardEventHandler getKeyboardEventHandler(){
        return this.keyboardEventHandler;
    }

    public RightGridPane getRightGridPane(){
        return this.rightGridPane;
    }

    public LogPane getLogPane(){
        return this.logPane;
    }

    public GameMap getMap(){
        return this.map;
    }

    public Stage getStage(){
        return this.stage;
    }

    public Scene createScene(){
        ImageCursor cursor = new ImageCursor(new Image("/cursor.png"));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
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

    public void keepRefreshing(){
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();
    }

    public void stopRefreshing(){
        timeline.stop();
    }

}
