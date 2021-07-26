package utils;

import org.apache.log4j.LogManager;

public class Logger {
    private static org.apache.log4j.Logger getLogger() {
        return LogManager.getLogger(Logger.class);
    }

    public static void logDebug(String text) {
        getLogger().debug(text);
    }
}
