package ru.mephi.bonus.StreamApi.Tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;

public class Task2Solved {

    static class LogEntry {
        LocalDateTime timestamp;
        String level;
        String message;

        public LogEntry(LocalDateTime timestamp, String level, String message) {
            this.timestamp = timestamp;
            this.level = level;
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getLevel() {
            return level;
        }

        public String getMessage() {
            return message;
        }

        public int getHour() {
            return timestamp.getHour();
        }
    }

    public static void main(String[] args) {

        List<LogEntry> logs = Arrays.asList(
                new LogEntry(LocalDateTime.of(2024, 1, 15, 10, 30), "ERROR", "NullPointerException"),
                new LogEntry(LocalDateTime.of(2024, 1, 15, 10, 45), "ERROR", "NullPointerException"),
                new LogEntry(LocalDateTime.of(2024, 1, 15, 10, 50), "INFO", "System started"),
                new LogEntry(LocalDateTime.of(2024, 1, 15, 11, 15), "ERROR", "IOException"),
                new LogEntry(LocalDateTime.of(2024, 1, 15, 11, 30), "ERROR", "SQLException"),
                new LogEntry(LocalDateTime.of(2024, 1, 15, 11, 45), "ERROR", "IOException"),
                new LogEntry(LocalDateTime.of(2024, 1, 15, 12, 10), "WARN", "Low memory"),
                new LogEntry(LocalDateTime.of(2024, 1, 15, 12, 20), "ERROR", "NullPointerException")
        );

        Map<Integer, Map<String, Long>> errorsByHour = logs.stream()
                .filter(log -> "ERROR".equals(log.getLevel()))
                .collect(Collectors.groupingBy(
                        LogEntry::getHour,
                        Collectors.groupingBy(
                                LogEntry::getMessage,
                                Collectors.counting()
                        )
                ));
        errorsByHour.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(hourEntry -> {
                    System.out.println("Час " + hourEntry.getKey() + ":");
                    hourEntry.getValue().forEach((error, count) ->
                            System.out.println("  " + error + ": " + count)
                    );
                });
    }
}
