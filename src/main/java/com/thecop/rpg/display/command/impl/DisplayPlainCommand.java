package com.thecop.rpg.display.command.impl;

import com.thecop.rpg.display.Display;
import com.thecop.rpg.display.command.DisplayCommand;
import com.thecop.rpg.display.data.DisplayDataGroup;
import com.thecop.rpg.display.data.impl.DisplayDataSimple;

/**
 * Created by TheCop on 15.12.2015.
 */
public class DisplayPlainCommand extends DisplayCommand {
    private Display display;
    private boolean useHalfWidth = false;
    private DisplayDataGroup group;

    public DisplayPlainCommand(DisplayDataSimple data) {
        this.group = new DisplayDataGroup(data);
    }

    public DisplayPlainCommand(DisplayDataSimple data, boolean useHalfWidth) {
        this.group = new DisplayDataGroup(data);
        this.useHalfWidth = useHalfWidth;
    }

    public DisplayPlainCommand(DisplayDataGroup group) {
        this.group = group;
    }

    public DisplayPlainCommand(DisplayDataGroup group, boolean useHalfWidth) {
        this.group = group;
        this.useHalfWidth = useHalfWidth;
    }

    @Override
    public void execute(Display display) {
        this.display = display;
        String format = getFormat();
        group.formatToColumn(getWidthForDisplayData())
                .forEach(line -> printLine(line, format));
    }

    private void printLine(String line, String format) {
        System.out.println(String.format(format, line));
    }

    private String getFormat() {
        return "| %-" + getWidthForFormat() + "s |";
    }

    private int getWidthForDisplayData() {
        int width = getWidthForFormat();
        if (useHalfWidth) {
            width = width / 2;
        }
        return width;
    }

    private int getWidthForFormat() {
        int width = display.getWidth();
        width = width - 2;//display bounds
        width = width - 2;//spaces on both sides
        return width;
    }
}
