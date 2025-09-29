package ru.mephi.week5.lesson2.Examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.stream.Stream;

public class DirectoryOperations {
    public static void main(String[] args) {
        System.out.println("=== Операции с директориями ===\n");

        try {
            // 1. Создание структуры директорий
            createDirectoryStructure();

            // 2. Перечисление файлов (старый способ - File)
            listFilesOldWay();

            // 3. Перечисление файлов (новый способ - NIO.2)
            listFilesNewWay();

            // 4. Рекурсивный обход директорий
            recursiveDirectoryTraversal();

            // 5. Поиск файлов
            findFiles();

            // 6. Удаление директорий
            deleteDirectories();

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // 1. Создание структуры директорий
    private static void createDirectoryStructure() throws IOException {
        System.out.println("1. Создание структуры директорий:");

        // Старый способ - File
        File oldDir = new File("old_style_dir");
        if (oldDir.mkdir()) {
            System.out.println("Директория создана: " + oldDir.getName());
        }

        File nestedOldDir = new File("old_parent/child");
        if (nestedOldDir.mkdirs()) { // mkdirs создает всю цепочку
            System.out.println("Цепочка директорий создана: " + nestedOldDir.getPath());
        }

        // Новый способ - NIO.2
        Path newDir = Paths.get("new_style_dir");
        Files.createDirectories(newDir);
        System.out.println("NIO директория создана: " + newDir);

        Path nestedNewDir = Paths.get("new_parent", "child", "grandchild");
        Files.createDirectories(nestedNewDir);
        System.out.println("NIO цепочка директорий создана: " + nestedNewDir);

        // Создаем файлы для тестирования
        createTestFiles();
        System.out.println();
    }

    // Создаем тестовые файлы
    private static void createTestFiles() throws IOException {
        Path testDir = Paths.get("test_directory");
        Files.createDirectories(testDir);

        Files.write(testDir.resolve("file1.txt"), "Содержимое файла 1".getBytes());
        Files.write(testDir.resolve("file2.java"), "public class Test {}".getBytes());
        Files.write(testDir.resolve("document.pdf"), "PDF содержимое".getBytes());

        Path subDir = testDir.resolve("subdirectory");
        Files.createDirectories(subDir);
        Files.write(subDir.resolve("nested_file.txt"), "Вложенный файл".getBytes());
    }

    // 2. Перечисление файлов старым способом
    private static void listFilesOldWay() {
        System.out.println("2. Перечисление файлов (File API):");

        File dir = new File("test_directory");
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                Arrays.stream(files).forEach(file -> {
                    String type = file.isDirectory() ? "DIR" : "FILE";
                    System.out.println("  [" + type + "] " + file.getName() + " (" + file.length() + " байт)");
                });
            }

            // Фильтрация файлов
            System.out.println("  Только .txt файлы:");
            File[] txtFiles = dir.listFiles((directory, name) -> name.endsWith(".txt"));
            if (txtFiles != null) {
                Arrays.stream(txtFiles).forEach(file ->
                    System.out.println("    " + file.getName())
                );
            }
        }
        System.out.println();
    }

    // 3. Перечисление файлов новым способом
    private static void listFilesNewWay() throws IOException {
        System.out.println("3. Перечисление файлов (NIO.2 API):");

        Path dir = Paths.get("test_directory");
        if (Files.exists(dir) && Files.isDirectory(dir)) {

            // Простое перечисление
            System.out.println("  Все файлы и директории:");
            try (Stream<Path> paths = Files.list(dir)) {
                paths.forEach(path -> {
                    try {
                        String type = Files.isDirectory(path) ? "DIR" : "FILE";
                        long size = Files.isRegularFile(path) ? Files.size(path) : 0;
                        System.out.println("    [" + type + "] " + path.getFileName() + " (" + size + " байт)");
                    } catch (IOException e) {
                        System.err.println("Ошибка при получении размера файла: " + e.getMessage());
                    }
                });
            }

            // Фильтрация
            System.out.println("  Только .txt файлы:");
            try (Stream<Path> paths = Files.list(dir)) {
                paths.filter(path -> path.toString().endsWith(".txt"))
                     .forEach(path -> System.out.println("    " + path.getFileName()));
            }
        }
        System.out.println();
    }

    // 4. Рекурсивный обход директорий
    private static void recursiveDirectoryTraversal() throws IOException {
        System.out.println("4. Рекурсивный обход директорий:");

        Path startDir = Paths.get("test_directory");
        if (Files.exists(startDir)) {

            // Обход с Files.walk()
            System.out.println("  Files.walk():");
            try (Stream<Path> paths = Files.walk(startDir)) {
                paths.forEach(path -> {
                    try {
                        int depth = path.getNameCount() - startDir.getNameCount();
                        String indent = "    " + "  ".repeat(depth);
                        String type = Files.isDirectory(path) ? "DIR" : "FILE";
                        System.out.println(indent + "[" + type + "] " + path.getFileName());
                    } catch (Exception e) {
                        System.err.println("Ошибка: " + e.getMessage());
                    }
                });
            }

            // Обход с Files.walkFileTree() (более гибкий)
            System.out.println("  Files.walkFileTree():");
            Files.walkFileTree(startDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    System.out.println("    Файл: " + file.getFileName() + " (" + attrs.size() + " байт)");
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    System.out.println("    Вход в директорию: " + dir.getFileName());
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        System.out.println();
    }

    // 5. Поиск файлов
    private static void findFiles() throws IOException {
        System.out.println("5. Поиск файлов:");

        Path startDir = Paths.get("test_directory");
        if (Files.exists(startDir)) {

            // Поиск по расширению
            System.out.println("  Поиск .txt файлов:");
            try (Stream<Path> paths = Files.find(startDir, Integer.MAX_VALUE,
                    (path, attrs) -> path.toString().endsWith(".txt"))) {
                paths.forEach(path -> System.out.println("    " + path));
            }

            // Поиск файлов больше определенного размера
            System.out.println("  Поиск файлов больше 10 байт:");
            try (Stream<Path> paths = Files.find(startDir, Integer.MAX_VALUE,
                    (path, attrs) -> attrs.isRegularFile() && attrs.size() > 10)) {
                paths.forEach(path -> {
                    try {
                        System.out.println("    " + path + " (" + Files.size(path) + " байт)");
                    } catch (IOException e) {
                        System.err.println("Ошибка: " + e.getMessage());
                    }
                });
            }

            // Поиск с использованием glob паттерна
            System.out.println("  Поиск с glob паттерном (*.java):");
            PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.java");
            try (Stream<Path> paths = Files.walk(startDir)) {
                paths.filter(path -> matcher.matches(path.getFileName()))
                     .forEach(path -> System.out.println("    " + path));
            }
        }
        System.out.println();
    }

    // 6. Удаление директорий
    private static void deleteDirectories() throws IOException {
        System.out.println("6. Удаление директорий:");

        // Удаление пустой директории
        Path emptyDir = Paths.get("empty_dir");
        Files.createDirectories(emptyDir);
        Files.delete(emptyDir);
        System.out.println("Пустая директория удалена");

        // Удаление директории с содержимым (осторожно!)
        Path dirToDelete = Paths.get("dir_to_delete");
        Files.createDirectories(dirToDelete);
        Files.write(dirToDelete.resolve("file.txt"), "тест".getBytes());

        System.out.println("Удаление директории с содержимым:");
        Files.walkFileTree(dirToDelete, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                System.out.println("  Удален файл: " + file.getFileName());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                System.out.println("  Удалена директория: " + dir.getFileName());
                return FileVisitResult.CONTINUE;
            }
        });

        System.out.println();
    }
}