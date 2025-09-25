package ru.mephi.week5.lesson1.Example;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeIntegrationExample {
    public static void main(String[] args) throws Exception {
        // --- Работа с java.util.Date ---
        Date oldDate = new Date();
        System.out.println("Текущая дата (Date): " + oldDate);

        // Конвертация Date в java.time.LocalDateTime
        Instant instantFromDate = oldDate.toInstant();
        LocalDateTime ldtFromDate = LocalDateTime.ofInstant(instantFromDate, ZoneId.systemDefault());
        System.out.println("Преобразование Date в LocalDateTime: " + ldtFromDate);

        // --- Работа с java.time.LocalDateTime ---

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Текущие дата и время (LocalDateTime): " + now);

        // Конвертация LocalDateTime обратно в Date
        ZonedDateTime zdt = now.atZone(ZoneId.systemDefault());
        Date dateFromLDT = Date.from(zdt.toInstant());
        System.out.println("LocalDateTime обратно в Date: " + dateFromLDT);

        // --- Работа с java.util.Calendar ---
        Calendar calendar = Calendar.getInstance();
        System.out.println("Текущий календарь (Calendar): " + calendar.getTime());

        // Конвертация Calendar в ZonedDateTime
        Instant instantFromCal = calendar.toInstant();
        ZonedDateTime zdtFromCal = instantFromCal.atZone(calendar.getTimeZone().toZoneId());
        System.out.println("Преобразование Calendar в ZonedDateTime: " + zdtFromCal);

        // Конвертация ZonedDateTime обратно в GregorianCalendar
        GregorianCalendar gregCalFromZDT = GregorianCalendar.from(zdtFromCal);
        System.out.println("ZonedDateTime обратно в GregorianCalendar: " + gregCalFromZDT.getTime());

        // --- Пример форматирования даты и времени ---
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDateTime = ldtFromDate.format(formatter);
        System.out.println("Отформатированная дата и время из LocalDateTime: " + formattedDateTime);

    }
}
