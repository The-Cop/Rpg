package com.thecop.rpg.display.command.impl;

import com.thecop.rpg.display.Display;
import com.thecop.rpg.display.command.DisplayCommand;
import com.thecop.rpg.display.data.DisplayDataGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TheCop on 15.12.2015.
 */
public class DisplayByColumnsCommand extends DisplayCommand {

    //TODO think about refactor and/or commenting code
    private List<DisplayDataGroup> groups;
    private int columns;
    private Display display;

    public DisplayByColumnsCommand(int columns, List<DisplayDataGroup> groups) {
        this.columns = columns;
        this.groups = groups;
    }

    @Override
    public void execute(Display display) {
        this.display = display;
        int columnWidth = getEffectiveColumnWidth();
        int lastColumnWidth = columnWidth + getLastColumnRemainder();
        printRowsOfGroups(getRowsOfGroups(), columnWidth, lastColumnWidth);
    }

    private void printRowsOfGroups(List<List<DisplayDataGroup>> rowsOfGroups, int columnWidth, int lastColumnWidth) {
        Iterator<List<DisplayDataGroup>> iterator = rowsOfGroups.iterator();
        while (iterator.hasNext()) {
            List<DisplayDataGroup> rowOfGroups = iterator.next();
            printRowOfGroups(rowOfGroups, columnWidth, lastColumnWidth);
            if (iterator.hasNext()) {
                display.printLine();
            }
        }
        //        rowsOfGroups.stream().forEach(rowOfGroups -> printRowOfGroups(rowOfGroups, columnWidth,
        // lastColumnWidth));
    }

    private void printRowOfGroups(List<DisplayDataGroup> rowOfGroups, int columnWidth, int lastColumnWidth) {
        List<FormattedGroup> formattedGroupsRow = formatGroups(rowOfGroups, columnWidth, lastColumnWidth);
        String format = createFormat(columnWidth, lastColumnWidth);
        printRowOfFormattedGroups(formattedGroupsRow, format);
    }

    private void printRowOfFormattedGroups(List<FormattedGroup> formattedGroupsRow, String format) {
        int lineIndex = 0;
        while (hasLineToPrint(formattedGroupsRow, lineIndex)) {
            printSingleLine(formattedGroupsRow, lineIndex, format);
            lineIndex++;
        }
    }

    private void printSingleLine(List<FormattedGroup> formattedGroupsRow, int lineIndex, String format) {
        List<String> singleLinesFromEachGroup = formattedGroupsRow.stream()
                .map(fgr -> fgr.getLine(lineIndex))
                .collect(Collectors.toList());
        System.out.println(String.format(format, singleLinesFromEachGroup.toArray()));
    }

    private String createFormat(int columnWidth, int lastColumnWidth) {
        StringBuilder sb = new StringBuilder("| ");
        for (int i = 0; i < columns - 1; i++) {
            sb.append("%-").append(columnWidth).append("s").append(" | ");
        }
        sb.append("%-").append(lastColumnWidth).append("s").append(" |");
        return sb.toString();
    }

    private List<FormattedGroup> formatGroups(List<DisplayDataGroup> rowOfGroups, int columnWidth,
            int lastColumnWidth) {
        List<FormattedGroup> formattedGroupsRow = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            int width = i == columns - 1 ? lastColumnWidth : columnWidth;
            if (i < rowOfGroups.size()) {
                DisplayDataGroup group = rowOfGroups.get(i);
                formattedGroupsRow.add(new FormattedGroup(group, width));
            } else {
                formattedGroupsRow.add(new FormattedGroup(width));
            }
        }
        return formattedGroupsRow;
    }

    private boolean hasLineToPrint(List<FormattedGroup> formattedGroupsRow, int lineIndex) {
        for (FormattedGroup formattedGroup : formattedGroupsRow) {
            if (lineIndex < formattedGroup.lines.size()) {
                return true;
            }
        }
        return false;
    }

    private List<List<DisplayDataGroup>> getRowsOfGroups() {
        List<List<DisplayDataGroup>> rowsOfGroups = new ArrayList<>();
        for (int i = 0; i < groups.size(); i = i + columns) {
            List<DisplayDataGroup> rowOfGroups = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                if ((i + j) < groups.size()) {
                    rowOfGroups.add(groups.get(i + j));
                }
            }
            rowsOfGroups.add(rowOfGroups);
        }
        return rowsOfGroups;
    }

    private int getEffectiveColumnWidth() {
        int width = display.getWidth();
        width = width - 2;//left and right bounds
        width = width - (columns - 1); //columns delimiters
        width = width - columns * 2; //spaces
        width = width / columns;//per column
        return width;
    }

    private int getLastColumnRemainder() {
        int columnWidth = getEffectiveColumnWidth();
        int width = columnWidth * columns;
        width = width + 2;//left and right bounds
        width = width + (columns - 1); //columns delimiters
        width = width + columns * 2; //spaces
        return display.getWidth() - width;
    }

    private static final class FormattedGroup {

        private List<String> lines;
        private int width;

        public FormattedGroup(DisplayDataGroup group, int columnWidth) {
            this.lines = group.formatToColumn(columnWidth);
            width = columnWidth;
        }

        public FormattedGroup(int columnWidth) {
            this.lines = new ArrayList<>();
            width = columnWidth;
        }

        public String getLine(int lineIndex) {
            return lineIndex >= lines.size() ? "" : lines.get(lineIndex);
        }
    }
}
