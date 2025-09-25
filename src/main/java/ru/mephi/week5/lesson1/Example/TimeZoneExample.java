package ru.mephi.week5.lesson1.Example;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeZoneExample {
    public static void main(String[] args) {
        // Получаем текущие дата и время в системной временной зоне
        ZonedDateTime nowSystem = ZonedDateTime.now();
        System.out.println("Текущее время с системной зоной: " + nowSystem);

        // Получение текущего времени в конкретной временной зоне
        ZonedDateTime nowMoscow = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        ZonedDateTime nowNewYork = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println("Время в Москве: " + nowMoscow);
        System.out.println("Время в Нью-Йорке: " + nowNewYork);

        // Создание LocalDateTime без зоны
        LocalDateTime localDateTime = LocalDateTime.of(2025, 9, 25, 15, 0);
        System.out.println("LocalDateTime без зоны: " + localDateTime);

        // Привязка LocalDateTime к зоне умеренного пояса
        ZonedDateTime zonedDateTimeMoscow = localDateTime.atZone(ZoneId.of("Europe/Moscow"));
        System.out.println("LocalDateTime с зоной Москвы: " + zonedDateTimeMoscow);

        // Преобразование времени из одной зоны в другую
        ZonedDateTime convertedToNewYork = zonedDateTimeMoscow.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("Преобразовано в зону Нью-Йорка: " + convertedToNewYork);

        // Форматирование времени для удобного вывода
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss z");
        System.out.println("Форматированное время Москвы: " + zonedDateTimeMoscow.format(formatter));
        System.out.println("Форматированное время Нью-Йорка: " + convertedToNewYork.format(formatter));

        // Вычисление разницы между двумя временами в разных зонах
        Duration duration = Duration.between(convertedToNewYork.toInstant(), zonedDateTimeMoscow.toInstant());
        System.out.println("Разница между Москвой и Нью-Йорком в часах: " + duration.toHours());

        // Пример работы с классом Calendar
        Calendar calendarMoscow = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        Calendar calendarNY = Calendar.getInstance(TimeZone.getTimeZone("America/New_York"));

        System.out.println("Время в Москве: " + calendarMoscow.getTime());
        System.out.println("Время в Нью-Йорке: " + calendarNY.getTime());
    }
}
