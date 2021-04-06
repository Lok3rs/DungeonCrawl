package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(0,28));
        tileMap.put("potion", new Tile(25, 23));
        tileMap.put("ghost", new Tile(27, 6));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("door", new Tile(11,11));
        tileMap.put("opendoor", new Tile(12,11));
        tileMap.put("ghoul", new Tile(28, 6));
        tileMap.put("cobweb", new Tile(2,15));
        tileMap.put("rock", new Tile(5,2));
        tileMap.put("spikes", new Tile(22,0));
        tileMap.put("a", new Tile(19, 30));
        tileMap.put("b", new Tile(20, 30));
        tileMap.put("c", new Tile(21, 30));
        tileMap.put("d", new Tile(22, 30));
        tileMap.put("e", new Tile(23, 30));
        tileMap.put("f", new Tile(24, 30));
        tileMap.put("g", new Tile(25, 30));
        tileMap.put("h", new Tile(26, 30));
        tileMap.put("i", new Tile(27, 30));
        tileMap.put("j", new Tile(28, 30));
        tileMap.put("k", new Tile(29, 30));
        tileMap.put("l", new Tile(30, 30));
        tileMap.put("m", new Tile(31, 30));
        tileMap.put("n", new Tile(19, 31));
        tileMap.put("o", new Tile(20, 31));
        tileMap.put("p", new Tile(21, 31));
        tileMap.put("q", new Tile(22, 31));
        tileMap.put("r", new Tile(23, 31));
        tileMap.put("s", new Tile(24, 31));
        tileMap.put("t", new Tile(25, 31));
        tileMap.put("u", new Tile(26, 31));
        tileMap.put("v", new Tile(27, 31));
        tileMap.put("w", new Tile(28, 31));
        tileMap.put("x", new Tile(29, 31));
        tileMap.put("y", new Tile(30, 31));
        tileMap.put("z", new Tile(31, 31));
        tileMap.put("0", new Tile(19, 29));
        tileMap.put("1", new Tile(20, 29));
        tileMap.put("2", new Tile(21, 29));
        tileMap.put("3", new Tile(22, 29));
        tileMap.put("4", new Tile(23, 29));
        tileMap.put("5", new Tile(24, 29));
        tileMap.put("6", new Tile(25, 29));
        tileMap.put("7", new Tile(26, 29));
        tileMap.put("8", new Tile(27, 29));
        tileMap.put("9", new Tile(28, 29));
        tileMap.put(" ", new Tile(0,0));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        drawTile(context, d, x, y, 1);
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y, int scale) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, scale*TILE_WIDTH, scale*TILE_WIDTH);
    }

    public static void drawLetter(GraphicsContext context, String name, int x, int y, int scale) {
        String lowerName = name.toLowerCase();
        Tile tile = tileMap.get(lowerName);
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, scale*TILE_WIDTH, scale*TILE_WIDTH);
    }
}
