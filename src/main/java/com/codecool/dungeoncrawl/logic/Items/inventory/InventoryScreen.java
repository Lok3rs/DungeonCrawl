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

        context.setFill(Color.BLACK);
        context.fillRect(0, 0,
                canvas.getWidth(), canvas.getHeight());

        context.setFill(Color.web("#472D3C"));
        context.fillRect(Tiles.TILE_WIDTH, Tiles.TILE_WIDTH,
                canvas.getWidth()-2*Tiles.TILE_WIDTH, canvas.getHeight()-2*Tiles.TILE_WIDTH);

        for (int i = 0; i < inventory.size(); i++) {
            Tiles.drawTile(context, inventory.get(i), 1, i*2+1, 2);
        }
    }
}
