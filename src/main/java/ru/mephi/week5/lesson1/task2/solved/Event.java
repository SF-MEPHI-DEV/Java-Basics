package ru.mephi.week5.lesson1.task2.solved;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private String title;
    private Date date;
    private String location;

    public Event(String title, Date date, String location) {
        this.title = title;
        this.date = date;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format(
                "Название: %s, Дата: %s, Место: %s",
                title,
                new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date),
                location
        );
    }
}