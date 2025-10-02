package ru.mephi.week6.lesson1.Task1;

import java.io.*;
import java.util.Scanner;

public class Task1 {

    /**
     * <h2>Задача: копирование изображения</h2>
     * <br>
     * <h2>Описание: </h2>
     * <p>Вам необходимо написать программу, которая копирует файл изображения с
     * одного места в другое, используя классы {@code FileInputStream} и {@code FileOutputStream}. Программа должна:</p>
     * <ul>
     * <li>Прочитать исходный файл изображения (например, image.jpg) с помощью класса {@code FileInputStream}.</li>
     * <li>Записать прочитанные данные в новый файл (например, copy_image.jpg) с помощью класса FileOutputStream.</li>
     * <li>Для повышения производительности используйте чтение данных порциями (например, по 4 KB), чтобы не читать по одному байту за раз.</li>
     * </ul>
     */

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter source file");
        String sourceFilePath = scanner.nextLine();

        File sourceFile = new File(sourceFilePath);
        if (!sourceFile.exists()) {
            System.out.println("Source file not found");
            return;
        }

        System.out.println("Enter destination file");
        String destinationFilePath = scanner.nextLine();

        File destinationFile = new File(destinationFilePath);
        boolean append = false;
        boolean duplicate = false;

        String postfix = "(1)";
        /*
        if (destinationFilePath.equals(sourceFilePath)) {
            System.out.println("Create dublicate to file (y|n)");
            String answer = scanner.nextLine();
            duplicate = answer.equalsIgnoreCase("y");
            if (duplicate){
                destinationFile = new File(destinationFilePath + postfix);
            }
            return;
        }
        */
        if (destinationFile.exists()) {
            System.out.println("Append to file (y|n)");
            String answer = scanner.nextLine();
            append = answer.equalsIgnoreCase("y");
        } else {
            destinationFile.createNewFile();
        }


        FileOutputStream fileOutputStream = new FileOutputStream(destinationFile, append);
        FileInputStream fileInputStream = new FileInputStream(sourceFile);

        byte[] buffer = new byte[1024 * 4];
        int data;
        while ((data = fileInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, data);
        }
        fileInputStream.close();
        fileOutputStream.close();

        scanner.close();
    }

}
