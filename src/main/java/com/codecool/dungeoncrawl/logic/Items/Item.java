package com.codecool.dungeoncrawl.logic.Items;
import com.codecool.dungeoncrawl.logic.Cell;

public class Item {
    private Cell cell;
    private String itemName;
    private int itemDamage;
    private int itemDefense;

    public Item(Cell cell, String itemName, int itemDamage, int itemDefense) {
        this.cell = cell;
        this.itemName = itemName;
        this.itemDamage = itemDamage;
        this.itemDefense = itemDefense;
    }

    public Cell getCell(){
        return cell;
    }

    public String getItemName(){
        return itemName;
    }

    public int getItemDamage(){
        return itemDamage;
    }

    public int getItemDefense(){
        return itemDefense;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDamage(int itemDamage) {
        this.itemDamage = itemDamage;
    }

    public void setItemDefense(int itemDefense) {
        this.itemDefense = itemDefense;
    }
}
