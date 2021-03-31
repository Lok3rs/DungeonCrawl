package com.codecool.dungeoncrawl.engine.menu;

import com.codecool.dungeoncrawl.engine.eventhandlers.KeyboardEventHandler;
import com.codecool.dungeoncrawl.engine.gui.LogPane;
import com.codecool.dungeoncrawl.engine.gui.MainController;
import com.codecool.dungeoncrawl.engine.gui.RightGridPane;
import com.codecool.dungeoncrawl.engine.map.GameMap;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public abstract class Menu {

    private final ImageCursor cursor = new ImageCursor(new Image("/cursor.png"));

    protected final MainController mainController;
    protected final Canvas canvas;
    protected final Stage stage;
    protected final GraphicsContext context;
    protected RightGridPane rightGridPane;
    protected LogPane logPane;
    protected GameMap map;
    protected KeyboardEventHandler keyboardEventHandler;

    public Menu(MainController mainController){
        this.mainController = mainController;
        this.canvas = mainController.getCanvas();
        this.stage = mainController.getStage();
        this.context = canvas.getGraphicsContext2D();
        this.rightGridPane = mainController.getRightGridPane();
        this.logPane = mainController.getLogPane();
        this.map = mainController.getMap();
        this.keyboardEventHandler = mainController.getKeyboardEventHandler();
    }

    public void handleMenu(){
        beforeMenuDisplayEvents();
        Scene menuScene = drawMenu();
        menuScene.setCursor(cursor);
        stage.setScene(menuScene);
        stage.show();
    }

    protected void beforeMenuDisplayEvents(){}

    protected Scene drawMenu(){
        return new Scene(new StackPane());
    }
    protected void createTitleAndBackground(){}
    protected void createButtons(StackPane stackPane){}

    protected Button createExitButton(){
        Button exit = new javafx.scene.control.Button();
        exit.setText("Exit");
        exit.setOnMouseClicked(mouseEvent -> stage.close());
        return exit;
    }
}
