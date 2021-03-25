package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

import java.util.Random;

public class Ghoul extends Monster {
    private final Random random = new Random();

    public Ghoul(Cell cell) {
        super(cell);
        this.health = 30;
        this.attack = 11;
        this.armor = 9;
        this.exp = 30;
    }

    @Override
    public void move() {
        Player enemy = cell.getGameMap().getPlayer();
        Cell enemyCell = enemy.getCell();
        int enemyPositionX = enemyCell.getX();
        int enemyPositionY = enemyCell.getY();
        int distanceX = Math.abs(enemyPositionX - this.cell.getX());
        int distanceY = Math.abs(enemyPositionY - this.cell.getY());
        int dx = 0;
        int dy = 0;
        if (Math.max(distanceY, distanceX) < 7) {
            if (distanceX < distanceY && enemyPositionY < this.cell.getY())dy = -1;
            else if (distanceX < distanceY) dy = 1;
            else if (distanceY < distanceX && enemyPositionX < this.cell.getX()) dx = -1;
            else if (distanceX == distanceY && enemyPositionX < this.cell.getX()){
                if (cell.getNeighbor(-1, 0).isWalkable())dx = -1;
                else dy = cell.getNeighbor(0, -1).isWalkable() ? -1 : 1; }
            else if (distanceX == distanceY && enemyPositionX > this.cell.getX()){
                if (cell.getNeighbor(1, 0).isWalkable()) dx = 1;
                else dy = cell.getNeighbor(0, -1).isWalkable() ? -1 : 1; }
            else if (distanceY == distanceX && enemyPositionY < this.cell.getY()) dy = -1;
            else dy = 1;
        } else {
            if (random.nextBoolean()) dx = random.nextBoolean() ? 1 : -1;
            else dy = random.nextBoolean() ? 1 : -1;
        }
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor nextActor = nextCell.getActor();
        if (isPlayerInNeighborhood()) {
            attackNeighborPlayer();
        } else if (nextCell.isWalkable() && nextActor == null) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
            System.out.println(getDistanceFromEnemy());
        }
    }

    @Override
    public String getTileName() {
        return "ghoul";
    }

    private int getDistanceFromEnemy() {
        Player enemy = cell.getGameMap().getPlayer();
        Cell enemyCell = enemy.getCell();
        int distanceX = Math.abs(enemyCell.getX() - this.cell.getX());
        int distanceY = Math.abs(enemyCell.getY() - this.cell.getY());
        return Math.max(distanceY, distanceX);
    }
}
