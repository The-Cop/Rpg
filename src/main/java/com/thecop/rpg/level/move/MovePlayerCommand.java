package com.thecop.rpg.level.move;

import com.thecop.rpg.level.LevelMap;
import com.thecop.rpg.level.Point;
import com.thecop.rpg.level.Tile;
import com.thecop.rpg.userinput.MenuItem;

public final class MovePlayerCommand implements MenuItem {

    private LevelMap levelMap;
    private String controlString;
    private String description;
    private Direction direction;
    private int distance;
    private int revealRadius;

    public MovePlayerCommand(String controlString, String description, Direction direction, int distance,
            LevelMap levelMap, int revealRadius) {
        this.controlString = controlString;
        this.description = description;
        this.direction = direction;
        this.distance = distance;
        this.levelMap = levelMap;
        this.revealRadius = revealRadius;
    }

    public void move() {
        if (!canMove()) {
            System.err.println("Trying to move while can not move");
            return;
        }
        Point targetPoint = getTargetCoords();
        Point currentPoint = levelMap.getPlayerPartyPosition();
        //todo revealing is called twice - here and in setPlayerPartyPosition, think how to avoid
        while (!currentPoint.equals(targetPoint)) {
            currentPoint = nextPoint(currentPoint);
            levelMap.revealAreaAround(currentPoint, revealRadius);
        }
        levelMap.setPlayerPartyPosition(targetPoint, revealRadius);
    }

    //TODO delete?
    public boolean canMove() {
        return isPathWalkable();
    }

    private boolean isPathWalkable() {
        Point targetPoint = getTargetCoords();
        Tile targetTile = levelMap.getTileAt(targetPoint);
        //check target point. Can not move to unrevealed tile.
        if (!levelMap.isPointInBounds(targetPoint) || targetTile.isBlockingMovement() || !targetTile.isRevealed()) {
            return false;
        }
        //check all point on the way one-by-one
        Point currentPoint = levelMap.getPlayerPartyPosition();
        while (!currentPoint.equals(targetPoint)) {
            currentPoint = nextPoint(currentPoint);
            if (levelMap.getTileAt(currentPoint).isBlockingMovement()) {
                return false;
            }
        }
        return true;
    }

    private Point nextPoint(Point from) {
        return pointInDistance(from, 1);
    }

    private Point getTargetCoords() {
        Point playerPosition = levelMap.getPlayerPartyPosition();
        return pointInDistance(playerPosition, distance);
    }

    private Point pointInDistance(Point from, int dist) {
        switch (direction) {
            case NORTH:
                return new Point(from.getX(), from.getY() - dist);
            case EAST:
                return new Point(from.getX() + dist, from.getY());
            case SOUTH:
                return new Point(from.getX(), from.getY() + dist);
            case WEST:
                return new Point(from.getX() - dist, from.getY());
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

    @Override
    public void execute() {
        move();
    }
}
