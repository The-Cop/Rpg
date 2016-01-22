package com.thecop.rpg;


import com.thecop.rpg.display.Display;
import com.thecop.rpg.display.command.DisplayCommand;
import com.thecop.rpg.display.command.impl.DisplayByColumnsCommand;
import com.thecop.rpg.display.command.impl.DisplayLevelMapCommand;
import com.thecop.rpg.display.command.impl.DisplayMenuCommand;
import com.thecop.rpg.display.command.impl.DisplayPlainCommand;
import com.thecop.rpg.display.data.DisplayData;
import com.thecop.rpg.display.data.DisplayDataGroup;
import com.thecop.rpg.display.data.impl.DisplayDataSimple;
import com.thecop.rpg.display.data.impl.DisplayDataWithValue;
import com.thecop.rpg.display.data.impl.DisplayMenuItem;
import com.thecop.rpg.userinput.MenuItem;
import com.thecop.rpg.level.LevelMap;
import com.thecop.rpg.level.generator.impl.RoomLevelMapGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TheCop on 15.12.2015.
 */
public class Main {
    private static final int DISPLAY_WIDTH = 180;
    public static void main(String[] args) {
//        testTableOutput();
//        testPlainOutput();
//        testMenuOutput();
        testLevelMapOutput();
        testLevelMapOutput();
        testLevelMapOutput();
    }

    private static void testLevelMapOutput() {
        Display display = new Display(DISPLAY_WIDTH);
        List<DisplayCommand> displayCommands = new ArrayList<>();

        LevelMap levelMap = new RoomLevelMapGenerator(DISPLAY_WIDTH-4,30).generateMap();
        DisplayCommand levelCommand = new DisplayLevelMapCommand(levelMap);
        displayCommands.add(levelCommand);

        //menu and text in table view
        DisplayDataGroup menuGroup = createMenuDisplayGroup();
        DisplayDataGroup textGroup = new DisplayDataGroup(
                Arrays.asList(
                        new DisplayDataSimple[]
                                {
                                        new DisplayDataSimple("You see a great wide cave."),
                                        new DisplayDataSimple("Since we are moving from left to right we need the two corresponding x values, but only one y value since we won't be moving up or down. When we move vertically we will need the y values. In the for loop at the beginning of each function, we iterate from the starting value (x or y) to the ending value until we have carved out the entire corridor.")
                                }
                )
        );
        List<DisplayDataGroup> groups = new ArrayList<>();
        groups.add(menuGroup);
        groups.add(textGroup);
        DisplayByColumnsCommand displayByColumnsCommand = new DisplayByColumnsCommand(2,groups);
        displayCommands.add(displayByColumnsCommand);
        display.display(displayCommands);
    }

    private static void testMenuOutput() {
        Display display = new Display(DISPLAY_WIDTH);
        String description = "Cast a mega spell on all enemies in universe. Deals 100 damage.";
        String controlString = "D";
        List<DisplayCommand> commands = new ArrayList<>();
        commands.add(new DisplayMenuCommand(new DisplayMenuItem(new SimpleMenuItem(controlString, description))));
        commands.add(createDisplayMenuCommand());
        commands.add(new DisplayMenuCommand(new DisplayMenuItem(new SimpleMenuItem(controlString, description))));
        display.display(commands);
    }

    private static DisplayCommand createDisplayMenuCommand() {
        DisplayCommand dc = new DisplayMenuCommand(createMenuDisplayGroup());
        return dc;
    }

    private static DisplayDataGroup createMenuDisplayGroup() {
        return new DisplayDataGroup(
                createMenu().stream()
                        .map(mi -> new DisplayMenuItem(mi))
                        .collect(Collectors.toList())
        );
    }

    private static List<MenuItem> createMenu() {
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new SimpleMenuItem("D", "Cast a mega spell on all enemies in universe. Deals 100 damage."));
        menu.add(new SimpleMenuItem("S", "Stop everything"));
        menu.add(new SimpleMenuItem("A", "Attack manually. Again, very long unimaginable menu description with some shoop-da-woop in the ENDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD"));
        menu.add(new SimpleMenuItem("Q", "Quit"));
        return menu;
    }

    private static void testPlainOutput() {
        Display display = new Display(DISPLAY_WIDTH);
        String s1 = "��������� ������. AZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZ" +
                "������� ������� �� �Les vrais pourtraits...� ����� ���� (1584). " +
                "������ ������������� ���� - ���� �� ���� ������ � ������������ ������ ������������ �������� ������� � ��� ������. � 390-� ����� ��������� ������ ����������� � ����������� ������ �������, �������� ����� �������� ����������� �������� � 393 �� 404 ���. �������� ������������ �������� ������� (��. 185 - ��. 254) ����� �������� ������ ��� ��� ����� ���������������� ���������, ������ �� ���������� ���������� ����� � 381 ���� ������� ��������� �������������� ������������ ������ ���� ������ ������ ���������. �������� �������� ������������ � ������, ��������� � � �������� ������� �������. ������������ ����� �� ��������� �������� ���� ������ ��������� ������ ������� ������� �������� �������� � �������� ������ ���������������." +
                "� ������ �������� ��������� ����������� ���� ������ � ���� ��� ������� ������������ �������. ���������� ����������� ���������� ������, ������� ���� �������� ����� � ����� ����������� ����������, ��������� �� ���. 1:26-27 � �� �������� ������ ������ � ���, ���, �� ���������� ������ ���� ������, ����� ������ ��� ��������. ������ �� ������� ����������� � ����� ��������. � 399 ���� �� ������ ��� ����������������� ������� ���������������� � ����������� ���� �������� ������, � ����� ��� ������� � ���������. ��� ������� ���� ������������ �������� � ����������, ���� � �� �����. ������������ � ���� ���������� ������������ ��������, ������ ����� ������� ��������� ������ ��������� ��������� � ������ �����������, ���� ������� ����������� � ����� �����������. ����� ��������� �� ����� � ��������������� ������ �������� �������� � ����� �������� � ������ �� ������� ������������ ������ ���������. ��� ������������������ ����������� ��������� ���� ���� ������ � ������ ���������� � ������������ ���������, ��� ��������������� ��� ����������, ��������� �������� �������������������� ������������.";
        String s2 = "���������� DC-6 ��� ���������� - ����������� ����������, ������������ ������� �� ������� 1 ������ 1955 ���� � ���� � ������� �� ��������� � � ��������� ���������� �� ������� (���� ��������). ������������ ������ Douglas DC-6B ������������ ������������ United Air Lines �������� ������������ ���� �� ������� � �������� (���� ������), �� ������ ��������� ����� ����� ����� ���������� ��������� � �������, ��� ���� ������� 44 ��������. ���������� ������ ���� �����������, ��� �������� ���������� ����� ��������� ���������� �� ���� �����, ����� ���� ���� �������� ������������ ���� �������������, � ������� �������� ��� ���� ������ ������������� �������� ������������ ������������.";
        DisplayDataSimple d1 = new DisplayDataSimple(s1);
        DisplayDataSimple d2 = new DisplayDataSimple(s2);
        DisplayDataGroup dg = new DisplayDataGroup(Arrays.asList(new DisplayDataSimple[]{d1, d2, new DisplayDataWithValue("I am valued!", "666")}));
        DisplayPlainCommand dpc1 = new DisplayPlainCommand(d1, false);
        DisplayPlainCommand dpc2 = new DisplayPlainCommand(d2, false);
        DisplayPlainCommand dpcg = new DisplayPlainCommand(dg, false);
        display.display(dpc1);
        display.display(dpc2);
        display.display(Arrays.asList(new DisplayCommand[]{new DisplayByColumnsCommand(4, getDisplaydataGroups(8)), dpc1, dpc2}));
        display.display(dpcg);
    }

    private static void testTableOutput() {
        Display display = new Display(DISPLAY_WIDTH);
        DisplayCommand c = new DisplayByColumnsCommand(4, getDisplaydataGroups(8));
        display.display(c);
    }

    private static List<DisplayDataGroup> getDisplaydataGroups(int count) {
        List<DisplayDataGroup> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (i % 3 == 0) list.add(getLongDataGroup(i + 1));
            else if (i % 2 == 0) list.add(getMediumDataGroup(i + 1));
            else list.add(getShortDataGroup(i + 1));
        }
        return list;
    }

    private static DisplayDataGroup getShortDataGroup(int n) {
        List<DisplayData> ddata = new ArrayList<>();
        ddata.add(new DisplayDataSimple("I am short display data no" + n));
        ddata.add(new DisplayDataWithValue("I am short display data with value", "555"));
        ddata.add(new DisplayDataWithValue("Strength", "5"));
        return new DisplayDataGroup(ddata);
    }

    private static DisplayDataGroup getMediumDataGroup(int n) {
        List<DisplayData> ddata = new ArrayList<>();
        ddata.add(new DisplayDataSimple("I am medium display data no" + n));
        ddata.add(new DisplayDataWithValue("I am medium data with value", "555"));
        ddata.add(new DisplayDataWithValue("Agility", "5"));
        ddata.add(new DisplayDataSimple(""));
        ddata.add(new DisplayDataSimple("I also have some long strings to demonstrate the line breaking and formatting, so look at this really long text here, lol."));
        ddata.add(new DisplayDataWithValue("And the same shit with valued data display object. Hardly to believe the game would have such long valued things, but who knows?", "666"));
        return new DisplayDataGroup(ddata);
    }

    private static DisplayDataGroup getLongDataGroup(int n) {
        List<DisplayData> ddata = new ArrayList<>();
        ddata.add(new DisplayDataSimple("I am long display data no" + n));
        ddata.add(new DisplayDataWithValue("I am long data with value", "555"));
        ddata.add(new DisplayDataWithValue("Ingelligence", "5"));
        ddata.add(new DisplayDataSimple(""));
        ddata.add(new DisplayDataSimple("I also have some long strings to demonstrate the line breaking and formatting, so look at this really long text here, omg."));
        ddata.add(new DisplayDataWithValue("And the same shit with valued data display object. Hardly to believe the game would have such long valued things, but who knows?", "777"));
        ddata.add(new DisplayDataSimple(""));
        ddata.add(new DisplayDataSimple("AZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZAZ"));
        ddata.add(new DisplayDataWithValue("OLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLOOLOLOLO", "IAMVALUE"));

        return new DisplayDataGroup(ddata);
    }

    private static void printLine(int width) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < width; i++) {
            sb.append("-");
        }
        System.out.println(sb.toString());
    }

    private static class SimpleMenuItem implements MenuItem{

        private String controlString;
        private String description;

        public SimpleMenuItem(String controlString, String description) {
            this.controlString = controlString;
            this.description = description;
        }

        @Override
        public String getControlString() {
            return controlString;
        }

        @Override
        public String getDescription() {
            return description;
        }
    }
}
