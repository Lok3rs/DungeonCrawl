package com.codecool.dungeoncrawl.engine.menu;

import com.codecool.dungeoncrawl.engine.gui.MainController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class MainMenu extends Menu{

    private TextField textField;


    public MainMenu(MainController mainController){
        super(mainController);

    }

    @Override
    protected void createButtons(StackPane stackPane){
        Button startGame = new Button();
        startGame.setText("Start Game");
        startGame.setOnMouseClicked(mouseEvent -> startNewGame());

        Button loadGame = new Button();
        loadGame.setText("Load saved game");

        Label label1 = new Label("Name:");
        textField = new TextField ();
        HBox hb = new HBox();


        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);

        Button exit = createExitButton();

        stackPane.getChildren().addAll(canvas, startGame, loadGame, exit, hb);
        startGame.setTranslateY(-100);
        loadGame.setTranslateY(-40);
        hb.setTranslateY(240);
        hb.setTranslateX(280);

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
        stage.setScene(mainController.createScene(checkIfCheat()));
        stage.show();
    }

    private boolean checkIfCheat(){
        return textField.getText().equals("Dawid") || textField.getText().equals("Gabriela");
    }

}
