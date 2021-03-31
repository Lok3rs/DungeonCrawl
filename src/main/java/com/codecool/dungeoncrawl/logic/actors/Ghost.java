package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Ghost extends Monster {
    private final Random random = new Random();
    public Ghost(Cell cell) {
        super(cell);
        this.health = 20;
        this.attack = 50;
        this.armor = 7;
        this.exp = 20;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }

    @Override
    public void move() {
        int dx = 0;
        int dy = 0;
        if (random.nextBoolean()){
            dx = random.nextBoolean() ? 1 : -1;
        } else {
            dy = random.nextBoolean() ? 1 : -1;
        }
        if (!cell.isNeighborOutOfBounds(dx, dy)){
            Cell nextCell = cell.getNeighbor(dx, dy);
            Actor nextActor = nextCell.getActor();
            if (isPlayerInNeighborhood()){
                attackNeighborPlayer();
            }
            else if (nextActor == null){
                cell.setActor(null);
                nextCell.setActor(this);
                cell = nextCell;
            }
        }

    }
}
