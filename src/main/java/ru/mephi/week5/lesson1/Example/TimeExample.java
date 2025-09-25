package ru.mephi.week5.lesson1.Example;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeExample {
    public static void main(String[] args) {

        // Работа с временем без даты: LocalTime
        LocalTime currentTime = LocalTime.now();
        System.out.println("Текущее время: " + currentTime);

        // Создание времени вручную (часы, минуты, секунды)
        LocalTime time1 = LocalTime.of(14, 30, 50);
        System.out.println("Заданное время: " + time1);

        // Прибавление и вычитание времени
        LocalTime plusOneHour = time1.plusHours(1);
        System.out.println("Время +1 час: " + plusOneHour);

        LocalTime minus15Minutes = time1.minusMinutes(15);
        System.out.println("Время -15 минут: " + minus15Minutes);

        // Сравнение времени
        if (plusOneHour.isAfter(time1)) {
            System.out.println(plusOneHour + " позже, чем " + time1);
        }

        // Работа с датой без времени: LocalDate
        LocalDate currentDate = LocalDate.now();
        System.out.println("Сегодняшняя дата: " + currentDate);

        // Создание конкретной даты
        LocalDate birthday = LocalDate.of(1990, 6, 15);
        System.out.println("Дата рождения: " + birthday);

        // Вычисление разницы между датами (в днях)
        long daysBetween = ChronoUnit.DAYS.between(birthday, currentDate);
        System.out.println("Дней с даты рождения: " + daysBetween);

        // Работа с датой и временем без зоны: LocalDateTime
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("Текущие дата и время: " + nowDateTime);

        // Создание LocalDateTime вручную
        LocalDateTime specificDateTime = LocalDateTime.of(2025, 9, 25, 14, 30, 0);
        System.out.println("Заданная дата и время: " + specificDateTime);

        // Форматирование даты и времени в удобный формат
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDateTime = specificDateTime.format(formatter);
        System.out.println("Отформатированные дата и время: " + formattedDateTime);

        // Работа с временем с учётом временной зоны: ZonedDateTime
        ZonedDateTime zonedNow = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        System.out.println("Текущие дата и время по Москве: " + zonedNow);

        ZonedDateTime zonedNewYork = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("Текущие дата и время по Нью-Йорку: " + zonedNewYork);

        // Разница во времени между двумя ZonedDateTime
        Duration duration = Duration.between(zonedNewYork, zonedNow);
        System.out.println("Разница во времени между Москвой и Нью-Йорком в часах: " + duration.toHours());
    }
}
