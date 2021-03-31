package com.codecool.dungeoncrawl.engine.gui;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.engine.Engine;
import com.codecool.dungeoncrawl.engine.eventhandlers.KeyboardEventHandler;
import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.engine.menu.MainMenu;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.VPos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {
    Canvas canvas;
    GameMap map;
    KeyboardEventHandler keyboardEventHandler;
    RightGridPane rightGridPane;
    LogPane logPane;
    GraphicsContext context;
    Stage stage;

    public MainController(Canvas canvas, GameMap map){
        this.canvas = canvas;
        this.map = map;
        this.keyboardEventHandler = new KeyboardEventHandler(this, map);
        this.rightGridPane = new RightGridPane(map);
        this.logPane = new LogPane(map);
        this.context = canvas.getGraphicsContext2D();
    }

    public void run(Stage stage){
        this.stage = stage;
        MainMenu menu = new MainMenu(stage, this);
        menu.handleMainMenu();
        stage.setTitle("Dungeon Crawl");
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
//        keepRefreshing();
        return scene;
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (map.getPlayer().getHealth() > 0) map.refreshMap(context);
        else handleGameOver();
        rightGridPane.refreshLabels();
    }

    private void keepRefreshing(){
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(500),
                        event -> refresh()));
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();
    }

    private void handleGameOver(){
        map.getPlayer().setHealth(0);
        context.setFont(Font.font("Franklin Gothic Heavy"));
        context.setFill(Color.RED);
        context.setTextAlign(TextAlignment.CENTER);
        context.setTextBaseline(VPos.CENTER);
        context.fillText("GAME OVER", 400, 300);
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> {
            map = MapLoader.loadMap();
            this.rightGridPane = new RightGridPane(map);
            this.logPane = new LogPane(map);
            keyboardEventHandler = new KeyboardEventHandler(this, map);
            stage.setScene(createScene());
            stage.show();
            refresh();

        });
        delay.play();
    }
}
