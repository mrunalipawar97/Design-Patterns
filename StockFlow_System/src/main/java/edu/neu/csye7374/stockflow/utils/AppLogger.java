package edu.neu.csye7374.stockflow.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLogger {
    private static final Logger logger = LogManager.getLogger();

    private AppLogger() { }

    public static void info(String str) {
        logger.info(str);
    }

    public static void error(String str) {
        logger.error(str);
    }

    public static void debug(String str) {
        logger.debug(str);
    }

    public static void trace(String str) {
        logger.trace(str);
    }

    public static void warn(String str) {
        logger.warn(str);
    }
}