package com.thecop.rpg.game.state.impl;

import com.thecop.rpg.display.Display;
import com.thecop.rpg.display.command.DisplayCommand;
import com.thecop.rpg.display.command.impl.DisplayByColumnsCommand;
import com.thecop.rpg.display.command.impl.DisplayLevelMapCommand;
import com.thecop.rpg.display.data.DisplayData;
import com.thecop.rpg.display.data.DisplayDataGroup;
import com.thecop.rpg.display.data.impl.DisplayDataSimple;
import com.thecop.rpg.display.data.impl.DisplayMenuItem;
import com.thecop.rpg.game.Game;
import com.thecop.rpg.game.state.GameState;
import com.thecop.rpg.level.LevelMap;
import com.thecop.rpg.userinput.MenuItem;
import com.thecop.rpg.userinput.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LevelWalkState implements GameState {

    private LevelMap levelMap;
    private List<MenuItem> menuItems;

    public LevelWalkState(LevelMap levelMap) {
        this.levelMap = levelMap;
    }

    @Override
    public void display() {
        initMenuItems();
        Display display = Game.getInstance().getDisplay();
        List<DisplayCommand> displayCommands = new ArrayList<>();
        displayCommands.add(new DisplayLevelMapCommand(levelMap));

        List<DisplayDataGroup> table = new ArrayList<>();
        table.add(getMenuDataGroup());
        table.add(getLevelObservationDataGroup());

        displayCommands.add(new DisplayByColumnsCommand(2, table));

        display.display(displayCommands);

        //info displayed - waiting for input
        userInput();
    }

    private void initMenuItems() {
        menuItems = levelMap.getDefaultMoveCommands(Game.getInstance().getRevealRadius()).stream()
                .collect(Collectors.toList());
        //todo may be add more commands e.g. "back", "quit", "menu", e.t.c.
    }

    private DisplayDataGroup getMenuDataGroup() {
        //todo get reveal radius from party
        List<DisplayData> displayData = new ArrayList<>();
        displayData.add(new DisplayDataSimple("Choose option:"));
        displayData.addAll(
                menuItems.stream()
                        .map(DisplayMenuItem::new)
                        .collect(Collectors.toList())
        );
        DisplayDataGroup ddg = new DisplayDataGroup(displayData);
        return ddg;
    }

    private DisplayDataGroup getLevelObservationDataGroup() {
        DisplayDataGroup ddg = new DisplayDataGroup(
                levelMap.getObservationDescription().stream()
                        .map(DisplayDataSimple::new)
                        .collect(Collectors.toList())
        );
        return ddg;
    }

    public void userInput() {
        UserInput.chooseFromMenu(menuItems).execute();
        Game.getInstance().invokeCurrentSate();
    }
}
