package challengeOne;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.setLogLevel(Logger.LogLevel.WARNING); // Set log level to WARNING

        // Log messages with different levels
        logger.log(Logger.LogLevel.INFO, "This is an informational message."); // Won't be logged
        logger.log(Logger.LogLevel.WARNING, "This is a warning message."); // Will be logged
        logger.log(Logger.LogLevel.ERROR, "This is an error message!"); // Will be logged

        // Simulate log rotation
        for (int i = 0; i < 100; i++) {
            logger.log(Logger.LogLevel.INFO, "Filling up the log file...");
        }

        System.out.println("Check 'application.log' and 'application_backup.log' for logs.");
    }
}
