package ru.mephi.week6.lesson2;

import java.io.*;
import java.util.Scanner;

public class Task1Solved {

    /**
     * <h2>Задание: Чтение данных из консоли и запись в файл</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Напишите программу, которая позволяет пользователю вводить строки
     * текста, и завершает ввод по команде "exit". Все введённые строки должны
     * быть записаны в файл. Используйте {@code Scanner} для чтения с консоли и
     * {@code FileWriter} для записи в файл.</p>
     */

    public static void main(String[] args) throws IOException {

        String filePath = "output.txt";

        File outputFile = new File(filePath);
        if (!outputFile.exists() && !outputFile.createNewFile()) {
            System.out.println("Error");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        FileWriter fileWriter = new FileWriter(outputFile);

        String input;

        while (!(input = scanner.nextLine()).equalsIgnoreCase("exit")) {
            fileWriter.write(input + "\n");
        }

        fileWriter.close();
        scanner.close();

        System.out.println("Текст успешно записан в файл " + filePath);


    }

}
