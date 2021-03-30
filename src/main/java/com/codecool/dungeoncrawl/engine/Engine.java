package com.codecool.dungeoncrawl.engine;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.engine.gui.MainController;
import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.engine.menu.MainMenu;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;


public class Engine extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);

    public MainController mainController = new MainController(canvas, map);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        primaryStage.setScene(mainScene.createScene());
//        MainMenu menu = new MainMenu(primaryStage, mainController);
//        menu.handleMainMenu();
//        primaryStage.setTitle("Dungeon Crawl");
//        primaryStage.show();
        mainController.run(primaryStage);
    }

}
