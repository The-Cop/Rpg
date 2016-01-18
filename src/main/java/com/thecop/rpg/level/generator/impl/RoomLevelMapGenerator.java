package com.thecop.rpg.level.generator.impl;

import com.thecop.rpg.level.LevelMap;
import com.thecop.rpg.level.Point;
import com.thecop.rpg.level.Tile;
import com.thecop.rpg.level.generator.LevelMapGenerator;
import com.thecop.rpg.level.tiles.TileFactory;

import java.util.ArrayList;
import java.util.List;

public class RoomLevelMapGenerator implements LevelMapGenerator {

    private int width, height;
    private int roomsCount = 4;
    private int roomsWidth = 10;
    private int roomsWidthThreshold = 5;
    private int roomsHeight = 10;
    private int roomsHeightThreshold = 5;

    public RoomLevelMapGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public LevelMap generateMap() {
        Tile[][] grid = new Tile[height][width];
        fillWallTiles(grid);
        return new LevelMap(grid);
    }

    private static void fillWallTiles(Tile[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = TileFactory.simpleWall();
            }
        }
    }

    private void generateRooms(){
        List<Room> rooms = new ArrayList<>();
        for(int i=0;i<roomsCount;i++){
            work here
        }
    }

    private static class Room {

        private Point topLeft;
        private Point bottomRight;

        public Room(Point topLeft, Point bottomRight) {
            this.topLeft = topLeft;
            this.bottomRight = bottomRight;
        }

        public Point getCenter() {
            return new Point((bottomRight.getX() + topLeft.getX()) / 2, (bottomRight.getY() + topLeft.getY()) / 2);
        }

        public boolean intersects(Room room) {
            return (topLeft.getX() <= room.getBottomRight().getX() &&
                    bottomRight.getX() >= room.getTopLeft().getX() &&
                    topLeft.getY() <= room.getBottomRight().getY() &&
                    bottomRight.getY() >= room.getTopLeft().getY());
        }

        public Point getTopLeft() {
            return topLeft;
        }

        public Point getBottomRight() {
            return bottomRight;
        }
    }
}
