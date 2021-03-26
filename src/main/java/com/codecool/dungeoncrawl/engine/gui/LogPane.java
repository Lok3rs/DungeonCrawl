package com.codecool.dungeoncrawl.engine.gui;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.Label;


public class LogPane extends BorderGridPane{

    private static final Label logMessage1 = new Label();
    private static final Label logMessage2 = new Label();
    private static final Label logMessage3 = new Label();

    public LogPane(GameMap map) {
        super(map);
    }

    @Override
    public void setGridPane(BorderPane parentPane) {
        ui.setPrefHeight(50);
        ui.setPadding(new Insets(5));
        ui.add(logMessage3, 0, 0);
        ui.add(logMessage2, 0, 1);
        ui.add(logMessage1, 0, 2);
        parentPane.setBottom(ui);
    }

    public static void log(String message){
        if (logMessage1.getText().length() == 0){
            logMessage1.setText(message);
        }
        else if (logMessage2.getText().length() == 0){
            logMessage2.setText(logMessage1.getText());
            logMessage1.setText(message);
        }
        else {
            logMessage3.setText(logMessage2.getText());
            logMessage2.setText(logMessage1.getText());
            logMessage1.setText(message);
        }
    }

    public static void clearLogs(){
        logMessage3.setText("");
        logMessage2.setText("");
        logMessage1.setText("");
    }
}
