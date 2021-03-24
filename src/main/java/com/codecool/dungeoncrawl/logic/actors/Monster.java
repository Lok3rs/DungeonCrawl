package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

import java.util.List;

public abstract class Monster extends Actor {
    protected int exp;

    public Monster(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return null;
    }

    public int getExp(){
        return this.exp;
    }

    public void move(){}

    public boolean isPlayerInNeighborhood(){
        List<Cell> neighbors = cell.getNeighbors();
        for (Cell neighbor : neighbors){
            if (neighbor.getActor() != null && neighbor.getActor().getClass() == Player.class) return true;
        }
        return false;
    }

    public void attackNeighborPlayer(){
        List<Cell> neighbors = cell.getNeighbors();
        for (Cell neighbor : neighbors){
            if (neighbor.getActor() != null && neighbor.getActor().getClass() == Player.class){
                Actor player = neighbor.getActor();
                player.setHealth(player.getHealth() - (attack - player.getArmor()));
            }
        }
    }
}
