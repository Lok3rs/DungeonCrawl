package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.fight.FightService;

public abstract class Actor implements Drawable {
    private Cell cell;
    protected int health;
    protected int attack;
    protected int armor;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor nextActor = nextCell.getActor();
        if (nextCell.isWalkable() && nextActor == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextActor != null && Monster.class.isAssignableFrom(nextActor.getClass())) {
            FightService.fight(this, nextActor);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newValue) {this.health = newValue;}

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }


    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public boolean isAlive(){
        return this.health > 0;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
