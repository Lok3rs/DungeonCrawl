package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.Items.ItemService;
import com.codecool.dungeoncrawl.logic.Items.Key;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    ItemService itemService = new ItemService();
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();
    Button button = new Button("Pick Up!");

    Label attackLabel = new Label();
    Label armorLabel = new Label();
    Label levelLabel = new Label();
    Label expLabel = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));


        ui.add(button, 0, 20);
        button.setOnAction(actionEvent ->  {
            Item item = itemService.pickUpItem(map.getPlayer());
            if (item instanceof Key){
                map.getCell(((Key) item).getDoorX(), ((Key) item).getDoorY()).setType(CellType.OPENDOOR);
            }
        });

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
        ui.add(new Label("INVENTORY"), 0, 5);
        ui.add(inventoryLabel, 0,6);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case S:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case A:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case D:
                map.getPlayer().move(1,0);
                refresh();
            }
        }


    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (map.getPlayer().getHealth() > 0){
            for (int x = 0; x < map.getWidth(); x++) {
                for (int y = 0; y < map.getHeight(); y++) {
                    Cell cell = map.getCell(x, y);
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x, y);
                    } else if (cell.getItem() != null){
                        Tiles.drawTile(context, cell.getItem(), x, y);
                    }
                    else {
                        Tiles.drawTile(context, cell, x, y);
                    }
                }
            }
        } else {
            map.getPlayer().setHealth(0);
            context.setFont(Font.font("Franklin Gothic Heavy"));
            context.setFill(Color.RED);
            context.setTextAlign(TextAlignment.CENTER);
            context.setTextBaseline(VPos.CENTER);
            context.fillText("GAME OVER", 400, 300);
            PauseTransition delay = new PauseTransition(Duration.seconds(5));
            delay.setOnFinished(event -> {
                map.getPlayer().createPlayer();
                map = MapLoader.loadMap();
                refresh();
            });
            delay.play();
        }

        levelLabel.setText("" + map.getPlayer().getLevel());
        expLabel.setText(String.format("%s / %s", map.getPlayer().getCurrentExp(), map.getPlayer().getExpToNextLevel()));
        healthLabel.setText("" + map.getPlayer().getHealth());
        inventoryLabel.setText(" "+ itemService.inventoryToString(map.getPlayer().getInventory()));

        attackLabel.setText("" + map.getPlayer().getAttack());
        armorLabel.setText("" + map.getPlayer().getArmor());

    }
}
