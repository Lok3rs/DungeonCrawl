package com.codecool.dungeoncrawl.logic.Items;

import com.codecool.dungeoncrawl.logic.actors.Player;

import java.util.ArrayList;

public class ItemService {

    public void pickUpItem(Player player){
        if (player.getCell().getItem() != null){
            player.addToInventory(player.getCell().getItem());
            player.getCell().setItem(null);
        }
    }

    public String inventoryToString(ArrayList<Item> inventory){
        
    }
}
