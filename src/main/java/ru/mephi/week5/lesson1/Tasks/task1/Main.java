package ru.mephi.week5.lesson1.Tasks.task1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {


    /**
     * <h2>Задача: Калькулятор сроков</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Создайте программу, которая поможет пользователям рассчитывать различные даты на
     * основе заданной начальной даты. Программа должна предоставлять следующие функции:</p>
     * <ul>
     * <li> Найти дату через определенное количество дней:
     * - Пользователь вводит начальную дату и количество дней, и программа выводит дату, которая будет через это количество дней.
     * </li>
     * <br>
     * <li> Найти дату через определенное количество месяцев:
     * - Пользователь вводит начальную дату и количество месяцев, и программа выводит дату, которая будет через это количество месяцев.
     * </li>
     * <br>
     * <li> Найти день недели для заданной даты:
     * - Пользователь вводит дату, и программа выводит, какой это день недели.
     * </li>
     * <br>
     * <li> Найти количество дней между двумя датами:
     * - Пользователь вводит две даты, и программа отображает количество дней между ними.
     * </li>
     * </ul>
     */

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

        }
    }

}
