package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }

    public boolean isWalkable(){
        return switch (this) {
            case EMPTY, FLOOR -> true;
            case WALL -> false;
        };
    }
}
