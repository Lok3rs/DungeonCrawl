package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Monster {
    public Ghost(Cell cell) {
        super(cell);
        this.health = 20;
        this.attack = 8;
        this.armor = 7;
        this.exp = 20;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
