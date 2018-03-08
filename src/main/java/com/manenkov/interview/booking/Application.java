package com.manenkov.interview.booking;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Booking service application. Entry point.
 */
public final class Application {

    /**
     * Command line arguments.
     */
    private final String[] args;

    /**
     * Logger.
     */
    private final Logger logger;

    /**
     * Ctor.
     *
     * @param level Logging level.
     * @param args Command line arguments.
     */
    public Application(final Level level, final String... args) {
        this.args = args;
        this.logger =
            Logger.getLogger(Application.class.getName());
        this.logger.setLevel(level);
    }

    /**
     * Entry point.
     */
    private void execute() {
        logger.info("Placeholder");
    }

    /**
     * Entry point.
     *
     * @param args Command line arguments
     */
    public static void main(final String... args) {
        new Application(Level.INFO, args).execute();
    }
}
