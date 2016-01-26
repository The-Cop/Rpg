package com.thecop.rpg.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Util {

    private Util() {
    }

    public static String inputSystem() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine().toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }
}
