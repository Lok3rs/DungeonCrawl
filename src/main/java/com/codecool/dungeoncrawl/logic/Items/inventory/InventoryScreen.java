package com.codecool.dungeoncrawl.logic.Items.inventory;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.logic.Items.Item;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class InventoryScreen {
    private ArrayList<Item> inventory;

    public InventoryScreen(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void drawInventory(Canvas canvas) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.AQUAMARINE);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        Tiles.drawBigTile(context, "potion", 1, 5);
//        Tiles.drawBigTile(context, "sword", 1, 3);
        for (int i = 0; i < inventory.size(); i++) {
            Tiles.drawTile(context, inventory.get(i), 1, i*2+2, 2);
        }
//        Tiles.drawTile(context, brzeszczot, 1, 7, 2);
    }
}
