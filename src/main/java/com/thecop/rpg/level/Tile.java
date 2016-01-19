package com.thecop.rpg.level;

import com.thecop.rpg.level.tiles.TileObject;

public class Tile {

    private static final char DEFAULT_DISPLAY_CHAR = ' ';
    private boolean isRevealed = false;
    private TileObject object;

    public Tile() {
    }

    public Tile(TileObject object) {
        this.object = object;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setIsRevealed(boolean isRevealed) {
        this.isRevealed = isRevealed;
    }

    public char getDisplayChar() {
        return object != null ? object.getTileChar() : DEFAULT_DISPLAY_CHAR;
    }

    public boolean isBlockingMovement() {
        return object != null && object.isBlocking();
    }

    public TileObject getObject() {
        return object;
    }

    public void setObject(TileObject object) {
        this.object = object;
    }
}
