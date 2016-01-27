package com.thecop.rpg.game;

import com.thecop.rpg.display.Display;
import com.thecop.rpg.game.state.GameState;
import com.thecop.rpg.level.LevelMap;

//todo check all appropriate classes to become final
public final class Game {

    private static final int DISPLAY_WIDTH = 150;

    private static Game instance = new Game();

    private Display display = new Display(DISPLAY_WIDTH);
    private GameState state;

    //todo may be move levelMap and party to some GameData class object
    private LevelMap levelMap;
    //TODO replace with actual PlayerParty class when ready
    private Object playerParty;

    private Game() {
    }

    public static Game getInstance() {
        return instance;
    }

    public void invokeCurrentSate() {
        if (state != null) {
            state.display();
        }
    }

    public Display getDisplay() {
        return display;
    }

    public LevelMap getLevelMap() {
        return levelMap;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void setLevelMap(LevelMap levelMap) {
        this.levelMap = levelMap;
    }

    //TODO get this from players party
    @Deprecated
    public int getRevealRadius() {
        return 3;
    }
}
