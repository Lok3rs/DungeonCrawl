package com.codecool.dungeoncrawl.logic.Items.inventory;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.logic.Items.Item;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class InventoryScreen {
    private ArrayList<Item> inventory;
    private GraphicsContext context;
    private Canvas canvas;

    public InventoryScreen(ArrayList<Item> inventory, Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
        this.inventory = inventory;
    }

    public void drawInventory() {

        // Border
        context.setFill(Color.BLACK);
        context.fillRect(0, 0,
                canvas.getWidth(), canvas.getHeight());

        // Background
        context.setFill(Color.web("#472D3C"));
        context.fillRect(Tiles.TILE_WIDTH, Tiles.TILE_WIDTH,
                canvas.getWidth()-2*Tiles.TILE_WIDTH, canvas.getHeight()-2*Tiles.TILE_WIDTH);

        // Header
        writeString("inventory", 8 , 0, 1);

        // Items
        for (int i = 0; i < inventory.size(); i++) {
            Tiles.drawTile(context, inventory.get(i), 1, i*2+1, 2);
            writeString(inventory.get(i).getName(), 3, i*2+1, 1);
        }
    }

    private void writeString(String text, int x, int y, int scale){
        for (int i = 0; i < text.length(); i++) {
            Tiles.drawLetter(context, String.valueOf(text.charAt(i)), x+i*scale, y, scale);
        }
    }
}
