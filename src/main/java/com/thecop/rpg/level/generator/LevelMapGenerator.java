package com.thecop.rpg.level.generator;

import com.thecop.rpg.level.LevelMap;
import com.thecop.rpg.level.Tile;

public class LevelMapGenerator {
    //TODO implement different generators
    // http://gamedevelopment.tutsplus.com/tutorials/create-a-procedurally-generated-dungeon-cave-system--gamedev-10099
    // http://gamedevelopment.tutsplus.com/tutorials/cave-levels-cellular-automata--gamedev-9664

    public static final int MAP_WIDTH = 200;
    public static final int MAP_HEIGHT = 30;

    public static LevelMap generateMap(){
        Tile[][] grid = new Tile[MAP_HEIGHT][MAP_WIDTH];
        fillEmptyTiles(grid);
        return new LevelMap(grid);
    }
    private static void fillEmptyTiles(Tile[][] grid){
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                grid[i][j] = new Tile();
            }
        }
    }
}
