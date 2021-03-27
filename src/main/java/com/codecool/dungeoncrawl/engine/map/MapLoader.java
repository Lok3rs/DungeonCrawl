package com.codecool.dungeoncrawl.engine.map;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Items.Key;
import com.codecool.dungeoncrawl.logic.Items.Potion;
import com.codecool.dungeoncrawl.logic.Items.Sword;
import com.codecool.dungeoncrawl.logic.actors.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        ArrayList<Key> keys = new ArrayList<>();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ' -> cell.setType(CellType.EMPTY);
                        case '#' -> cell.setType(CellType.WALL);
                        case '.' -> cell.setType(CellType.FLOOR);
                        case 's' -> {
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                        }
                        case 'g' -> {
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                        }
                        case 'G' -> {
                            cell.setType(CellType.FLOOR);
                            new Ghoul(cell);
                        }
                        case '@' -> {
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                        }
                        case '%' -> {
                            cell.setType(CellType.FLOOR);
                            new Potion(cell);
                        }
                        case '&' -> {
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                        }
                        case '!' -> {
                            cell.setType(CellType.FLOOR);
                            Key keyOne = new Key(cell, 15, 3, "Gold Key");
                            keys.add(keyOne);
                        }
                        case '=' -> {
                            cell.setType(CellType.FLOOR);
                            Key keyTwo = new Key(cell, 20, 9, "Silver Key");
                            keys.add(keyTwo);
                        }
                        case '?' -> {
                            cell.setType(CellType.FLOOR);
                            new Cobweb(cell);
                        }
                        default -> throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");

                    }
                }
            }
        }
        for (Key key : keys) {
            map.getCell(key.getDoorX(), key.getDoorY()).setType(CellType.DOOR);
        }
        return map;
    }

}
