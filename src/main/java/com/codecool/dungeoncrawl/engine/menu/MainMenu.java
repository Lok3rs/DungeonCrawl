package com.codecool.dungeoncrawl.engine.menu;

import com.codecool.dungeoncrawl.engine.gui.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class MainMenu extends Menu{


    public MainMenu(MainController mainController){
        super(mainController);

    }

    @Override
    protected Scene drawMenu(){
        StackPane stackPane = new StackPane();
        createTitleAndBackground();
        createButtons(stackPane);
        return new Scene(stackPane);
    }
    @Override
    protected void createButtons(StackPane stackPane){
        Button startGame = new Button();
        startGame.setText("Start Game");
        startGame.setOnMouseClicked(mouseEvent -> startNewGame());

        Button loadGame = new Button();
        loadGame.setText("Load saved game");

        Button exit = createExitButton();

        stackPane.getChildren().addAll(canvas, startGame, loadGame, exit);
        startGame.setTranslateY(-80);
        loadGame.setTranslateY(-40);
    }

    @Override
    protected void createTitleAndBackground(){
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.RED);
        context.fillText("MAIN MENU", 320, 200);
    }

    private void startNewGame(){
        stage.hide();
        stage.setScene(mainController.createScene());
        stage.show();
    }

}
