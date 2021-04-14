package com.codecool.dungeoncrawl.engine.gui;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.Items.ItemService;
import com.codecool.dungeoncrawl.logic.Items.Key;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class RightGridPane extends BorderGridPane {
    ItemService itemService = new ItemService();
    Button button = new Button("Pick Up!");
    Button pause = new Button("Pause");
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();
    Label attackLabel = new Label();
    Label armorLabel = new Label();
    Label levelLabel = new Label();
    Label expLabel = new Label();
    private final Stage primaryStage;


    public RightGridPane(GameMap map, Stage primaryStage) {
        super(map);
        this.primaryStage = primaryStage;
    }

    @Override
    public void setGridPane(BorderPane parentPane) {
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        addButtons(parentPane);
        addLabels();
        refreshLabels();
        parentPane.setRight(ui);
    }

    public void refreshLabels(){
        levelLabel.setText("" + map.getPlayer().getLevel());
        expLabel.setText(String.format("%s / %s", map.getPlayer().getCurrentExp(), map.getPlayer().getExpToNextLevel()));
        healthLabel.setText("" + map.getPlayer().getHealth());
        inventoryLabel.setText(" "+ itemService.inventoryToString(map.getPlayer().getInventory()));
        attackLabel.setText("" + map.getPlayer().getAttack());
        armorLabel.setText("" + map.getPlayer().getArmor());
    }

    private void addButtons(BorderPane parentPane) {
        ui.getChildren().remove(button);
        ui.add(button, 0, 20);
        button.setOnAction(actionEvent -> {
            Item item = itemService.pickUpItem(map.getPlayer());

            if (item instanceof Key) {
                map.getCell(((Key) item).getDoorX(), ((Key) item).getDoorY()).setType(CellType.OPENDOOR);
            }
        });
        preparePauseButton(parentPane);
        ui.add(pause, 0, 21);
        pause.setTranslateY(10);
    }

    private void preparePauseButton(BorderPane parentPane){
        Rectangle rect = new Rectangle(50, 50, 50, 50);
        rect.setFill(Color.CORAL);

        TranslateTransition animation = createAnimation(rect);

        Pane pane = new Pane(rect);
        pane.setMinSize(600, 150);

        pause.setOnAction(e -> {
            animation.pause();
            parentPane.setEffect(new GaussianBlur());

            VBox pauseRoot = new VBox(5);
            pauseRoot.getChildren().add(new Label("Paused"));
            pauseRoot.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
            pauseRoot.setAlignment(Pos.CENTER);
            pauseRoot.setPadding(new Insets(20));

            Button resume = new Button("Resume");
            pauseRoot.getChildren().add(resume);

            Stage popupStage = new Stage(StageStyle.TRANSPARENT);
            popupStage.initOwner(primaryStage);
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));


            resume.setOnAction(event -> {
                parentPane.setEffect(null);
                animation.play();
                popupStage.hide();
            });

            popupStage.show();
        });

    }

    private TranslateTransition createAnimation(Rectangle rect) {
        TranslateTransition animation = new TranslateTransition(Duration.seconds(1), rect);
        animation.setByX(400);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setAutoReverse(true);
        animation.play();
        return animation;
    }


    private void addLabels() {
        ui.getChildren().removeAll(levelLabel, expLabel, healthLabel, attackLabel, armorLabel, inventoryLabel);
        ui.add(new Label("Level: "), 0, 0);
        ui.add(levelLabel, 1, 0);
        ui.add(new Label("Exp: "), 0, 1);
        ui.add(expLabel, 1, 1);
        ui.add(new Label("Health: "), 0, 2);
        ui.add(healthLabel, 1, 2);
        ui.add(new Label("Attack: "), 0, 3);
        ui.add(attackLabel, 1, 3);
        ui.add(new Label("Armor: "), 0, 4);
        ui.add(armorLabel, 1, 4);
    }


}
