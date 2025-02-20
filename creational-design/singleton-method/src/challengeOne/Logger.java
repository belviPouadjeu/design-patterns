package challengeOne;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Logger instance;
    private static final Object lock = new Object();
    private static final String LOG_FILE = "application.log";
    private static final long MAX_FILE_SIZE = 5 * 1024; // 5 KB for demo
    private LogLevel currentLogLevel = LogLevel.INFO; // Default log level

    // Enum for log levels
    public enum LogLevel {
        INFO, WARNING, ERROR
    }

    // Private constructor
    private Logger() {}

    // Thread-safe Singleton instance
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Set log level
    public void setLogLevel(LogLevel level) {
        this.currentLogLevel = level;
    }

    // Log method with level
    public void log(LogLevel level, String message) {
        if (level.ordinal() >= currentLogLevel.ordinal()) {
            rotateLogFileIfNeeded();
            try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                writer.println("[" + level + "] " + timestamp + " - " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Log rotation
    private void rotateLogFileIfNeeded() {
        File logFile = new File(LOG_FILE);
        if (logFile.exists() && logFile.length() > MAX_FILE_SIZE) {
            File backupFile = new File("application_backup.log");
            if (backupFile.exists()) {
                backupFile.delete(); // Delete previous backup
            }
            logFile.renameTo(backupFile); // Rename old log file
        }
    }
}
