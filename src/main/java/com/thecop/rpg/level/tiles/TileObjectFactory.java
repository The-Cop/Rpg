package com.thecop.rpg.level.tiles;

import com.thecop.rpg.level.tiles.impl.SimpleWall;
import com.thecop.rpg.level.tiles.impl.TestPath;

public class TileObjectFactory {

    //TODO vertical and horizontal walls may appear later. Also rocks, trees, e.t.c.
    //TODO may be concern a ENUM instead, if TileObject objects never change themselves
    //(Tile objects just change their reference to another TileObject)
    private static SimpleWall SIMPLE_WALL = new SimpleWall();
    private static TestPath PATH = new TestPath();

    public static TileObject simpleWall() {
        return SIMPLE_WALL;
    }

    public static TileObject testPath() {
        return PATH;
    }
}
