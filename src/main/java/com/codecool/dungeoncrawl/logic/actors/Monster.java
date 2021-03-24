package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.List;

public abstract class Monster extends Actor {
    protected int exp;

    public Monster(Cell cell) {
        super(cell);
        startMoving();
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

    protected void startMoving(){
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds( 1 ),
                        event -> {
                            if (isAlive()) move();
                        }
                )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();
    }

}
