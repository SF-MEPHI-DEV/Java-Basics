package ru.mephi.week5.lesson1.Example;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateComparisonExample {

    // Метод для обнуления времени у Date (оставить только дату)
    public static Date truncateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static void main(String[] args) throws Exception {
        // --- Работа с java.util.Date ---

        // Исходные даты с разным временем
        SimpleDateFormat sdfFull = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date1 = sdfFull.parse("25.09.2025 14:30:25");
        Date date2 = sdfFull.parse("25.09.2025 18:45:10");

        // Сравнение с точностью до миллисекунд (полная точность)
        System.out.println("Полное сравнение дат с временем: " + date1.equals(date2)); // false

        // Усечение времени — сравнение только даты без времени
        Date truncatedDate1 = truncateTime(date1);
        Date truncatedDate2 = truncateTime(date2);
        System.out.println("Сравнение только по дате (без времени): " + truncatedDate1.equals(truncatedDate2)); // true

        // Сравнение через форматирование (игнорируем время)
        SimpleDateFormat sdfDateOnly = new SimpleDateFormat("dd.MM.yyyy");
        String fmtDate1 = sdfDateOnly.format(date1);
        String fmtDate2 = sdfDateOnly.format(date2);
        System.out.println("Сравнение по отформатированной дате: " + fmtDate1.equals(fmtDate2)); // true

        // --- Работа с java.time ---

        // Создаём LocalDateTime с разным временем
        LocalDateTime ldt1 = LocalDateTime.of(2025, 9, 25, 14, 30, 25, 500_000_000); // 500 миллисекунд
        LocalDateTime ldt2 = LocalDateTime.of(2025, 9, 25, 18, 45, 10, 900_000_000); // 900 миллисекунд

        // Полное сравнение (включая миллисекунды)
        System.out.println("Полное сравнение LocalDateTime: " + ldt1.equals(ldt2)); // false

        // Усечение времени до секунд
        LocalDateTime truncatedLdt1 = ldt1.truncatedTo(ChronoUnit.SECONDS);
        LocalDateTime truncatedLdt2 = ldt2.truncatedTo(ChronoUnit.SECONDS);
        System.out.println("Сравнение LocalDateTime до секунд: " + truncatedLdt1.equals(truncatedLdt2)); // false

        // Сравнение только по дате (без времени)
        LocalDate dateOnly1 = ldt1.toLocalDate();
        LocalDate dateOnly2 = ldt2.toLocalDate();
        System.out.println("Сравнение LocalDate: " + dateOnly1.equals(dateOnly2)); // true
    }
}
