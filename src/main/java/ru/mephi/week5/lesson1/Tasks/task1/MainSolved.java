package ru.mephi.week5.lesson1.Tasks.task1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MainSolved {

    public static void main(String[] args) throws ParseException {
        String[] options = {
                "Find date after n days",
                "Find date after n months",
                "Find day of the week",
                "Find count of days between",
                "Quit"
        };

        String dateForamtString = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateForamtString);

        boolean isWorking = true;
        Scanner scanner = new Scanner(System.in);

        while (isWorking) {

            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d. %s%n", i + 1, options[i]);
            }
            System.out.print("> ");

            int inputValue = scanner.nextInt();
            scanner.nextLine();

            switch (inputValue) {
                case 1: {
                    System.out.println("Enter date in format " + dateForamtString);
                    String dateString = scanner.nextLine();
                    System.out.println("Enter count of days");
                    int days = scanner.nextInt();

                    Calendar calendar = Calendar.getInstance();
                    Date date = dateFormat.parse(dateString);
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, days);
                    System.out.println("Output time: " + dateFormat.format(calendar.getTime()));
                    break;
                }
                case 2: {
                    System.out.println("Enter date in format " + dateForamtString);
                    String dateString = scanner.nextLine();
                    System.out.println("Enter count of months");
                    int months = scanner.nextInt();

                    Calendar calendar = Calendar.getInstance();
                    Date date = dateFormat.parse(dateString);
                    calendar.setTime(date);
                    calendar.add(Calendar.MONTH, months);
                    System.out.println("output time: " + dateFormat.format(calendar.getTime()));
                    break;
                }
                case 3: {
                    System.out.println("Enter date in format " + dateForamtString);
                    String dateString = scanner.nextLine();

                    Date date = dateFormat.parse(dateString);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    System.out.println("Day of the week: " + calendar.get(Calendar.DAY_OF_WEEK));
                }
                case 4: {
                    System.out.println("Enter date in format " + dateForamtString);
                    String dateString1 = scanner.nextLine();
                    System.out.println("Enter date in format " + dateForamtString);
                    String dateString2 = scanner.nextLine();

                    Date date1 = dateFormat.parse(dateString1);
                    Date date2 = dateFormat.parse(dateString2);
                    int days = (int) ((date1.getTime() - date2.getTime()) / (1000 * 3600 * 24));
                    System.out.println("Count of days: " + Math.abs(days));
                    break;
                }
                case 5: {
                    isWorking = false;
                    System.out.println("Good bye!");
                    break;
                }

                default: {
                    System.out.println("Invalid input");
                }
            }

        }
    }


}

