package ru.mephi.week5.lesson2.Examples;

import java.io.File;
import java.io.IOException;

public class BasicFileOperations {
    public static void main(String[] args) {

        File parentDirectory = new File("test");
        if(!parentDirectory.exists()){
            System.out.println("Родительская директория не  существует - Выход из программы");
            return;
        }else{
            System.out.println("Родительская директория уже существует");
        }
        File file = new File("test","example.txt");
        File directory = new File("testDir");

        System.out.println("=== Основные операции с File ===");

        try {
            // 2. Создание файла
            if (file.createNewFile()) {
                System.out.println("Файл создан: " + file.getName());
            } else {
                System.out.println("Файл уже существует");
            }

            // 3. Проверка существования
            System.out.println("Файл существует: " + file.exists());

            // 4. Информация о файле
            System.out.println("Абсолютный путь: " + file.getAbsolutePath());
            System.out.println("\"Родитель\": " + file.getParent());
            System.out.println("Имя файла: " + file.getName());
            System.out.println("Размер: " + file.length() + " байт");
            System.out.println("Можно читать: " + file.canRead());
            System.out.println("Можно писать: " + file.canWrite());
            System.out.println("Это файл: " + file.isFile());
            System.out.println("Это директория: " + file.isDirectory());

            // 5. Создание директории
            if (directory.mkdir()) {
                System.out.println("Директория создана: " + directory.getName());
            } else {
                System.out.println("Директория уже существует или не может быть создана");
            }

            // 6. Удаление
            if (file.delete()) {
                System.out.println("Файл удален");
            }

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
}