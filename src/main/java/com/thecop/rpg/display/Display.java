package com.thecop.rpg.display;

import com.thecop.rpg.display.command.DisplayCommand;

import java.util.List;

/**
 * Created by TheCop on 15.12.2015.
 */
public class Display {
    private static final int DISPLAYS_SEPARATION_LINES_COUNT = 7;
    private int width;

    public Display(int width) {
        this.width = width;
    }

    public void display(List<DisplayCommand> commands) {
        printDisplaysSeparation();
        printLine();
        commands.stream().forEach(c -> executeCommand(c));
    }

    public void display(DisplayCommand command) {
        printDisplaysSeparation();
        printLine();
        executeCommand(command);
    }

    private void executeCommand(DisplayCommand command) {
        command.execute(this);
        printLine();
    }

    public void printLine() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < width; i++) {
            sb.append("-");
        }
        System.out.println(sb.toString());
    }

    public void printDisplaysSeparation() {
        for (int i = 0; i < DISPLAYS_SEPARATION_LINES_COUNT; i++) {
            System.out.println();
        }
    }

    public int getWidth() {
        return width;
    }
}
