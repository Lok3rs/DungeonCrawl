package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Items.Item;

import java.util.ArrayList;

public class Player extends Actor {

    private ArrayList<Item> inventory;

    public Player(Cell cell) {
        super(cell);
        this.health = 50;
        this.attack = 10;
        this.armor = 4;
        this.inventory = new ArrayList<>();
    }




    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public String getTileName() {
        return "player";
    }


    @Override
    public int getHealth() {
        return super.getHealth();
    }

    @Override
    public int getAttack() {
        return super.getAttack();
    }

    @Override
    public int getArmor() {
        return super.getArmor();
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }
}
