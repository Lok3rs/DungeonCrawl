package com.codecool.dungeoncrawl.logic.Items.inventory;

import com.codecool.dungeoncrawl.logic.Items.Item;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class InventoryController {
    private final InventoryScreen inventoryScreen;
    private final Canvas inventoryCanvas;
    private ArrayList<Item> inventory;

    public InventoryController(int width,int height, ArrayList<Item> inventory) {
        this.inventoryCanvas = new Canvas(width, height);
        this.inventory = inventory;
        this.inventoryScreen = new InventoryScreen(inventory);
    }

    public void run(BorderPane mainPane) {
        mainPane.setCenter(inventoryCanvas);
        inventoryScreen.drawInventory(inventoryCanvas);
    }
}
