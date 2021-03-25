package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.Items.Key;
import com.codecool.dungeoncrawl.logic.Items.Potion;
import com.codecool.dungeoncrawl.logic.Items.Sword;
import com.codecool.dungeoncrawl.logic.actors.Ghost;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

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
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            new Ghost(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case '%':
                            cell.setType(CellType.FLOOR);
                            new Potion(cell);
                            break;
                        case '&':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case '!':
                            cell.setType(CellType.FLOOR);
                            Key key = new Key(cell);
                            keys.add(key);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
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
