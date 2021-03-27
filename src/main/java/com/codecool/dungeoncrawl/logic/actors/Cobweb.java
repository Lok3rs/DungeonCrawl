package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Cobweb extends Actor{

    public Cobweb(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "cobweb";
    }
}
