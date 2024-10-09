package ru.mephi.week6.lesson1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Task3Solved {

    /**
     * <h2>Задание: изменение файла</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Напишите программу, которая:</p>
     * <ul>
     * 	<li>Читает текстовый файл с помощью {@code FileInputStream}.</li>
     * 	<li>Заменяет в нём все вхождения определённой строки на другую строку.</li>
     * 	<li>Записывает результат в новый файл с помощью {@code FileOutputStream}.</li>
     * </ul>
     */

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the input file path:");
        String inputFilePath = scanner.nextLine();

        System.out.println("Enter the string to search for:");
        String searchString = scanner.nextLine();

        System.out.println("Enter the string to replace with:");
        String replaceString = scanner.nextLine();

        System.out.println("Enter the output file path:");
        String outputFilePath = scanner.nextLine();

        byte[] fileContent = readFile(inputFilePath);
        String contentStr = new String(fileContent);
        String updatedContent = contentStr.replace(searchString, replaceString);
        writeFile(outputFilePath, updatedContent.getBytes());
        System.out.println("Replacement completed. Output saved to: " + outputFilePath);

    }

    private static byte[] readFile(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] fileContent = new byte[fis.available()];
        fis.read(fileContent);
        fis.close();
        return fileContent;

    }

    private static void writeFile(String filePath, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(data);
        fos.close();
    }

}
