package com.thecop.rpg.display.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TheCop on 15.12.2015.
 */
public class DisplayDataGroup {

    private List<DisplayData> group;

    public DisplayDataGroup(List<DisplayData> group) {
        this.group = group;
    }

    public DisplayDataGroup(DisplayData data) {
        this.group = new ArrayList<>();
        this.group.add(data);
    }

    public List<String> formatToColumn(int width) {
        return group.stream()
                .map(g -> g.formatToWidth(width))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}

