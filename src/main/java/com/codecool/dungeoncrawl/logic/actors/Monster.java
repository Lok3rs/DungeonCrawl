package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Monster extends Actor {
    public Monster(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return null;
    }
}
