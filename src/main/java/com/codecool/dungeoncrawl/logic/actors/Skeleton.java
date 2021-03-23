package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
        this.health = 15;
        this.armor = 3;
        this.attack = 5;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
