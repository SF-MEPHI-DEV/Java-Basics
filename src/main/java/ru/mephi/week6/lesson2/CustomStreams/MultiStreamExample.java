package ru.mephi.week6.lesson2.CustomStreams;


import ru.mephi.week6.lesson2.CustomStreams.MultiStream.MultiOutputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Примеры использования MultiOutputStream
 */
public class MultiStreamExample {

    /**
     * Пример 1: Запись в несколько файлов одновременно
     */
    public static void example1_MultipleFiles() throws IOException {
        System.out.println("=== Пример 1: Запись в несколько файлов ===\n");

        File file1 = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/output1.txt");
        File file2 = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/output2.txt");
        File file3 = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/output3.txt");

        // Создаём MultiOutputStream для записи в 3 файла одновременно
        try (MultiOutputStream multi = new MultiOutputStream(
                new FileOutputStream(file1),
                new FileOutputStream(file2),
                new FileOutputStream(file3)
        )) {
            String message = "Это сообщение записывается в 3 файла одновременно!\n";
            multi.write(message.getBytes(StandardCharsets.UTF_8));
            multi.flush();
        }

        System.out.println("Записано в файлы:");
        System.out.println("  " + file1.getName() + " (" + file1.length() + " байт)");
        System.out.println("  " + file2.getName() + " (" + file2.length() + " байт)");
        System.out.println("  " + file3.getName() + " (" + file3.length() + " байт)");
        System.out.println();
    }

    /**
     * Пример 2: Запись в файл и консоль одновременно
     */
    public static void example2_FileAndConsole() throws IOException {
        System.out.println("=== Пример 2: Запись в файл и консоль ===\n");

        File logFile = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/app.log");

        // Записываем в файл И на экран
        OutputStream[] streams = {
                new FileOutputStream(logFile),
                System.out
        };

        boolean[] closeFlags = {
                true,   // закрыть файл
                false   // НЕ закрывать System.out
        };

        try (MultiOutputStream multi = new MultiOutputStream(streams, closeFlags)) {
            multi.write("[INFO] Application started\n".getBytes());
            multi.write("[INFO] Loading configuration\n".getBytes());
            multi.write("[INFO] Ready to process requests\n".getBytes());
            multi.flush();
        }

        System.out.println("\nЛог также сохранён в файл: " + logFile.getName());
        System.out.println();
    }

    /**
     * Пример 3: Дублирование данных (основной файл + резервная копия)
     */
    public static void example3_Backup() throws IOException {
        System.out.println("=== Пример 3: Основной файл + резервная копия ===\n");

        File mainFile = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/data.txt");
        File backupFile = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/data_backup.txt");

        try (MultiOutputStream multi = new MultiOutputStream(
                new BufferedOutputStream(new FileOutputStream(mainFile)),
                new BufferedOutputStream(new FileOutputStream(backupFile))
        )) {
            for (int i = 1; i <= 10; i++) {
                String line = "Строка " + i + ": важные данные\n";
                multi.write(line.getBytes(StandardCharsets.UTF_8));
            }
            multi.flush();
        }

        System.out.println("Данные записаны в оба файла:");
        System.out.println("  Основной: " + mainFile.getName() + " (" + mainFile.length() + " байт)");
        System.out.println("  Резервная копия: " + backupFile.getName() + " (" + backupFile.length() + " байт)");
        System.out.println();
    }

    /**
     * Пример 4: Разделение логов по уровням (info, warning, error - всё в один файл)
     */
    public static void example4_LogLevels() throws IOException {
        System.out.println("=== Пример 4: Логирование с разделением ===\n");

        File allLogs = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/all.log");
        File errorLogs = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/errors.log");

        // Всё пишем в all.log, но ошибки дублируем в errors.log
        try (FileOutputStream allStream = new FileOutputStream(allLogs);
             FileOutputStream errorStream = new FileOutputStream(errorLogs)) {

            // Обычные сообщения - только в all.log
            allStream.write("[INFO] Application started\n".getBytes());
            allStream.write("[DEBUG] Loading config from file\n".getBytes());

            // Ошибки - в оба файла
            MultiOutputStream errorMulti = new MultiOutputStream(allStream, errorStream);
            errorMulti.write("[ERROR] Connection failed\n".getBytes());
            errorMulti.write("[ERROR] Retry timeout\n".getBytes());
            errorMulti.flush();

            // Опять обычное сообщение
            allStream.write("[INFO] Shutting down\n".getBytes());
            allStream.flush();
        }

        System.out.println("Логи записаны:");
        System.out.println("  Все логи: " + allLogs.getName() + " (" + allLogs.length() + " байт)");
        System.out.println("  Только ошибки: " + errorLogs.getName() + " (" + errorLogs.length() + " байт)");
        System.out.println();
    }

    /**
     * Пример 5: Запись данных с одновременным выводом в буфер памяти
     */
    public static void example5_FileAndMemory() throws IOException {
        System.out.println("=== Пример 5: Файл + память ===\n");

        File file = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/report.txt");
        ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();

        // Пишем в файл И в память
        try (MultiOutputStream multi = new MultiOutputStream(
                new FileOutputStream(file),
                memoryBuffer
        )) {
            multi.write("ОТЧЁТ\n".getBytes(StandardCharsets.UTF_8));
            multi.write("=====\n".getBytes(StandardCharsets.UTF_8));
            multi.write("Данные за месяц: 12345 записей\n".getBytes(StandardCharsets.UTF_8));
            multi.flush();
        }

        System.out.println("Данные записаны в файл: " + file.getName());
        System.out.println("Содержимое из памяти:\n");
        System.out.println(memoryBuffer.toString(StandardCharsets.UTF_8));
        System.out.println();
    }

    /**
     * Пример 6: Распределённая запись (несколько выходов с разными обработчиками)
     */
    public static void example6_WithDecorators() throws IOException {
        System.out.println("=== Пример 6: MultiOutputStream с декораторами ===\n");

        File file1 = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/plain.txt");
        File file2 = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/buffered.txt");

        // Создаём разные обработчики
        OutputStream plain = new FileOutputStream(file1);
        OutputStream buffered = new BufferedOutputStream(new FileOutputStream(file2));

        try (MultiOutputStream multi = new MultiOutputStream(plain, buffered)) {
            for (int i = 0; i < 100; i++) {
                String line = "Line " + i + "\n";
                multi.write(line.getBytes(StandardCharsets.UTF_8));
            }
            multi.flush();
        }

        System.out.println("Записано с разными декораторами:");
        System.out.println("  Plain: " + file1.length() + " байт");
        System.out.println("  Buffered: " + file2.length() + " байт");
        System.out.println();
    }

    /**
     * Пример 7: Реальный сценарий - сохранение конфигурации в несколько мест
     */
    public static void example7_Configuration() throws IOException {
        System.out.println("=== Пример 7: Сохранение конфигурации ===\n");

        File localConfig = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/config.local");
        File backupConfig = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/config.backup");
        File remoteConfig = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/MultiStream/config.remote");

        String config = """
                # Configuration file
                host=localhost
                port=8080
                timeout=30000
                max_connections=100
                """;

        try (MultiOutputStream multi = new MultiOutputStream(
                new FileOutputStream(localConfig),
                new FileOutputStream(backupConfig),
                new FileOutputStream(remoteConfig)
        )) {
            multi.write(config.getBytes(StandardCharsets.UTF_8));
            multi.flush();
        }

        System.out.println("Конфигурация сохранена в:");
        System.out.println("  Локально: " + localConfig.getName());
        System.out.println("  Резервная копия: " + backupConfig.getName());
        System.out.println("  Удалённый сервер: " + remoteConfig.getName());
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        example1_MultipleFiles();
        example2_FileAndConsole();
        example3_Backup();
        example4_LogLevels();
        example5_FileAndMemory();
        example6_WithDecorators();
        example7_Configuration();

        System.out.println("=".repeat(70));
        System.out.println("Все примеры выполнены!");
        System.out.println("=".repeat(70));
    }
}
