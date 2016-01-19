package com.thecop.rpg.level.generator.impl;

import com.thecop.rpg.level.LevelMap;
import com.thecop.rpg.level.Point;
import com.thecop.rpg.level.Tile;
import com.thecop.rpg.level.generator.LevelMapGenerator;
import com.thecop.rpg.level.tiles.TileFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomLevelMapGenerator implements LevelMapGenerator {

    private int mapWidth, mapHeight;
    private static Random random = new Random();
    //TODO make these map-size-dependant
    private int roomsCount = 100;
    private int minRoomWidth = 15;
    private int maxRoomWidth = 30;
    private int minRoomHeight = 5;
    private int maxRoomHeight = 15;
    //TODO implement even width of path
    private int pathWidth = 3;

    private Tile[][] grid;

    public RoomLevelMapGenerator(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    @Override
    public LevelMap generateMap() {
        grid = new Tile[mapHeight][mapWidth];
        fillWallTiles();
        generateRooms();
        return new LevelMap(grid);
    }

    private void fillWallTiles() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = TileFactory.simpleWall();
            }
        }
    }

    private void generateRooms() {
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < roomsCount; i++) {
            Room room = createRandomRoom();

            boolean failed = false;
            for (Room otherRoom : rooms) {
                if (room.intersects(otherRoom)) {
                    failed = true;
                    break;
                }
            }
            if (!failed) {
                carveRoom(room);
                //corridors
                if (!rooms.isEmpty()) {
                    carveCorridors(room, rooms.get(rooms.size()-1));
                }
                rooms.add(room);
            }
        }
    }

    private void carveRoom(Room room) {
        for (int y = room.getTopLeft().getY(); y <= room.getBottomRight().getY(); y++) {
            for (int x = room.getTopLeft().getX(); x <= room.getBottomRight().getX(); x++) {
                grid[y][x] = TileFactory.emptyTile();
            }
        }
    }

    private void carveCorridors(Room room1, Room room2) {
        Point center1 = room1.getCenter();
        Point center2 = room2.getCenter();
        if (random.nextBoolean()) {
            carveHorizontalCorridor(center1.getX(),center2.getX(),center1.getY(),pathWidth);
            carveVerticalCorridor(center1.getY(),center2.getY(),center2.getX(),pathWidth);
        } else {
            carveVerticalCorridor(center1.getY(),center2.getY(),center1.getX(),pathWidth);
            carveHorizontalCorridor(center1.getX(),center2.getX(),center2.getY(),pathWidth);

        }
    }

    private void carveHorizontalCorridor(int fromX, int toX, int y, int thickness) {
        if (thickness <= 0) {
            throw new IllegalArgumentException("thickness must be greater than 0");
        }

        if (fromX > toX) {
            int temp = toX;
            toX = fromX;
            fromX = temp;
        }

        thickness = thickness - 1;

        int fromY = y - thickness / 2;
        int toY = y + thickness / 2;
        for (int yy = fromY; yy <= toY; yy++) {
            for (int x = fromX; x <= toX; x++) {
                grid[yy][x] = TileFactory.testPath();
            }
        }
    }

    private void carveVerticalCorridor(int fromY, int toY, int x, int thickness) {
        if (thickness <= 0) {
            throw new IllegalArgumentException("thickness must be greater than 0");
        }

        if (fromY > toY) {
            int temp = toY;
            toY = fromY;
            fromY = temp;
        }

        thickness = thickness - 1;

        int fromX = x - thickness / 2;
        int toX = x + thickness / 2;
        for (int xx = fromX; xx <= toX; xx++) {
            for (int y = fromY; y <= toY; y++) {
                grid[y][xx] = TileFactory.testPath();
            }
        }
    }

    private Room createRandomRoom() {
        int roomWidth = minRoomWidth + random.nextInt(maxRoomWidth - minRoomWidth + 1);
        int roomHeight = minRoomHeight + random.nextInt(maxRoomHeight - minRoomHeight + 1);
        //-2 and +1 ensure a 1-tile wall around the map
        int topLeftX = random.nextInt(mapWidth - roomWidth - 2) + 1;
        int topLeftY = random.nextInt(mapHeight - roomHeight - 2) + 1;
        return new Room(
                new Point(topLeftX, topLeftY),
                new Point(topLeftX + roomWidth, topLeftY + roomHeight)
        );
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
            return !(
                    //other room is far to the right
                    room.getTopLeft().getX() > this.bottomRight.getX() ||
                            //other room is far to the left
                            room.getBottomRight().getX() < this.topLeft.getX() ||
                            //other room is higher (y=0 is the highest)
                            room.getBottomRight().getY() < this.topLeft.getY() ||
                            //other room is lower (y=0 is the highest)
                            room.getTopLeft().getY() > this.bottomRight.getY()
            );

        }

        public Point getTopLeft() {
            return topLeft;
        }

        public Point getBottomRight() {
            return bottomRight;
        }

        public Point getArtificialTopRight() {
            return new Point(getBottomRight().getX(), getTopLeft().getY());
        }

        public Point getArtificialBottomLeft() {
            return new Point(getTopLeft().getX(), getBottomRight().getY());
        }

        public int getWidth() {
            return bottomRight.getX() - topLeft.getX();
        }

        public int getHeight() {
            return bottomRight.getY() - topLeft.getY();
        }

        @Override
        public String toString() {
            return "Room{" +
                    "width=" + getWidth() +
                    ", height=" + getHeight() +
                    ", topLeft=" + topLeft +
                    ", bottomRight=" + bottomRight +
                    '}';
        }
    }
}
