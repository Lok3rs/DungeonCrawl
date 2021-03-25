package com.codecool.dungeoncrawl.logic.Items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item{
    private final int doorX;
    private final int doorY;

    public Key (Cell cell, int X, int Y, String name){
        super(cell, name);
        this.doorX = X;
        this.doorY = Y;
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