package com.codecool.dungeoncrawl.engine.menu;

import com.codecool.dungeoncrawl.engine.gui.MainScene;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class MainMenu {

    Stage stage;
    MainScene mainScene;

    Canvas canvas = new Canvas(720, 640);
    GraphicsContext context = canvas.getGraphicsContext2D();

    public MainMenu(Stage stage, MainScene mainScene){
        this.stage = stage;
        this.mainScene = mainScene;
    }

    public void handleMainMenu(){
        stage.setScene(drawMainMenu());
        stage.show();
    }

    private Scene drawMainMenu(){
        ImageCursor cursor = new ImageCursor(new Image("/cursor.png"));
        StackPane stackPane = new StackPane();

        createTitleAndBackground();
        createButtons(stackPane);

        Scene scene = new Scene(stackPane);
        scene.setCursor(cursor);
        return scene;
    }

    private void createButtons(StackPane stackPane){
        Button startGame = new Button();

        startGame.setText("Start Game");

        startGame.setOnMouseClicked(mouseEvent -> {
            stage.hide();
            stage.setScene(mainScene.createScene());
            stage.show();
        });

        Button loadGame = new Button();
        loadGame.setText("Load saved game");

        Button exit = new Button();
        exit.setText("Exit");
        exit.relocate(10, 10);

        stackPane.getChildren().addAll(canvas, startGame, loadGame, exit);
        startGame.setTranslateY(-80);
        loadGame.setTranslateY(-40);
    }

    private void createTitleAndBackground(){
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.RED);
        context.fillText("MAIN MENU", 320, 200);
    }

}
