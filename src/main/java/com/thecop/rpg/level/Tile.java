package com.thecop.rpg.level;

import com.thecop.rpg.level.tiles.TileObject;

public class Tile {

    private static final char DEFAULT_DISPLAY_CHAR = ' ';
    private static final char NOT_REVEALED_CHAR = '+';

    private Point coords;
    private boolean isRevealed = false;
    private TileObject object;

    public Tile(Point coords) {
        this.coords = coords;
    }

    public Tile(Point coords, TileObject object) {
        this.coords = coords;
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
