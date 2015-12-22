package com.thecop.rpg.display.command.impl;

import com.thecop.rpg.display.Display;
import com.thecop.rpg.display.command.DisplayCommand;
import com.thecop.rpg.display.data.DisplayDataGroup;
import com.thecop.rpg.display.data.impl.DisplayMenuItem;

/**
 * Created by TheCop on 15.12.2015.
 */
public class DisplayMenuCommand extends DisplayCommand {

    private Display display;
    private DisplayDataGroup group;

    public DisplayMenuCommand(DisplayMenuItem data) {
        this.group = new DisplayDataGroup(data);
    }

    public DisplayMenuCommand(DisplayDataGroup group) {
        this.group = group;
    }

    @Override
    public void execute(Display display) {
        this.display = display;
        String format = getFormat();
        group.formatToColumn(getWidthForFromat())
                .forEach(line -> printLine(line, format));
    }

    private void printLine(String line, String format) {
        System.out.println(String.format(format, line));
    }


    //TODO move these two methods to abstract class
    private String getFormat() {
        return "| %-" + getWidthForFromat() + "s |";
    }

    private int getWidthForFromat() {
        int width = display.getWidth();
        width = width - 2;//display bounds
        width = width - 2;//spaces on both sides
        return width;
    }
}
