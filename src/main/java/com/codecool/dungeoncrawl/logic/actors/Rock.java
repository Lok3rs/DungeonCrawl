package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Rock extends Actor{

    public Rock(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "rock";
    }
}