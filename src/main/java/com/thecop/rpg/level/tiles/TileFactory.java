package com.thecop.rpg.level.tiles;

import com.thecop.rpg.level.Tile;
import com.thecop.rpg.level.tiles.impl.SimpleWall;

public class TileFactory {

    //TODO vertical and horizontal walls may appear later. Also rocks, trees, e.t.c.
    //TODO may be concern a ENUM instead, if TileObject objects never change themselves
    //(Tile objects just change their reference to another TileObject)
    private static SimpleWall SIMPLE_WALL = new SimpleWall();

    public static Tile simpleWall() {
        return new Tile(SIMPLE_WALL);
    }
}
