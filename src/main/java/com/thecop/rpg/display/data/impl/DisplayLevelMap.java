package com.thecop.rpg.display.data.impl;

import com.thecop.rpg.display.data.DisplayData;
import com.thecop.rpg.level.LevelMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheCop on 20.12.2015.
 */
public class DisplayLevelMap implements DisplayData {

    private static final int CONTROL_STRING_FIELD_WIDTH = 5;
    private LevelMap levelMap;

    public DisplayLevelMap(LevelMap levelMap) {
        this.levelMap = levelMap;
    }

    @Override
    public List<String> formatToWidth(int width) {
        if (width < levelMap.getWidth()) {
            throw new IllegalArgumentException("Width given is too small");
        }

        return getLevelMapLines();
    }

    private List<String> getLevelMapLines(){
        List<String> lines = new ArrayList<>();
        for(int i=0;i<levelMap.getGrid().length;i++){
            lines.add(getRow(i));
        }
        return lines;
    }
    private String getRow(int yCoord){
        StringBuilder line = new StringBuilder();
        for(int i=0;i<levelMap.getGrid()[yCoord].length;i++){
            line.append(levelMap.getGrid()[yCoord][i].getDisplayChar());
        }
        return line.toString();
    }

}
