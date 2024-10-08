package ru.mephi.week5.lesson2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task2 {

    /**
     * <h2>Задание: тренировка работы с файлами</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Напишите программу на Java, которая выполняет следующие действия:</p>
     * <ul>
     * 	<li>Создаёт текстовый файл example.txt, если его нет.</li>
     * 	<li>Проверяет, существует ли файл и выводит его абсолютный путь.</li>
     * 	<li>Переименовывает файл в renamed_example.txt.</li>
     * 	<li>Проверяет, является ли файл директорией.</li>
     * 	<li>Удаляет файл.</li>
     * 	<li>Проверяет, существует ли файл после удаления.</li>
     * 	</ul>
     */

    public static void main(String[] args) throws IOException {

        String fileName = "example.txt";
        String newFileName = "renamed_example.txt";

        Path file = Paths.get(fileName);
        Path newFile = Paths.get(newFileName);

        if (!Files.exists(file)) {
            Files.createFile(file);
            System.out.println("File created: " + file);
        } else {
            System.out.println("File already exists: " + file);
        }

        Path absolutePath = file.toAbsolutePath();
        System.out.println("Absolute path: " + absolutePath);

        Files.move(file, newFile);

        if (Files.isDirectory(newFile)) {
            System.out.println(newFile + " is a directory");
        } else {
            System.out.println(newFile + " is a file");
        }

        Files.delete(newFile);

        if (Files.exists(newFile)) {
            System.out.println("File " + newFile + " exits");
        } else {
            System.out.println("File " + newFile + " does not exit");
        }

    }

}
