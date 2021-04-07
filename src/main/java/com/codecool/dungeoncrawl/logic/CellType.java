package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    DOOR("door"),
    OPENDOOR("opendoor"),
    SPIKES("spikes"),
    ROCK("rock"),
    COBWEB("cobweb");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }

    public boolean isWalkable(){
        return switch (this) {
            case EMPTY, FLOOR, OPENDOOR, SPIKES -> true;
            case WALL, DOOR, ROCK, COBWEB -> false;
        };
    }
}
