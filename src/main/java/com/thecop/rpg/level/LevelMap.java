package com.thecop.rpg.level;

import java.util.Map;

public class LevelMap {

    //Tile[y][x]
    // y = number of rows
    // x = number of columns
    // e.g.
    // a11 a12 a13
    // a21 a22 a23
    private Tile[][] grid;
    private Point playerPartyPosition;
    //TODO replace with actual MonsterParty class when ready
    private Map<Point, Object> monstersPartyPositionsMap;

    public LevelMap(Tile[][] grid) {
        this.grid = grid;
    }

    public boolean isPointInBounds(Point point) {
        return point.getX() >= 0
                && point.getY() >= 0
                && point.getX() <= getWidth() - 1
                && point.getY() <= getHeight() - 1;
    }

    public Tile getTileAt(Point point) {
        return grid[point.getY()][point.getX()];
    }

    public int getWidth() {
        return grid.length > 0 ? grid[0].length : 0;
    }

    public int getHeight() {
        return grid.length;
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public Point getPlayerPartyPosition() {
        return playerPartyPosition;
    }

    //TODO may be unsafe to leave public, consider make private or delete
    public void setPlayerPartyPosition(Point playerPartyPosition) {
        this.playerPartyPosition = playerPartyPosition;
    }
}
