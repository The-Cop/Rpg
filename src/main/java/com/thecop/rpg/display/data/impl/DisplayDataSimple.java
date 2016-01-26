package com.thecop.rpg.display.data.impl;

import com.thecop.rpg.display.data.DisplayData;
import com.thecop.rpg.display.util.FormatUtil;

import java.util.List;

/**
 * Created by TheCop on 15.12.2015.
 */
public class DisplayDataSimple implements DisplayData {

    protected String data;

    public DisplayDataSimple(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<String> formatToWidth(int width) {
        return FormatUtil.formatToWidth(data, width);
    }

    @Override
    public String toString() {
        return data;
    }
}
