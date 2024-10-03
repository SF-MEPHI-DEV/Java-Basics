package ru.mephi.week5.lesson1.task2.solved;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventManager {
    private List<Event> events = new ArrayList<>();

    public void addEvent(String title, Date date, String location) {
        events.add(new Event(title, date, location));
    }

    public void printEvents() {
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public void findEventsByDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String targetDate = sdf.format(date);
        boolean found = false;

        System.out.println("События на " + targetDate + ":");
        for (Event event : events) {
            if (sdf.format(event.getDate()).equals(targetDate)) {
                System.out.println(event);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Нет событий на эту дату.");
        }
    }

    public void updateEventDate(String title, Date newDate) {
        for (Event event : events) {
            if (event.getTitle().equals(title)) {
                event.setDate(newDate);
                System.out.println("Дата события \"" + title + "\" обновлена.");
                return;
            }
        }
        System.out.println("Событие с названием \"" + title + "\" не найдено.");
    }

}