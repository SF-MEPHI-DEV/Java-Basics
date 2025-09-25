package ru.mephi.week5.lesson1.Example;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CalendarExample {
    public static void main(String[] args) {
        // Создание экземпляра Calendar с текущей датой и временем
        Calendar calendar = Calendar.getInstance();
        System.out.println("Текущая дата и время: " + calendar.getTime());

        // Получение конкретных компонентов даты и времени
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24-часовой формат
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.println("Год: " + year);
        System.out.println("Месяц (0-11): " + month + " (для отображения добавьте 1)");
        System.out.println("День месяца: " + day);
        System.out.println("Часы (24ч): " + hour);
        System.out.println("Минуты: " + minute);
        System.out.println("Секунды: " + second);

        // Установка конкретной даты: 25 декабря 2023
        calendar.set(2023, Calendar.DECEMBER, 25);
        System.out.println("Установленная дата: " + calendar.getTime());

        // Прибавление и вычитание времени
        calendar.add(Calendar.DAY_OF_MONTH, 10); // Добавляем 10 дней
        System.out.println("Дата +10 дней: " + calendar.getTime());

        calendar.add(Calendar.MONTH, -2); // Вычитаем 2 месяца
        System.out.println("Дата -2 месяца: " + calendar.getTime());

        // Сравнение двух дат через Calendar
        Calendar anotherDate = Calendar.getInstance();
        anotherDate.set(2024, Calendar.JANUARY, 1);

        if (calendar.before(anotherDate)) {
            System.out.println("calendar раньше anotherDate");
        } else if (calendar.after(anotherDate)) {
            System.out.println("calendar позже anotherDate");
        } else {
            System.out.println("Даты совпадают");
        }
        int value = calendar.compareTo(anotherDate);
        System.out.println("value after compareTo: " + value);
        // Преобразование Calendar в Date и форматирование
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String formatted = sdf.format(date);
        System.out.println("Отформатированная дата: " + formatted);

        // Установка времени вручную (час, минута, секунда)
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 45);
        System.out.println("Дата и время после установки времени: " + calendar.getTime());
    }
}
