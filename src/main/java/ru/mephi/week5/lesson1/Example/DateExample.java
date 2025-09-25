package ru.mephi.week5.lesson1.Example;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateExample {
    public static void main(String[] args) {
        // Создание объекта Date с текущим временем
        Date now = new Date();
        System.out.println("Текущая дата и время: " + now);

        // Создание объекта Date из микросекунд с 1 января 1970 года (timestamp)
        long millis = 1695620400000L;  // Пример метки времени
        Date specificDate = new Date(millis);
        System.out.println("Дата из timestamp (миллисекунды): " + specificDate);

        // Получение времени в миллисекундах с 1 января 1970 года
        long time = now.getTime();
        System.out.println("Текущее время в миллисекундах: " + time);

        // Сравнение дат
        if (specificDate.before(now)) {
            System.out.println("specificDate раньше, чем now");
        } else if (specificDate.after(now)) {
            System.out.println("specificDate позже, чем now");
        } else {
            System.out.println("Даты совпадают");
        }
        int value = specificDate.compareTo(now);
        System.out.println("value after compareTo: " + value);

        // Форматирование даты с помощью SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String formattedDate = sdf.format(now);
        System.out.println("Отформатированная текущая дата: " + formattedDate);

        // Парсинг строки в дату (строка должна совпадать с шаблоном)
        try {
            String dateString = "25.12.2023 10:15:30";
            Date parsedDate = sdf.parse(dateString);
            System.out.println("Распарсенная дата из строки: " + parsedDate);
        } catch (Exception e) {
            System.out.println("Ошибка при парсинге даты: " + e.getMessage());
        }

        // Изменение времени путем установки нового timestamp
        now.setTime(millis);
        System.out.println("Дата после изменения времени: " + now);
    }
}