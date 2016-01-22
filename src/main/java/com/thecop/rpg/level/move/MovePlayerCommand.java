package com.thecop.rpg.level.move;

import com.thecop.rpg.level.LevelMap;
import com.thecop.rpg.level.Point;
import com.thecop.rpg.level.Tile;
import com.thecop.rpg.userinput.MenuItem;

public class MovePlayerCommand implements MenuItem {

    private LevelMap levelMap;
    private String controlString;
    private String description;
    private Direction direction;
    private int distance;

    public MovePlayerCommand(LevelMap levelMap, String controlString, String description,
            Direction direction, int distance) {
        this.levelMap = levelMap;
        this.controlString = controlString;
        this.description = description;
        this.direction = direction;
        this.distance = distance;
    }

    public void move() {

        work here
    }

    public boolean canMove() {
        if (!isPathWalkable()) {
            return true;
        }
        return false;
    }

    private boolean isPathWalkable() {
        Point targetPoint = getTargetCoords();
        Tile targetTile = levelMap.getTileAt(targetPoint);
        if (!levelMap.isPointInBounds(targetPoint) || targetTile.isBlockingMovement() || !targetTile.isRevealed()) {
            return false;
        }

        return false;
    }

    private Point getTargetCoords() {
        Point playerPosition = levelMap.getPlayerPartyPosition();
        switch (direction) {
            case NORTH:
                return new Point(playerPosition.getX(), playerPosition.getY() - 1);
            case EAST:
                return new Point(playerPosition.getX() + 1, playerPosition.getY());
            case SOUTH:
                return new Point(playerPosition.getX(), playerPosition.getY() + 1);
            case WEST:
                return new Point(playerPosition.getX() - 1, playerPosition.getY());
            default:
                throw new IllegalStateException("Unknown direction");
        }
    }

    @Override
    public String getControlString() {
        return controlString;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
