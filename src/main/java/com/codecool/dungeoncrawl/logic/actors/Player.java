package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.engine.gui.LogPane;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Items.Item;

import java.util.ArrayList;

public class Player extends Actor {
    private int level;
    private int currentExp;
    private int expToNextLevel;

    private ArrayList<Item> inventory;

    public Player(Cell cell) {
        super(cell);
        createPlayer();
    }

    public void createPlayer() {
        this.health = 50;
        this.attack = 10;
        this.armor = 4;
        this.inventory = new ArrayList<>();
        this.currentExp = 0;
        this.level = 1;
        this.expToNextLevel = 100;
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

    public int getLevel() {
        return level;
    }

    public void checkIfEnoughExp() {
        if (currentExp >= expToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        this.level += 1;
        this.currentExp = this.currentExp - this.expToNextLevel;
        this.expToNextLevel += 50 * level;
        this.health += 30;
        this.attack += 10;
        this.armor += 5;
        LogPane.log(String.format("Level up! Your current level is %d.", this.level));
    }

    public void gainExp(int enemyExp) {
        this.currentExp += enemyExp;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public int getExpToNextLevel() {
        return expToNextLevel;
    }

}
