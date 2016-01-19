package com.thecop.rpg.level.tiles.impl;

import com.thecop.rpg.level.tiles.TileObject;

public class TestPath implements TileObject {

    //TODO delete this class

    @Override
    public char getTileChar() {
        return ' ';
    }

    @Override
    public boolean isBlocking() {
        return true;
    }
}
