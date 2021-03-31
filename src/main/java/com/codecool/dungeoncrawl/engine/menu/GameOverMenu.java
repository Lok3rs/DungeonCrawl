package com.codecool.dungeoncrawl.engine.menu;

import com.codecool.dungeoncrawl.engine.eventhandlers.KeyboardEventHandler;
import com.codecool.dungeoncrawl.engine.gui.LogPane;
import com.codecool.dungeoncrawl.engine.gui.MainController;
import com.codecool.dungeoncrawl.engine.gui.RightGridPane;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import javafx.animation.PauseTransition;
import javafx.geometry.VPos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class GameOverMenu {

    private final MainController mainController;
    public GameOverMenu(MainController mainController){
        this.mainController = mainController;
    }

    public void handleMenu(){
        mainController.map.getPlayer().setHealth(0);
        createTitleAndBackground();
        for (Monster monster : MapLoader.monsters) {
            monster.stopMoving();
            monster.getCell().setActor(null);
        }
        LogPane.log("You are dead.");
        mainController.stopRefreshing();
        mainController.stage.setScene(drawMenu());
    }

    private Scene drawMenu(){
        ImageCursor cursor = new ImageCursor(new Image("/cursor.png"));
        StackPane stackPane = new StackPane();

        createTitleAndBackground();
        createButtons(stackPane);

        Scene scene = new Scene(stackPane);
        scene.setCursor(cursor);
        return scene;
    }

    private void createTitleAndBackground(){
        mainController.context.setFill(Color.BLACK);
        mainController.context.fillRect(0, 0, mainController.canvas.getWidth(), mainController.canvas.getHeight());
        mainController.context.setFill(Color.RED);
        mainController.context.fillText("GAME OVER", 320, 200);
    }

    private void createButtons(StackPane stackPane){
        Button startGame = new Button();

        startGame.setText("Play again");
        startGame.setOnMouseClicked(mouseEvent -> {
            mainController.map = MapLoader.loadMap();
            mainController.rightGridPane = new RightGridPane(mainController.map);
            mainController.logPane = new LogPane(mainController.map);
            mainController.keyboardEventHandler = new KeyboardEventHandler(mainController, mainController.map);
            mainController.stage.setScene(mainController.createScene());
            mainController.stage.show();
            LogPane.clearLogs();
            mainController.keepRefreshing();
        });


        Button exit = new Button();
        exit.setText("Exit");
        exit.relocate(10, 10);
        exit.setOnMouseClicked(mouseEvent -> mainController.stage.close());

        stackPane.getChildren().addAll(mainController.canvas, startGame, exit);
        startGame.setTranslateY(-80);
    }
}
