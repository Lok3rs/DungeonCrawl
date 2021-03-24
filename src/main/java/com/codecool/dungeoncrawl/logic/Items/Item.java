package com.codecool.dungeoncrawl.logic.Items;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private Cell cell;
    private int itemDamage = 10;
    private int itemDefense = 10;
    private String name;

    public Item(Cell cell, String name) {
        this.cell = cell;
        this.cell.setItem(this);
        this.name = name;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    public String getName(){
        return name;
    }
}
