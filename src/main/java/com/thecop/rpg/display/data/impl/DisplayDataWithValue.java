package com.thecop.rpg.display.data.impl;

import java.util.List;

/**
 * Created by TheCop on 15.12.2015.
 */
public class DisplayDataWithValue extends DisplayDataSimple {
    private String value;

    public DisplayDataWithValue(String data, String value) {
        super(data);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public List<String> formatToWidth(int width) {
        if (value.length() > width) {
            throw new IllegalArgumentException("value's length can not be larger than given width");
        }
        List<String> dataRows = super.formatToWidth(width);
        if (dataRows.size() == 0) {
            String format = "%" + width + "s";
            dataRows.add(String.format(format, value));
            return dataRows;
        }

        String lastRow = dataRows.get(dataRows.size() - 1);
        //value fits with last row
        if (lastRow.length() + 1 + value.length() <= width) {
            String format = "%-" + lastRow.length() + "s%" + (width - lastRow.length()) + "s";
            dataRows.set(dataRows.size() - 1, String.format(format, lastRow, value));
        } else {
            String format = "%" + width + "s";
            dataRows.add(String.format(format, value));
        }
        return dataRows;
    }

    @Override
    public String toString() {
        return data + " " + value;
    }
}
