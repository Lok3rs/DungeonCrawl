package com.codecool.dungeoncrawl.engine;
import com.codecool.dungeoncrawl.engine.gui.MainController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Engine extends Application {

    public MainController mainController = new MainController();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainController.run(primaryStage);
    }

}
