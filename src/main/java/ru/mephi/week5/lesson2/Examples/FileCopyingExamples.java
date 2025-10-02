package ru.mephi.week5.lesson2.Examples;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;

public class FileCopyingExamples {
    public static void main(String[] args) {
        System.out.println("=== Различные способы копирования файлов ===\n");

        try {
            // Создаем исходный файл для копирования
            createSourceFile();

            // 1. Копирование через потоки (старый способ)
            copyWithStreams();

            // 2. Копирование через BufferedStreams (более эффективно)
            copyWithBufferedStreams();

            // 3. Копирование через Files.copy() (самый простой)
            copyWithFilesCopy();

            // 4. Копирование через каналы (высокая производительность)
            copyWithChannels();

            // 5. Копирование директорий
            copyDirectory();

            // 6. Перемещение файлов
            moveFiles();

            // Сравнение производительности
            performanceComparison();

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // Создаем исходный файл
    private static void createSourceFile() throws IOException {
        try (PrintWriter writer = new PrintWriter("source.txt")) {
            for (int i = 1; i <= 1000; i++) {
                writer.println("Строка номер " + i + " с некоторым содержимым для тестирования");
            }
        }
        System.out.println("Создан исходный файл: source.txt");
    }

    // 1. Копирование через обычные потоки
    private static void copyWithStreams() throws IOException {
        System.out.println("\n1. Копирование через FileInputStream/FileOutputStream:");

        long startTime = System.currentTimeMillis();

        try (FileInputStream fis = new FileInputStream("source.txt");
             FileOutputStream fos = new FileOutputStream("copy_streams.txt")) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Файл скопирован за: " + (endTime - startTime) + " мс");
        printFileInfo("copy_streams.txt");
    }

    // 2. Копирование через буферизованные потоки
    private static void copyWithBufferedStreams() throws IOException {
        System.out.println("\n2. Копирование через BufferedInputStream/BufferedOutputStream:");

        long startTime = System.currentTimeMillis();

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("source.txt"));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy_buffered.txt"))) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Файл скопирован за: " + (endTime - startTime) + " мс");
        printFileInfo("copy_buffered.txt");
    }

    // 3. Копирование через Files.copy()
    private static void copyWithFilesCopy() throws IOException {
        System.out.println("\n3. Копирование через Files.copy():");

        Path source = Paths.get("source.txt");
        Path target = Paths.get("copy_files_api.txt");

        long startTime = System.currentTimeMillis();

        // Простое копирование
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        long endTime = System.currentTimeMillis();
        System.out.println("Файл скопирован за: " + (endTime - startTime) + " мс");
        printFileInfo("copy_files_api.txt");

        // Копирование с сохранением атрибутов
        Path targetWithAttrs = Paths.get("copy_with_attributes.txt");
        Files.copy(source, targetWithAttrs,
                   StandardCopyOption.REPLACE_EXISTING,
                   StandardCopyOption.COPY_ATTRIBUTES);
        System.out.println("Копирование с сохранением атрибутов завершено");
    }

    // 4. Копирование через каналы
    private static void copyWithChannels() throws IOException {
        System.out.println("\n4. Копирование через FileChannel:");

        long startTime = System.currentTimeMillis();

        try (FileChannel sourceChannel = FileChannel.open(Paths.get("source.txt"), StandardOpenOption.READ);
             FileChannel targetChannel = FileChannel.open(Paths.get("copy_channels.txt"),
                     StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {

            sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Файл скопирован за: " + (endTime - startTime) + " мс");
        printFileInfo("copy_channels.txt");
    }

    // 5. Копирование директорий
    private static void copyDirectory() throws IOException {
        System.out.println("\n5. Копирование директорий:");

        // Создаем исходную директорию со структурой
        Path sourceDir = Paths.get("source_directory");
        Files.createDirectories(sourceDir);
        Files.write(sourceDir.resolve("file1.txt"), "Содержимое файла 1".getBytes());
        Files.write(sourceDir.resolve("file2.txt"), "Содержимое файла 2".getBytes());

        Path subDir = sourceDir.resolve("subdirectory");
        Files.createDirectories(subDir);
        Files.write(subDir.resolve("nested_file.txt"), "Вложенный файл".getBytes());

        // Копируем директорию рекурсивно
        Path targetDir = Paths.get("copied_directory");
        copyDirectoryRecursively(sourceDir, targetDir);

        System.out.println("Директория скопирована: " + sourceDir + " -> " + targetDir);
        printDirectoryStructure(targetDir, 0);
    }

    // Рекурсивное копирование директории
    private static void copyDirectoryRecursively(Path source, Path target) throws IOException {
        Files.walk(source).forEach(sourcePath -> {
            try {
                Path targetPath = target.resolve(source.relativize(sourcePath));
                if (Files.isDirectory(sourcePath)) {
                    Files.createDirectories(targetPath);
                } else {
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                throw new RuntimeException("Ошибка при копировании: " + e.getMessage(), e);
            }
        });
    }

    // 6. Перемещение файлов
    private static void moveFiles() throws IOException {
        System.out.println("\n6. Перемещение файлов:");

        // Создаем файл для перемещения
        Path fileToMove = Paths.get("file_to_move.txt");
        Files.write(fileToMove, "Файл для перемещения".getBytes());

        // Перемещение файла
        Path movedFile = Paths.get("moved_file.txt");
        Files.move(fileToMove, movedFile, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Файл перемещен: " + fileToMove + " -> " + movedFile);
        System.out.println("Исходный файл существует: " + Files.exists(fileToMove));
        System.out.println("Перемещенный файл существует: " + Files.exists(movedFile));

        // Переименование файла (перемещение в той же директории)
        Path renamedFile = Paths.get("renamed_file.txt");
        Files.move(movedFile, renamedFile);
        System.out.println("Файл переименован: " + movedFile + " -> " + renamedFile);
    }

    // Сравнение производительности
    private static void performanceComparison() throws IOException {
        System.out.println("\n=== Сравнение производительности ===");

        // Создаем большой файл для тестирования
        Path largeFile = Paths.get("large_file.txt");
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(largeFile))) {
            for (int i = 0; i < 10000; i++) {
                writer.println("Это строка номер " + i + " с достаточным количеством содержимого для тестирования производительности различных методов копирования файлов в Java");
            }
        }

        long fileSize = Files.size(largeFile);
        System.out.println("Размер тестового файла: " + fileSize + " байт");

        // Тестируем разные методы
        testCopyMethod("Files.copy()", () -> {
            try {
                Files.copy(largeFile, Paths.get("perf_test_files_copy.txt"), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        testCopyMethod("FileChannel", () -> {
            try (FileChannel source = FileChannel.open(largeFile, StandardOpenOption.READ);
                 FileChannel target = FileChannel.open(Paths.get("perf_test_channel.txt"),
                         StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
                source.transferTo(0, source.size(), target);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        testCopyMethod("BufferedStreams", () -> {
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(largeFile.toFile()));
                 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("perf_test_buffered.txt"))) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Тестирование метода копирования
    private static void testCopyMethod(String methodName, Runnable copyMethod) {
        long startTime = System.nanoTime();
        copyMethod.run();
        long endTime = System.nanoTime();

        double timeMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("%-20s: %.2f мс%n", methodName, timeMs);
    }

    // Вывод информации о файле
    private static void printFileInfo(String filename) throws IOException {
        Path path = Paths.get(filename);
        System.out.println("  Размер: " + Files.size(path) + " байт");
        System.out.println("  Существует: " + Files.exists(path));
    }

    // Вывод структуры директории
    private static void printDirectoryStructure(Path directory, int depth) throws IOException {
        if (Files.exists(directory)) {
            String indent = "  ".repeat(depth);
            try (var paths = Files.list(directory)) {
                paths.forEach(path -> {
                    System.out.println(indent + "- " + path.getFileName() +
                        (Files.isDirectory(path) ? "/" : ""));
                    if (Files.isDirectory(path)) {
                        try {
                            printDirectoryStructure(path, depth + 1);
                        } catch (IOException e) {
                            System.err.println("Ошибка при чтении директории: " + e.getMessage());
                        }
                    }
                });
            }
        }
    }
}