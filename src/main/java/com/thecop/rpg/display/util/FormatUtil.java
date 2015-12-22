package com.thecop.rpg.display.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by TheCop on 23.12.2015.
 */
public final class FormatUtil {

    private FormatUtil() {
    }

    public static final List<String> formatToWidth(String data, int width) {
        List<String> rows = new ArrayList<>();
        if (data == null) {
            return rows;
        }
        if (data.length() <= width) {
            rows.add(data);
            return rows;
        }
        //try splitting by spaces
        List<String> split = new ArrayList<>(Arrays.asList(data.split(" ")));
        rows.addAll(formatSplit(split, width));
        return rows;
    }

    private static final List<String> formatSplit(List<String> splitBySpace, int width) {
        List<String> rows = new ArrayList<>();
        if (splitBySpace.size() == 0) {
            return rows;
        }
        //if the word is larger than given width
        String firstWord = splitBySpace.get(0);
        if (firstWord.length() > width) {
            rows.add(firstWord.substring(0, width));
            splitBySpace.set(0, firstWord.substring(width));
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator<String> iterator = splitBySpace.iterator();
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (word.trim().isEmpty()) {
                    iterator.remove();
                    continue;
                }
                //first word in line
                if (sb.length() == 0) {
                    sb.append(word);
                    iterator.remove();
                }
                //further words in line
                else {
                    if (sb.length() + word.length() + 1 <= width) {
                        sb.append(" ").append(word);
                        iterator.remove();
                    } else {
                        break;
                    }
                }
            }
            rows.add(sb.toString());
        }
        rows.addAll(formatSplit(splitBySpace, width));
        return rows;
    }
}
