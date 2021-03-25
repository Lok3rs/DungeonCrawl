package com.codecool.dungeoncrawl.logic.Items;

import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;

public class ItemService {

    public Item pickUpItem(Player player){
        Item item = player.getCell().getItem();
        if (item != null){
            player.addToInventory(item);
            player.getCell().setItem(null);
        }
        return item;
    }


    public String inventoryToString(ArrayList<Item> inventory){
        ArrayList<String> inventoryItemNames = new ArrayList<>();
        for (Item item : inventory) {
            inventoryItemNames.add(item.getName());
        }
        String inventoryAsString = String.join(", ", inventoryItemNames);
        return inventoryAsString;
    }
}
