package com.thecop.rpg.level.tiles;

import com.thecop.rpg.level.Tile;
import com.thecop.rpg.level.tiles.impl.SimpleWall;
import com.thecop.rpg.level.tiles.impl.TestPath;

public class TileFactory {

    //TODO vertical and horizontal walls may appear later. Also rocks, trees, e.t.c.
    //TODO may be concern a ENUM instead, if TileObject objects never change themselves
    //(Tile objects just change their reference to another TileObject)
    private static SimpleWall SIMPLE_WALL = new SimpleWall();
    private static TestPath PATH = new TestPath();

    public static Tile simpleWall() {
        return new Tile(SIMPLE_WALL);
    }
    public static Tile testPath() {
        return new Tile(PATH);
    }
    public static Tile emptyTile() {
        return new Tile();
    }
}
