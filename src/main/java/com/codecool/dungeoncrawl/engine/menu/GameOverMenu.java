package com.codecool.dungeoncrawl.engine.menu;

import com.codecool.dungeoncrawl.engine.eventhandlers.KeyboardEventHandler;
import com.codecool.dungeoncrawl.engine.gui.LogPane;
import com.codecool.dungeoncrawl.engine.gui.MainController;
import com.codecool.dungeoncrawl.engine.gui.RightGridPane;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import javafx.animation.PauseTransition;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class GameOverMenu {

    private final MainController mainController;
    public GameOverMenu(MainController mainController){
        this.mainController = mainController;
    }

    public void handleGameOver(){
        mainController.map.getPlayer().setHealth(0);
        mainController.context.setFont(Font.font("Franklin Gothic Heavy"));
        mainController.context.setFill(Color.RED);
        mainController.context.setTextAlign(TextAlignment.CENTER);
        mainController.context.setTextBaseline(VPos.CENTER);
        mainController.context.fillText("GAME OVER", 400, 300);
        for (Monster monster : MapLoader.monsters) {
            monster.stopMoving();
            monster.getCell().setActor(null);
        }
        LogPane.log("You are dead.");
        mainController.stopRefreshing();
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> {
            mainController.map = MapLoader.loadMap();
            mainController.rightGridPane = new RightGridPane(mainController.map);
            mainController.logPane = new LogPane(mainController.map);
            mainController.keyboardEventHandler = new KeyboardEventHandler(mainController, mainController.map);
            mainController.stage.setScene(mainController.createScene());
            mainController.stage.show();
            LogPane.clearLogs();
            mainController.keepRefreshing();
        });
        delay.play();
    }
}
