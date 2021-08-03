package utils;

import io.qameta.allure.Allure;
import org.apache.log4j.LogManager;

public class Logger {
    private static org.apache.log4j.Logger getLogger() {
        return LogManager.getLogger(Logger.class);
    }

    public static void logFatal(String text) {
        getLogger().fatal(text);
    }

    public static void logError(String text) {
        getLogger().error(text);
    }

    public static void logWarn(String text) {
        getLogger().warn(text);
    }

    public static void logStep(int step, String text) {
        getLogger().info(String.format("Step %d: %s", step, text));
        Allure.step(text);
    }

    public static void logInfo(String text) {
        getLogger().info(text);
    }

    public static void logDebug(String text) {
        getLogger().debug(text);
    }

    public static void logTrace(String text) {
        getLogger().trace(text);
    }

}
