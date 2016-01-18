package com.thecop.rpg.level.generator.impl;

import com.thecop.rpg.level.LevelMap;
import com.thecop.rpg.level.Tile;
import com.thecop.rpg.level.generator.LevelMapGenerator;

public class SimpleLevelMapGenerator implements LevelMapGenerator {

    @Override
    public LevelMap generateMap(int width, int height) {
        Tile[][] grid = new Tile[height][width];
        fillEmptyTiles(grid);
        return new LevelMap(grid);
    }

    private static void fillEmptyTiles(Tile[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Tile();
            }
        }
    }
}
