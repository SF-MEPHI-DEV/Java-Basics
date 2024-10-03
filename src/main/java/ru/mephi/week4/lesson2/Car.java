package ru.mephi.week4.lesson2;

public class Car {

    public String name = "String";
    public int year = 12;

    public Car() {

    }

    public int startEngine(String time, int thisYear) {
        return thisYear - this.year;
    }

    public int startEngine(String time) {
        return startEngine(time, year);
    }


    public int startEngine() {
        return startEngine("00:00", year);
    }


}
