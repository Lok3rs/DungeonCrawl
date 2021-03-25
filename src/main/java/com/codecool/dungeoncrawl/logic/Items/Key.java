package com.codecool.dungeoncrawl.logic.Items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item{
    private final int doorX;
    private final int doorY;

    public Key (Cell cell){
        super(cell, "Key");
        this.doorX = 15;
        this.doorY = 3;
    }

    public int getDoorX() {
        return doorX;
    }

    public int getDoorY() {
        return doorY;
    }

    @Override
    public String getTileName() {
        return "key";
    }
}