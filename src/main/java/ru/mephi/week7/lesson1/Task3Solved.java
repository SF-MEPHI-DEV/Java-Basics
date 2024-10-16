package ru.mephi.week7.lesson1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Task3Solved {

    /**
     * <h2>Задача: копирование изображения</h2>
     * <br>
     * <h2>Описание: </h2>
     * <p>Вам необходимо написать программу, которая копирует файл изображения с
     * одного места в другое, используя классы FileInputStream и FileOutputStream. Программа должна:</p>
     * <ul>
     * <li>Прочитать исходный файл изображения (например, image.jpg) с помощью класса FileInputStream.</li>
     * <li>Записать прочитанные данные в новый файл (например, copy_image.jpg) с помощью класса FileOutputStream.</li>
     * <li>Для повышения производительности используйте чтение данных порциями (например, по 4 KB), чтобы не читать по одному байту за раз.</li>
     * </ul>
     */

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter source file path: ");
            String sourceFilePath = scanner.nextLine();

            File sourceFile = new File(sourceFilePath);

            if (!sourceFile.exists()) {
                System.out.println("Source file does not exist");
                return;
            }

            System.out.print("Enter destination file path: ");
            String destinationFilePath = scanner.nextLine();

            File destinationFile = new File(destinationFilePath);

            if (destinationFilePath.equals(sourceFilePath)) {
                System.out.println("Source and destination files are the same");
                return;
            }

            if (!destinationFile.exists()) {
                destinationFile.createNewFile();
            }

            byte[] buffer = new byte[4096];

            try (FileInputStream fis = new FileInputStream(sourceFile); FileOutputStream fos = new FileOutputStream(destinationFilePath)) {

                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }


    }
}
