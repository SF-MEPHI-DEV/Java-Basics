package ru.mephi.week6.lesson2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2Solved {

    /**
     * <h2>Задание: Простой парсер CSV-файла</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Создайте программу, которая читает CSV-файл, разделяет его содержимое
     * по запятой и выводит каждую строку с отдельными значениями. Используйте
     * {@code Scanner} для чтения данных из файла.</p>
     */

    public static void main(String[] args) throws FileNotFoundException {

        String filePath = "src/main/java/ru/mephi/week6/lesson2/employees.csv";
        Scanner scanner = new Scanner(new File(filePath));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            for (String value : values) {
                System.out.print(value.trim() + " ");
            }
            System.out.println();
        }
    }
}
