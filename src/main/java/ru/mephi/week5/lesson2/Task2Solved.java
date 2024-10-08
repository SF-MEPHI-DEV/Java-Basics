package ru.mephi.week5.lesson2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Task2Solved {

    public static void main(String[] args) throws IOException {

        String initialFileName = "example.txt";
        String renamedFileName = "renamed_example.txt";

        Path initialFilePath = Paths.get(initialFileName);
        Path renamedFilePath = Paths.get(renamedFileName);

        if (!Files.exists(initialFilePath)) {
            Files.createFile(initialFilePath);
            System.out.println("Файл создан: " + initialFilePath.toAbsolutePath());
        } else {
            System.out.println("Файл уже существует: " + initialFilePath.toAbsolutePath());
        }

        if (Files.exists(initialFilePath)) {
            System.out.println("Файл существует: " + initialFilePath.toAbsolutePath());
        }

        Files.move(initialFilePath, renamedFilePath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Файл переименован в: " + renamedFilePath.toAbsolutePath());

        if (Files.isDirectory(renamedFilePath)) {
            System.out.println("Это директория.");
        } else {
            System.out.println("Это не директория.");
        }

        Files.delete(renamedFilePath);
        System.out.println("Файл удалён: " + renamedFilePath);

        if (!Files.exists(renamedFilePath)) {
            System.out.println("Файл успешно удалён.");
        } else {
            System.out.println("Файл всё ещё существует.");
        }

    }

}
