package com.codecool.dungeoncrawl.engine.gui;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.Items.ItemService;
import com.codecool.dungeoncrawl.logic.Items.Key;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class RightGridPane extends BorderGridPane {
    ItemService itemService = new ItemService();
    Button button = new Button("Pick Up!");
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();
    Label attackLabel = new Label();
    Label armorLabel = new Label();
    Label levelLabel = new Label();
    Label expLabel = new Label();


    public RightGridPane(GameMap map) {
        super(map);
    }

    @Override
    public void setGridPane(BorderPane parentPane) {
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        addButtons();
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

    private void addButtons() {
        ui.getChildren().remove(button);
        ui.add(button, 0, 20);
        button.setOnAction(actionEvent -> {
            Item item = itemService.pickUpItem(map.getPlayer());

            if (item instanceof Key) {
                map.getCell(((Key) item).getDoorX(), ((Key) item).getDoorY()).setType(CellType.OPENDOOR);
            }
        });
    }

    private void addLabels() {
        
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
        ui.add(new Label("Inventory: "), 0, 5);
        ui.add(inventoryLabel, 0, 6);
    }


}
