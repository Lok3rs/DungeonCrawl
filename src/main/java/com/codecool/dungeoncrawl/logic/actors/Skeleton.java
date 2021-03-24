package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.fight.FightService;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

public class Skeleton extends Monster {
    private Random random = new Random();
    public Skeleton(Cell cell) {
        super(cell);
        this.health = 15;
        this.armor = 3;
        this.attack = 5;
        this.exp = 15;
        startMoving();
    }

    @Override
    public String getTileName() {
        return "skeleton";
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
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor nextActor = nextCell.getActor();
        if (nextCell.isWalkable() && nextActor == null){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextActor != null && Monster.class.isAssignableFrom(nextActor.getClass())) {
            System.out.println("PLAYER!");
        }
    }

    private void startMoving(){
        final Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds( 1 ),
                        event -> {
                            move();
                        }
                )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();
    }
}
