package org.auk.utils;

import java.util.logging.Level;

public class Logger {

    public static java.util.logging.Logger log() {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("app");
        logger.setLevel(Level.INFO);

        return logger;
    }

}
