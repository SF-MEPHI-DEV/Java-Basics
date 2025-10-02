package ru.mephi.week6.lesson2.Examples;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Сравнение работы с потоками и без потоков</h2>
 * <p>
 * В этом примере сравниваются различные подходы к работе с файлами:
 * <ul>
 *     <li>Работа БЕЗ потоков (прямая работа с File, Files)</li>
 *     <li>Работа С потоками (InputStream/OutputStream)</li>
 *     <li>Сравнение производительности и удобства</li>
 * </ul>
 * </p>
 */
public class StreamComparisonExample {

    public static void main(String[] args) {
        System.out.println("=== Сравнение работы с потоками и без потоков ===\n");

        try {
            // 1. Чтение файла: с потоками vs без потоков
            compareReadingFile();

            // 2. Запись файла: с потоками vs без потоков
            compareWritingFile();

            // 3. Копирование файла: разные подходы
            compareCopyingFile();

            // 4. Работа с большими файлами: почему потоки эффективнее
            compareLargeFileProcessing();

            // 5. Построчная обработка: с потоками vs без потоков
            compareLineByLineProcessing();

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 1. Сравнение чтения файла
     */
    private static void compareReadingFile() throws IOException {
        System.out.println("1. Чтение файла: разные подходы\n");

        String filename = "test_read.txt";

        // Создаем тестовый файл
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Строка 1: Привет, мир!\n");
            writer.write("Строка 2: Это тестовый файл\n");
            writer.write("Строка 3: Для сравнения подходов\n");
        }

        // Подход 1: БЕЗ потоков
        System.out.println("   Подход 1: БЕЗ потоков (Files.readAllBytes)");
        long startTime = System.nanoTime();
        byte[] allBytes = Files.readAllBytes(Paths.get(filename));
        String content1 = new String(allBytes);
        long time1 = System.nanoTime() - startTime;
        System.out.println("   Прочитано: " + allBytes.length + " байт");
        System.out.printf("   Время: %.3f мс%n", time1 / 1_000_000.0);
        System.out.println("   Плюсы: просто, одна строка кода");
        System.out.println("   Минусы: весь файл загружается в память сразу");

        // Подход 2: С потоками - построчное чтение
        System.out.println("\n   Подход 2: С потоками (BufferedReader)");
        startTime = System.nanoTime();
        StringBuilder content2 = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content2.append(line).append("\n");
            }
        }
        long time2 = System.nanoTime() - startTime;
        System.out.println("   Прочитано: " + content2.length() + " символов");
        System.out.printf("   Время: %.3f мс%n", time2 / 1_000_000.0);
        System.out.println("   Плюсы: эффективная память, можно обрабатывать построчно");
        System.out.println("   Минусы: больше кода, нужно следить за закрытием");

        System.out.println("\n   Вывод: для маленьких файлов - Files удобнее, для больших - потоки\n");
    }

    /**
     * 2. Сравнение записи файла
     */
    private static void compareWritingFile() throws IOException {
        System.out.println("2. Запись в файл: разные подходы\n");

        String data = "Тестовые данные для записи\nВторая строка\nТретья строка\n";

        // Подход 1: БЕЗ потоков - через Files
        System.out.println("   Подход 1: БЕЗ потоков (Files.write)");
        String file1 = "write_test1.txt";
        long startTime = System.nanoTime();
        Files.write(Paths.get(file1), data.getBytes());
        long time1 = System.nanoTime() - startTime;
        System.out.printf("   Время: %.3f мс%n", time1 / 1_000_000.0);
        System.out.println("   Код: 1 строка");

        // Подход 2: С потоками - через BufferedWriter
        System.out.println("\n   Подход 2: С потоками (BufferedWriter)");
        String file2 = "write_test2.txt";
        startTime = System.nanoTime();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file2))) {
            writer.write(data);
        }
        long time2 = System.nanoTime() - startTime;
        System.out.printf("   Время: %.3f мс%n", time2 / 1_000_000.0);
        System.out.println("   Код: try-with-resources блок");

        System.out.println("\n   Вывод: для простой записи Files удобнее, для потоковой записи - Writer\n");
    }

    /**
     * 3. Сравнение копирования файла
     */
    private static void compareCopyingFile() throws IOException {
        System.out.println("3. Копирование файла: разные подходы\n");

        String sourceFile = "copy_source.txt";

        // Создаем исходный файл
        StringBuilder largeContent = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeContent.append("Строка номер ").append(i).append(" с тестовым содержимым\n");
        }
        Files.write(Paths.get(sourceFile), largeContent.toString().getBytes());

        long fileSize = new File(sourceFile).length();
        System.out.println("   Размер исходного файла: " + fileSize + " байт\n");

        // Подход 1: БЕЗ потоков - Files.copy
        System.out.println("   Подход 1: БЕЗ потоков (Files.copy)");
        String dest1 = "copy_dest1.txt";
        long startTime = System.nanoTime();
        Files.copy(Paths.get(sourceFile), Paths.get(dest1),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        long time1 = System.nanoTime() - startTime;
        System.out.printf("   Время: %.3f мс%n", time1 / 1_000_000.0);
        System.out.println("   Код: 1 строка");

        // Подход 2: С байтовыми потоками без буферизации
        System.out.println("\n   Подход 2: С потоками БЕЗ буферизации");
        String dest2 = "copy_dest2.txt";
        startTime = System.nanoTime();
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(dest2)) {
            int byteValue;
            while ((byteValue = fis.read()) != -1) {
                fos.write(byteValue);
            }
        }
        long time2 = System.nanoTime() - startTime;
        System.out.printf("   Время: %.3f мс%n", time2 / 1_000_000.0);
        System.out.println("   Код: цикл с чтением/записью по байту");

        // Подход 3: С байтовыми потоками С буферизацией
        System.out.println("\n   Подход 3: С потоками С буферизацией");
        String dest3 = "copy_dest3.txt";
        startTime = System.nanoTime();
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sourceFile));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest3))) {
            int byteValue;
            while ((byteValue = bis.read()) != -1) {
                bos.write(byteValue);
            }
        }
        long time3 = System.nanoTime() - startTime;
        System.out.printf("   Время: %.3f мс%n", time3 / 1_000_000.0);
        System.out.println("   Код: то же + буферизация");

        // Подход 4: С потоками и буфером массива
        System.out.println("\n   Подход 4: С потоками + массив буфера");
        String dest4 = "copy_dest4.txt";
        startTime = System.nanoTime();
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(dest4)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        long time4 = System.nanoTime() - startTime;
        System.out.printf("   Время: %.3f мс%n", time4 / 1_000_000.0);
        System.out.println("   Код: чтение/запись блоками");

        // Сравнение
        System.out.println("\n   Сравнение производительности:");
        System.out.printf("   Files.copy:              %.3f мс (базовая линия)%n", time1 / 1_000_000.0);
        System.out.printf("   Потоки без буфера:       %.3f мс (медленнее в %.1f раз)%n",
                time2 / 1_000_000.0, (double) time2 / time1);
        System.out.printf("   Потоки с буферизацией:   %.3f мс%n", time3 / 1_000_000.0);
        System.out.printf("   Потоки с буфером-массивом: %.3f мс%n", time4 / 1_000_000.0);

        System.out.println("\n   Вывод: Files.copy - самый простой, потоки с буфером - самые гибкие\n");
    }

    /**
     * 4. Работа с большими файлами
     */
    private static void compareLargeFileProcessing() throws IOException {
        System.out.println("4. Обработка больших файлов\n");

        String filename = "large_file.txt";

        // Создаем большой файл
        System.out.println("   Создание большого файла (10000 строк)...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < 10000; i++) {
                writer.write("Строка " + i + ": Длинное содержимое для тестирования производительности потоков\n");
            }
        }

        long fileSize = new File(filename).length();
        System.out.println("   Размер файла: " + (fileSize / 1024) + " KB\n");

        // Подход 1: БЕЗ потоков - загружаем весь файл
        System.out.println("   Подход 1: Загрузка ВСЕГО файла в память (Files.readAllLines)");
        long startTime = System.nanoTime();
        long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        List<String> allLines = Files.readAllLines(Paths.get(filename));
        int wordCount1 = 0;
        for (String line : allLines) {
            wordCount1 += line.split("\\s+").length;
        }

        long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long time1 = System.nanoTime() - startTime;

        System.out.println("   Подсчитано слов: " + wordCount1);
        System.out.printf("   Время: %.3f мс%n", time1 / 1_000_000.0);
        System.out.printf("   Память: ~%d KB%n", (memoryAfter - memoryBefore) / 1024);
        System.out.println("   Проблема: весь файл в памяти!");

        // Подход 2: С потоками - построчная обработка
        System.out.println("\n   Подход 2: Построчная обработка (BufferedReader)");
        startTime = System.nanoTime();
        memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        int wordCount2 = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wordCount2 += line.split("\\s+").length;
            }
        }

        memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long time2 = System.nanoTime() - startTime;

        System.out.println("   Подсчитано слов: " + wordCount2);
        System.out.printf("   Время: %.3f мс%n", time2 / 1_000_000.0);
        System.out.printf("   Память: ~%d KB%n", (memoryAfter - memoryBefore) / 1024);
        System.out.println("   Преимущество: в памяти только одна строка!");

        System.out.println("\n   Вывод: для больших файлов потоки ОБЯЗАТЕЛЬНЫ (экономия памяти)\n");
    }

    /**
     * 5. Построчная обработка
     */
    private static void compareLineByLineProcessing() throws IOException {
        System.out.println("5. Построчная обработка и фильтрация\n");

        String inputFile = "numbers.txt";
        String outputFile1 = "filtered1.txt";
        String outputFile2 = "filtered2.txt";

        // Создаем файл с числами
        System.out.println("   Создание файла с числами (1-100)...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            for (int i = 1; i <= 100; i++) {
                writer.write("Число: " + i + "\n");
            }
        }

        // Задача: выбрать только четные числа

        // Подход 1: БЕЗ потоков - читаем все, фильтруем, записываем
        System.out.println("\n   Подход 1: без потоков (загрузка всего файла)");
        long startTime = System.nanoTime();

        List<String> allLines = Files.readAllLines(Paths.get(inputFile));
        List<String> filtered = new ArrayList<>();
        for (String line : allLines) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                int num = Integer.parseInt(parts[1].trim());
                if (num % 2 == 0) {
                    filtered.add(line);
                }
            }
        }
        Files.write(Paths.get(outputFile1), filtered);

        long time1 = System.nanoTime() - startTime;
        System.out.println("   Отфильтровано: " + filtered.size() + " строк");
        System.out.printf("   Время: %.3f мс%n", time1 / 1_000_000.0);

        // Подход 2: С потоками - читаем построчно, сразу пишем
        System.out.println("\n   Подход 2: С потоками (построчная обработка)");
        startTime = System.nanoTime();

        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile2))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    int num = Integer.parseInt(parts[1].trim());
                    if (num % 2 == 0) {
                        writer.write(line);
                        writer.newLine();
                        count++;
                    }
                }
            }
        }

        long time2 = System.nanoTime() - startTime;
        System.out.println("   Отфильтровано: " + count + " строк");
        System.out.printf("   Время: %.3f мс%n", time2 / 1_000_000.0);
    }
}
