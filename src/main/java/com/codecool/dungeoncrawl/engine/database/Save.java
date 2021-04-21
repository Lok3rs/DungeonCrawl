package com.codecool.dungeoncrawl.engine.database;

import com.codecool.dungeoncrawl.engine.map.GameMap;
import com.codecool.dungeoncrawl.engine.map.MapLoader;
import com.codecool.dungeoncrawl.logic.Items.Item;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.monsters.Monster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class Save implements SaveDao{

    private final GameMap map;
    private final Player player;
    private final Connection conn = new Connection();

    public Save(GameMap map) {
        this.map = map;
        this.player = map.getPlayer();
    }

    @Override
    public void saveGame() {
        savePlayer();
        saveMonsters();
        saveInventory();
    }

    @Override
    public void savePlayer() {
        String query = String.format("INSERT INTO players " +
                "(name, level, health, experience, attack, armor, cheat_mode, map, y_coordinate, x_coordinate)" +
                "VALUES" +
                "('%s', %d, %d, %d, %d, %d, %b, '%s', %d, %d)",
                player.getName(), player.getLevel(), player.getHealth(), player.getCurrentExp(), player.getAttack(),
                player.getArmor(), player.isCheater(), map.getName(), player.getCell().getY(), player.getCell().getX());
        conn.executeQuery(query);

    }

    @Override
    public void saveInventory() {

    }

    @Override
    public void saveMonsters() {
        String queryForPlayer = String.format("SELECT * FROM players WHERE name='%s'",
                player.getName());

        for (Monster monster : MapLoader.monsters) {
            ResultSet monstersSet = conn.getResultSet("SELECT * FROM monsters");
            int currentMonsterId = 0;
            try{
                while(monstersSet.next()){
                    if (monstersSet.getString("name").equalsIgnoreCase(monster.getTileName())){
                        currentMonsterId = monstersSet.getInt("monster_id");
                    }
                }
                ResultSet foundPlayer = conn.getResultSet(queryForPlayer);
                foundPlayer.next();
                String insertMonsterQuery = String.format(
                        "INSERT INTO saved_monsters (monster_id, player_id, health, map, y_coordinate, x_coordinate)" +
                                "VALUES(%d, %d, %d, '%s', %d, %d)",
                        currentMonsterId,

                        foundPlayer.getInt("player_id"),
                        monster.getHealth(),
                        map.getName(), monster.getCell().getY(), monster.getCell().getX());
                conn.executeQuery(insertMonsterQuery);
            } catch(SQLException e){
                System.out.println("Error while looping through monsterSet in Save.saveMonsters method.");
                e.printStackTrace();
            }

        }
    }

}
