package ru.mephi.bonus.StreamApi.Tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;

public class Task2 {

    /**
     * <h2>Задача: Обработка логов с ошибками</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Дан список логов системы. Каждый лог содержит временную метку, уровень важности
     * (INFO, WARN, ERROR) и сообщение об ошибке. Необходимо проанализировать логи и вывести
     * статистику по ошибкам: сгруппировать все ERROR логи по часам и посчитать количество
     * каждого типа ошибки в каждом часу.</p>
     * <br>
     * <h2>Требования:</h2>
     * <ul>
     *     <li>Отфильтровать только логи с уровнем ERROR</li>
     *     <li>Извлечь час из временной метки</li>
     *     <li>Сгруппировать ошибки по часам</li>
     *     <li>Внутри каждого часа посчитать количество каждого типа ошибки</li>
     *     <li>Вывести результат в формате: "Час - Тип ошибки: количество"</li>
     * </ul>
     * <br>
     * <h2>Пример входных данных:</h2>
     * <pre>
     * LogEntry(2024-01-15T10:30:00, ERROR, NullPointerException)
     * LogEntry(2024-01-15T10:45:00, ERROR, NullPointerException)
     * LogEntry(2024-01-15T10:50:00, INFO, System started)
     * LogEntry(2024-01-15T11:15:00, ERROR, IOException)
     * LogEntry(2024-01-15T11:30:00, ERROR, SQLException)
     * </pre>
     * <br>
     * <h2>Ожидаемый результат:</h2>
     * <pre>
     * Час 10:
     *   NullPointerException: 2
     * Час 11:
     *   IOException: 1
     *   SQLException: 1
     * </pre>
     */

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

    }
}
