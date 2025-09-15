package ru.mephi.week2.lesson1.enumExample;

public class Example {
    public static void main(String[] args) {
        DayOfWeek a = DayOfWeek.WEDNESDAY;
        DayOfWeek b = DayOfWeek.SATURDAY;
        switch (a) {
            case MONDAY:
                System.out.println(DayOfWeek.MONDAY);
                break;
            case TUESDAY:
                System.out.println(DayOfWeek.TUESDAY);
                break;
            default:
                System.out.println("DEFAULT");
        }
    }
}
