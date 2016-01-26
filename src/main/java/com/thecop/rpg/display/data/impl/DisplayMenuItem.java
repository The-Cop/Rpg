package com.thecop.rpg.display.data.impl;

import com.thecop.rpg.display.data.DisplayData;
import com.thecop.rpg.userinput.MenuItem;
import com.thecop.rpg.display.util.FormatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheCop on 20.12.2015.
 */
public class DisplayMenuItem implements DisplayData {

    private static final int CONTROL_STRING_FIELD_WIDTH = 5;
    private MenuItem menuItem;

    public DisplayMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public List<String> formatToWidth(int width) {
        if (width < 10) {
            throw new IllegalArgumentException("Width given is too small");
        }
        List<String> formattedDescription = FormatUtil.formatToWidth(menuItem.getDescription(),
                width - CONTROL_STRING_FIELD_WIDTH);
        List<String> formatted = new ArrayList<>();
        String format = getFormat(width);
        for (int i = 0; i < formattedDescription.size(); i++) {
            String line = formattedDescription.get(i);
            if (i == 0) {
                formatted.add(String.format(format, menuItem.getControlString() + " - ", line));
            } else {
                formatted.add(String.format(format, "", line));
            }
        }
        return formatted;
    }

    private String getFormat(int width) {
        return "%-5s%-" + (width - CONTROL_STRING_FIELD_WIDTH) + "s";
    }
}
