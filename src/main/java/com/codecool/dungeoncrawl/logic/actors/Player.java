package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Items.Item;

import java.util.ArrayList;

public class Player extends Actor {
    private ArrayList<Item> inventory;

    public Player(Cell cell) {
        super(cell);
        inventory = new ArrayList<>();
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }

    public String getTileName() {
        return "player";
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }
}
