package com.codecool.dungeoncrawl.logic.fight;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Monster;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class FightService {

    public static void fight(Actor player, Actor enemy){
        int playerAtk = player.getAttack();
        int playerDef = player.getArmor();
        int playerCurrentHealth =  player.getHealth();

        int enemyAtk = enemy.getAttack();
        int enemyDef = enemy.getArmor();
        int enemyCurrentHealth = enemy.getHealth();

        enemy.setHealth(enemyCurrentHealth - (playerAtk - enemyDef));

        if (enemy.isAlive()){
            player.setHealth(playerCurrentHealth - (enemyAtk - playerDef));
        } else {
            player.gainExp(enemy.getExp());
            player.checkIfEnoughExp();
            enemy.getCell().setActor(null);
        }
    }

    private boolean isAlive(Actor actor){
        return actor.getHealth() > 0;
    }

}
