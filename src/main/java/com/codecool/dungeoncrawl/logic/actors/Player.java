package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Player extends Actor {


    public Player(Cell cell) {
        super(cell);
        this.health = 50;
        this.attack = 10;
        this.armor = 4;
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
}
