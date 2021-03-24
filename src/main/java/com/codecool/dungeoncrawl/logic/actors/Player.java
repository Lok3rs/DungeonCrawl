package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {
    private int level;
    private int currentExp;
    private int expToNextLevel;


    public Player(Cell cell) {
        super(cell);
        createPlayer();
    }

    public void createPlayer(){
        this.health = 50;
        this.attack = 10;
        this.armor = 4;
        this.currentExp = 0;
        this.level = 1;
        this.expToNextLevel = 100;
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

    public int getLevel() {
        return level;
    }

    public void checkIfEnoughExp(){
        if (currentExp >= expToNextLevel){
            levelUp();
        }
    }

    private void levelUp() {
        this.level+= 1;
        this.currentExp = this.currentExp - this.expToNextLevel;
        this.expToNextLevel += 50 * level;
        this.health += 30;
        this.attack += 10;
        this.armor += 5;
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
