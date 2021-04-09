package com.codecool.dungeoncrawl.logic.actors.monsters;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

import java.util.Random;

public class Skeleton extends Monster {
    private final Random random = new Random();
    public Skeleton(Cell cell) {
        super(cell);
        this.health = 15;
        this.armor = 3;
        this.attack = 5;
        this.exp = 15;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move() {
        int dx = 0;
        int dy = 0;
        if (random.nextBoolean()) dx = random.nextBoolean() ? 1 : -1;
        else dy = random.nextBoolean() ? 1 : -1;
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor nextActor = nextCell.getActor();
        if (isPlayerInNeighborhood()){
            attackNeighborPlayer();
        }
        else if (nextCell.isWalkable() && nextActor == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }


}
