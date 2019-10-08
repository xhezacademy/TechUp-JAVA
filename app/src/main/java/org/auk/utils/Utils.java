package org.auk.utils;

import java.util.Map;

public class Utils {

    private static void printSystemProperties() {
        for (Map.Entry<?,?> e : System.getProperties().entrySet()) {
            System.out.println(String.format("%s = %s", e.getKey(), e.getValue()));
        }
    }
}
