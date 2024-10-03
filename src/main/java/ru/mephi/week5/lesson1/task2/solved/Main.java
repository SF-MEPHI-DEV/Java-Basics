package ru.mephi.week5.lesson1.task2.solved;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws ParseException {

        EventManager manager = new EventManager();

        manager.addEvent(
                "Встреча с клиентом",
                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("15/11/2023 10:00"),
                "Офис"
        );
        manager.addEvent(
                "Корпоратив",
                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("20/12/2023 19:00"),
                "Ресторан"
        );

        manager.printEvents();
        manager.findEventsByDate(new SimpleDateFormat("dd/MM/yyyy").parse("15/11/2023"));
        manager.updateEventDate(
                "Встреча с клиентом",
                new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("15/11/2023 11:00")
        );

        manager.printEvents();

    }

}
