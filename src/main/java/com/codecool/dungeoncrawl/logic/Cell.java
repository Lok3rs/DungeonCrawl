package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.actors.Actor;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private final GameMap gameMap;
    private final int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public GameMap getGameMap(){return this.gameMap;}

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public Actor getActor() {
        return actor;
    }

    public Item getItem(){
        return item;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    public List<Cell> getNeighbors(){
        List<Cell> neighbors = new ArrayList<>();
        int[] bounds = {1, -1};
        for (int bound : bounds){
            try{
                neighbors.add(gameMap.getCell(x + bound, y));
            }
            catch(ArrayIndexOutOfBoundsException ignored){}
            try{
                neighbors.add(gameMap.getCell(x, y + bound));
            }
            catch(ArrayIndexOutOfBoundsException ignored){}
        }
        return neighbors;
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    @Override
    public boolean isWalkable(){
        return type.isWalkable();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isNeighborOutOfBounds(int dx, int dy){
        return gameMap.getWidth()  <= this.y + dy &&
                gameMap.getHeight() <= this.x + dx &&
                this.x + dx >= 0 &&
                this.y + dy >= 0;
    }
}
