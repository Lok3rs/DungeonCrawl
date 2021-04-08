package com.codecool.dungeoncrawl.logic.fight;

import com.codecool.dungeoncrawl.engine.gui.LogPane;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Monster;

import java.util.Locale;

public class FightService {

    public static void fight(Actor player, Monster enemy){
        int playerAtk = player.getAttack();
        int playerDef = player.getArmor();
        int playerCurrentHealth =  player.getHealth();

        int enemyAtk = enemy.getAttack();
        int enemyDef = enemy.getArmor();
        int enemyCurrentHealth = enemy.getHealth();
        int damageCaused = playerAtk - enemyDef;
        enemy.setHealth(enemyCurrentHealth - damageCaused);
        LogPane.log(String.format("You'd hit %s and caused %d damage.", enemy.getTileName(), damageCaused));
        String enemyNameCapitalized = enemy.getTileName().substring(0, 1).toUpperCase() + enemy.getTileName().substring(1);
        if (enemy.isAlive()){
            damageCaused = enemyAtk - playerDef * 2;
            player.setHealth(Math.min(playerCurrentHealth, playerCurrentHealth - damageCaused));
            LogPane.log(damageCaused > 0 ?
                    String.format("%s returns a hit with %d damage.", enemyNameCapitalized, damageCaused):
                    String.format("You're so strong! %s can't even touch you!", enemyNameCapitalized));
        } else {
            player.gainExp(enemy.getExp());
            player.checkIfEnoughExp();
            enemy.getCell().setActor(null);
            enemy.stopMoving();
            LogPane.log(String.format("%s killed. You gained %d experience.", enemyNameCapitalized, enemy.getExp()));
        }
    }


}
