package com.thecop.rpg.level.tiles.impl;

import com.thecop.rpg.level.tiles.TileObject;

public class SimpleWall implements TileObject {

    @Override
    public char getTileChar() {
        return '*';
    }

    @Override
    public boolean isBlocking() {
        return true;
    }
}
