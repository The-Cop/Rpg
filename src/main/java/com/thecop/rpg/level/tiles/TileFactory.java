package com.thecop.rpg.level.tiles;

import com.thecop.rpg.level.Tile;
import com.thecop.rpg.level.tiles.impl.SimpleWall;

public class TileFactory {

    //TODO vertical and horizontal walls may appear later. Also rocks, trees, e.t.c.
    private static SimpleWall SIMPLE_WALL = new SimpleWall();

    public static Tile simpleWall() {
        return new Tile(SIMPLE_WALL);
    }
}
