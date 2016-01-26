package com.thecop.rpg.level;

import com.thecop.rpg.level.move.Direction;
import com.thecop.rpg.level.move.MovePlayerCommand;
import com.thecop.rpg.level.tiles.TileObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelMap {

    //Tile[y][x]
    // y = number of rows
    // x = number of columns
    // e.g.
    // a11 a12 a13
    // a21 a22 a23
    private Tile[][] grid;
    private Point playerPartyPosition;
    private TileObject playerPartyPositionOriginalTileObject = null;
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

    public void revealAreaAround(Point point, int revealRadius) {
        for (int y = point.getY() - revealRadius; y <= point.getY() + revealRadius; y++) {
            for (int x = point.getX() - revealRadius; x <= point.getX() + revealRadius; x++) {
                try {
                    grid[y][x].setIsRevealed(true);
                } catch (IndexOutOfBoundsException e) {
                    //ignore
                }
            }
        }
    }

    //TODO may be get the revealRadius from Game.playerParty (?)
    public List<MovePlayerCommand> getDefaultMoveCommands(int revealRadius) {
        List<MovePlayerCommand> defaultMoveCommands = new ArrayList<>();
        defaultMoveCommands.add(new MovePlayerCommand("w", "Move north", Direction.NORTH, 1, this, revealRadius));
        defaultMoveCommands.add(new MovePlayerCommand("s", "Move south", Direction.SOUTH, 1, this, revealRadius));
        defaultMoveCommands.add(new MovePlayerCommand("a", "Move west", Direction.WEST, 1, this, revealRadius));
        defaultMoveCommands.add(new MovePlayerCommand("d", "Move east", Direction.EAST, 1, this, revealRadius));

        return defaultMoveCommands.stream().filter(c -> c.canMove()).collect(Collectors.toList());
    }

    public List<String> getObservationDescription() {
        //todo implement party's observations on a map
        List<String> observations = new ArrayList<>();
        observations.add("You see nothing special.");
        observations.add("Darkness is so heavy, you hardly can see your palms.");
        observations.add("");
        observations.add("But you aren't scared.");
        return observations;
    }

    //todo use some level-initialization game state instead, which will place player, monsters, e.t.c.
    @Deprecated
    public void placePlayerSomewhere(int revealRadius) {
        List<Tile> emptyTiles = tilesStream().filter(tile -> tile.isEmpty())
                .collect(Collectors.toList());
        Tile tile = emptyTiles.get(new Random().nextInt(emptyTiles.size()));
        setPlayerPartyPosition(tile.getCoords(), revealRadius);
    }

    private Stream<Tile> tilesStream() {
        return Arrays.stream(grid).flatMap(arr -> Arrays.stream(arr));
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
    public void setPlayerPartyPosition(Point playerPartyPosition, int revealRadius) {
        if (this.playerPartyPosition != null) {
            getTileAt(this.playerPartyPosition).setObject(playerPartyPositionOriginalTileObject);
        }
        this.playerPartyPosition = playerPartyPosition;
        //save the original tile object, to restore it when player moves away
        playerPartyPositionOriginalTileObject = getTileAt(this.playerPartyPosition).getObject();
        //TODO replace with actual party, not an anonymous class
        getTileAt(this.playerPartyPosition).setObject(new TileObject() {
            @Override
            public char getTileChar() {
                return 'X';
            }

            @Override
            public boolean isBlocking() {
                //TODO Auto-generated method stub
                return false;
            }
        });
        revealAreaAround(this.playerPartyPosition, revealRadius);
    }
}
