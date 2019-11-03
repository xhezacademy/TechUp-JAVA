package org.auk.utils;

import java.util.Map;

public class Utils {

    public static void printSystemProperties() {
        for (Map.Entry<?,?> e : System.getProperties().entrySet()) {
            System.out.println(String.format("%s = %s", e.getKey(), e.getValue()));
        }
    }

    /**
     * Prints a message
     *
     * @param message Message to print
     */
    public static void print(String message) {
        System.out.println(message);
    }
}
