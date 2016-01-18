package com.thecop.rpg.level;

public class LevelMap {

    //Tile[y][x]
    // y = number of rows
    // x = number of columns
    // e.g.
    // a11 a12 a13
    // a21 a22 a23
    private Tile[][] grid;

    public LevelMap(Tile[][] grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return grid.length > 0 ? grid[0].length : 0;
    }

    public int getHeight() {
        return grid.length;
    }

    public Tile[][] getGrid() {
        return grid;
    }
}
