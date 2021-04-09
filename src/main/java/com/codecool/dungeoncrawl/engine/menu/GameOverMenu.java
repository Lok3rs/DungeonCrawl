package com.codecool.dungeoncrawl.engine.menu;

import com.codecool.dungeoncrawl.engine.gui.LogPane;
import com.codecool.dungeoncrawl.engine.gui.MainController;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


public class GameOverMenu extends Menu {

    public GameOverMenu(MainController mainController) {
        super(mainController);
    }


    @Override
    protected void beforeMenuDisplayEvents() {
        map.getPlayer().setHealth(0);
        createTitleAndBackground();
        for (Monster monster : MapLoader.monsters) {
            monster.stopMoving();
            monster.getCell().setActor(null);
        }
        LogPane.log("You are dead.");
        mainController.stopRefreshing();
    }

    @Override
    protected void createTitleAndBackground() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.RED);
        context.fillText("GAME OVER", 320, 200);
    }

    @Override
    protected void createButtons(StackPane stackPane) {
        Button startGame = new Button();

        startGame.setText("Play again");
        startGame.setOnMouseClicked(mouseEvent -> playAgain());

        Button exit = createExitButton();
        exit.setText("Exit");

        stackPane.getChildren().addAll(canvas, startGame, exit);
        startGame.setTranslateY(-80);
    }

    private void playAgain() {
        stage.setScene(mainController.createScene(true));
        stage.show();
        LogPane.clearLogs();
    }


}
