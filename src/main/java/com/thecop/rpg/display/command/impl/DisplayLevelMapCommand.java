package com.thecop.rpg.display.command.impl;

import com.thecop.rpg.display.Display;
import com.thecop.rpg.display.command.DisplayCommand;
import com.thecop.rpg.display.data.DisplayDataGroup;
import com.thecop.rpg.display.data.impl.DisplayLevelMap;
import com.thecop.rpg.level.LevelMap;

/**
 * Created by TheCop on 15.12.2015.
 */
public class DisplayLevelMapCommand extends DisplayCommand {

    private Display display;
    private DisplayDataGroup group;

    public DisplayLevelMapCommand(LevelMap levelMap) {
        this.group = new DisplayDataGroup(new DisplayLevelMap(levelMap));
    }

    @Override
    public void execute(Display display) {
        this.display = display;
        String format = getFormat();
        group.formatToColumn(getWidthForFormat())
                .forEach(line -> printLine(line, format));
    }

    private void printLine(String line, String format) {
        System.out.println(String.format(format, line));
    }

    private String getFormat() {
        return "| %-" + getWidthForFormat() + "s |";
    }

    //TODO move to abstract class?
    private int getWidthForFormat() {
        int width = display.getWidth();
        width = width - 2;//display bounds
        width = width - 2;//spaces on both sides
        return width;
    }
}
