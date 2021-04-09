package com.codecool.dungeoncrawl.logic.actors.monsters;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.actors.Actor;

import java.util.Random;

public class Shaman extends Monster{
    private final Random random = new Random();
    public Shaman(Cell cell) {
        super(cell);
        this.health = 40;
        this.attack = 20;
        this.armor = 8;
        this.exp = 70;
    }

    @Override
    public String getTileName() {
        return "shaman";
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
        else if (random.nextInt(100) < 20){
            teleport();
        }
        else if (nextCell.isWalkable() && nextActor == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    private void teleport(){
        GameMap map = cell.getGameMap();
        Cell target = map.getCell(random.nextInt(map.getHeight()), random.nextInt(map.getWidth()));
        while (!target.isWalkable()){
            target = map.getCell(random.nextInt(map.getHeight()), random.nextInt(map.getWidth()));
        }
        cell.setActor(null);
        target.setActor(this);
        cell = target;
    }
}
