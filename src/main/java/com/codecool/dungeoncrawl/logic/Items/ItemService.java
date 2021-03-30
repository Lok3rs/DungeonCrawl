package com.codecool.dungeoncrawl.logic.Items;

import com.codecool.dungeoncrawl.engine.gui.LogPane;
import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;

public class ItemService {

    public Item pickUpItem(Player player){
        Item item = player.getCell().getItem();
        if (item != null){
            player.addToInventory(item);
            LogPane.log(String.format("You picked up %s.", item.getName()));
            player.getCell().setItem(null);
            if (item.getName().equals("Sword")){
                player.setAttack(player.getAttack() + 3);
            }
        }
        return item;
    }


    public String inventoryToString(ArrayList<Item> inventory){
        ArrayList<String> inventoryItemNames = new ArrayList<>();
        for (Item item : inventory) {
            inventoryItemNames.add(item.getName());
        }
        return String.join("\n ", inventoryItemNames);
    }
}
