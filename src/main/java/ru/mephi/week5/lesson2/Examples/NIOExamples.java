package ru.mephi.week5.lesson2.Examples;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Stream;

public class NIOExamples {
    public static void main(String[] args) {
        System.out.println("=== Java NIO.2 (java.nio.file) - современный API ===\n");

        try {
            // 1. Основы работы с Path
            pathBasics();

            // 2. Проверка существования и свойств файлов
            fileProperties();

            // 3. Создание и удаление файлов/директорий
            createAndDelete();

            // 4. Чтение и запись файлов
            readWriteFiles();

            // 5. Работа с директориями
            directoryOperations();

            // 6. Атрибуты файлов
            fileAttributes();

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // 1. Основы работы с Path
    private static void pathBasics() {
        System.out.println("1. Работа с Path:");

        // Создание Path объектов
        Path path1 = Paths.get("example.txt");
        Path path2 = Paths.get("folder", "subfolder", "file.txt");
        Path path2_1 = Paths.get("folder/subfolder/file.txt");
        Path path3 = Paths.get("/absolute/path/file.txt");

        System.out.println("Относительный путь: " + path1);
        System.out.println("Составной путь: " + path2);
        System.out.println("Абсолютный путь: " + path3);

        // Операции с путями
        System.out.println("Имя файла: " + path2.getFileName());
        System.out.println("Родительская директория: " + path2.getParent());
        System.out.println("Количество элементов пути: " + path2.getNameCount());

        // Преобразование путей
        System.out.println("Абсолютный путь: " + path1.toAbsolutePath());
        System.out.println();
    }

    // 2. Проверка существования и свойств файлов
    private static void fileProperties() throws IOException {
        System.out.println("2. Проверка свойств файлов:");

        Path testFile = Paths.get("nio_test.txt");

        // Создаем тестовый файл
        Files.write(testFile, "Тестовое содержимое".getBytes());
        System.out.println("Файл существует: " + Files.exists(testFile));
        System.out.println("Это обычный файл: " + Files.isRegularFile(testFile));
        System.out.println("Это директория: " + Files.isDirectory(testFile));
        System.out.println("Файл читаемый: " + Files.isReadable(testFile));
        System.out.println("Файл доступен для записи: " + Files.isWritable(testFile));
        System.out.println("Размер файла: " + Files.size(testFile) + " байт");

        System.out.println();
    }

    // 3. Создание и удаление файлов/директорий
    private static void createAndDelete() throws IOException {
        System.out.println("3. Создание и удаление:");

        // Создание файла
        Path newFile = Paths.get("new_file.txt");
        if (!Files.exists(newFile)) {
            Files.createFile(newFile);
            System.out.println("Файл создан: " + newFile);
        }

        // Создание директорий
        Path newDir = Paths.get("test_directory");
        Path nestedDir = Paths.get("parent", "child", "grandchild");

        Files.createDirectories(newDir); // создает директорию
        Files.createDirectories(nestedDir); // создает всю цепочку директорий

        System.out.println("Директории созданы");

        //Удаление (осторожно!)
        //Files.deleteIfExists(newFile);
        //Files.delete(newDir); // удаляет только пустую директорию

        System.out.println();
    }

    // 4. Чтение и запись файлов
    private static void readWriteFiles() throws IOException {
        System.out.println("4. Чтение и запись через NIO.2:");

        Path testFile = Paths.get("nio_read_write.txt");

        // Запись байтов
        String content = "Строка 1\nСтрока 2\nСтрока 3\n";
        Files.write(testFile, content.getBytes());

        // Запись строк
        List<String> lines = List.of("Первая строка", "Вторая строка", "Третья строка");
        Path linesFile = Paths.get("nio_lines.txt");
        Files.write(linesFile, lines);

        // Чтение байтов
        byte[] bytes = Files.readAllBytes(testFile);
        System.out.println("Прочитано байтов: " + bytes.length);

        // Чтение строк
        List<String> readLines = Files.readAllLines(linesFile);
        System.out.println("Прочитано строк: " + readLines.size());
        readLines.forEach(line -> System.out.println("  " + line));
        // Чтение как строка (Java 11+)
        try {
            String fileContent = Files.readString(testFile);
            System.out.println("Содержимое файла:\n" + fileContent);
        } catch (Exception e) {
            System.out.println("readString() недоступен в данной версии Java");
        }

        System.out.println();
    }

    // 5. Работа с директориями
    private static void directoryOperations() throws IOException {
        System.out.println("5. Операции с директориями:");

        // Создаем тестовую структуру
        Path testDir = Paths.get("test_dir_ops");
        Files.createDirectories(testDir);
        Files.write(testDir.resolve("file1.txt"), "Содержимое 1".getBytes());
        Files.write(testDir.resolve("file2.txt"), "Содержимое 2".getBytes());
        Files.createDirectories(testDir.resolve("subdir"));

        // Перечисление содержимого директории
        System.out.println("Содержимое директории " + testDir + ":");
        try (Stream<Path> paths = Files.list(testDir)) {
            paths.forEach(path -> {
                System.out.println("  " + path.getFileName() +
                    (Files.isDirectory(path) ? " (директория)" : " (файл)"));
            });
        }

        // Обход дерева файлов
        System.out.println("\nОбход дерева файлов:");
        try (Stream<Path> paths = Files.walk(testDir)) {
            paths.forEach(path -> {
                String indent = "  ".repeat(path.getNameCount() - testDir.getNameCount());
                System.out.println(indent + path.getFileName());
            });
        }

        System.out.println();
    }

    // 6. Атрибуты файлов
    private static void fileAttributes() throws IOException {
        System.out.println("6. Атрибуты файлов:");

        Path testFile = Paths.get("nio_test.txt");

        if (Files.exists(testFile)) {
            BasicFileAttributes attrs = Files.readAttributes(testFile, BasicFileAttributes.class);
            System.out.println("Время создания: " + attrs.creationTime());
            System.out.println("Время последнего изменения: " + attrs.lastModifiedTime());
            System.out.println("Время последнего доступа: " + attrs.lastAccessTime());
            System.out.println("Размер: " + attrs.size() + " байт");
            System.out.println("Это директория: " + attrs.isDirectory());
            System.out.println("Это обычный файл: " + attrs.isRegularFile());
        }

        System.out.println();
    }
}