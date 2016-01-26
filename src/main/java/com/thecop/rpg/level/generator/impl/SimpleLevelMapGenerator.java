package com.thecop.rpg.level.generator.impl;

import com.thecop.rpg.level.LevelMap;
import com.thecop.rpg.level.Point;
import com.thecop.rpg.level.Tile;
import com.thecop.rpg.level.generator.LevelMapGenerator;

public class SimpleLevelMapGenerator implements LevelMapGenerator {

    private int mapWidth;
    private int mapHeight;

    public SimpleLevelMapGenerator(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    @Override
    public LevelMap generateMap() {
        Tile[][] grid = new Tile[mapHeight][mapWidth];
        fillEmptyTiles(grid);
        return new LevelMap(grid);
    }

    private static void fillEmptyTiles(Tile[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                grid[y][x] = new Tile(new Point(x, y));
            }
        }
    }
}
