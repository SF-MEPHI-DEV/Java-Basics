package ru.mephi.week6.lesson2.CustomStreams;

import ru.mephi.week6.lesson2.CustomStreams.Logging.LoggingReader;
import ru.mephi.week6.lesson2.CustomStreams.Logging.LoggingWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Демонстрация LoggingReader и LoggingWriter
 */
public class ReaderWriterLoggingDemo {

    /**
     * Пример 1: Базовое логирование чтения символов
     */
    public static void example1_BasicReaderLogging() throws IOException {
        System.out.println("\n=== Пример 1: Базовое логирование чтения ===\n");

        String text = "Hello, World!";
        Reader baseReader = new StringReader(text);

        try (LoggingReader reader = new LoggingReader(baseReader, "Example1")) {
            char[] buffer = new char[5];
            int read = reader.read(buffer);
            System.out.println("\nПрочитано из буфера: \"" + new String(buffer, 0, read) + "\"");
        }
    }

    /**
     * Пример 2: Базовое логирование записи символов
     */
    public static void example2_BasicWriterLogging() throws IOException {
        System.out.println("\n=== Пример 2: Базовое логирование записи ===\n");

        StringWriter stringWriter = new StringWriter();

        try (LoggingWriter writer = new LoggingWriter(stringWriter, "Example2")) {
            writer.write("Привет, ");
            writer.write("Java!");
            writer.flush();
        }

        System.out.println("\nРезультат в StringWriter: \"" + stringWriter.toString() + "\"");
    }

    /**
     * Пример 3: Чтение файла с логированием
     */
    public static void example3_FileReaderLogging() throws IOException {
        System.out.println("\n=== Пример 3: Чтение файла с логированием ===\n");

        // Создаём тестовый файл
        File testFile = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/test_read.txt");
        try (FileWriter fw = new FileWriter(testFile)) {
            fw.write("Строка 1\n");
            fw.write("Строка 2\n");
            fw.write("Строка 3\n");
        }

        // Читаем с логированием
        try (LoggingReader reader = new LoggingReader(
                new FileReader(testFile, StandardCharsets.UTF_8),
                "FileReader")) {

            char[] buffer = new char[20];
            int charsRead;

            System.out.println("--- Начинаем чтение ---\n");
            while ((charsRead = reader.read(buffer)) != -1) {
                String content = new String(buffer, 0, charsRead);
                System.out.println("Приложение получило: \"" + content.replace("\n", "\\n") + "\"\n");
            }
        }
    }

    /**
     * Пример 4: Запись в файл с логированием
     */
    public static void example4_FileWriterLogging() throws IOException {
        System.out.println("\n=== Пример 4: Запись в файл с логированием ===\n");

        File outputFile = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/test_write.txt");

        try (LoggingWriter writer = new LoggingWriter(
                new FileWriter(outputFile, StandardCharsets.UTF_8),
                "FileWriter")) {

            System.out.println("--- Начинаем запись ---\n");
            writer.write("Первая строка\n");
            writer.write("Вторая строка\n");
            writer.write("Третья строка\n");
            writer.flush();
        }

        System.out.println("\n--- Проверяем результат ---");
        try (BufferedReader br = new BufferedReader(new FileReader(outputFile, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Файл содержит: " + line);
            }
        }
    }

    /**
     * Пример 5: Комбинация LoggingReader с BufferedReader
     */
    public static void example5_BufferedWithLogging() throws IOException {
        System.out.println("\n=== Пример 5: BufferedReader + LoggingReader ===\n");

        String text = "Line 1\nLine 2\nLine 3\n";

        // Матрёшка: BufferedReader -> LoggingReader -> StringReader
        try (BufferedReader buffered = new BufferedReader(
                new LoggingReader(
                        new StringReader(text),
                        "Buffered"
                ), 16)) {

            System.out.println("--- Читаем построчно ---\n");
            String line;
            while ((line = buffered.readLine()) != null) {
                System.out.println("Приложение получило строку: \"" + line + "\"\n");
            }
        }
    }

    /**
     * Пример 6: Комбинация LoggingWriter с BufferedWriter
     */
    public static void example6_BufferedWriterWithLogging() throws IOException {
        System.out.println("\n=== Пример 6: BufferedWriter + LoggingWriter ===\n");

        StringWriter stringWriter = new StringWriter();

        // Матрёшка: BufferedWriter -> LoggingWriter -> StringWriter
        try (BufferedWriter buffered = new BufferedWriter(
                new LoggingWriter(stringWriter, "Buffered"),
                16)) {

            System.out.println("--- Записываем построчно ---\n");
            buffered.write("First line");
            buffered.newLine();
            buffered.write("Second line");
            buffered.newLine();
            buffered.write("Third line");
            buffered.newLine();

            System.out.println("\n--- Вызываем flush ---\n");
            buffered.flush();
        }

        System.out.println("\nРезультат: \"" + stringWriter.toString().replace("\n", "\\n") + "\"");
    }

    /**
     * Пример 7: Копирование файла с логированием чтения и записи
     */
    public static void example7_FileCopyWithLogging() throws IOException {
        System.out.println("\n=== Пример 7: Копирование файла с полным логированием ===\n");

        File source = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/source.txt");
        File dest = new File("src/main/java/ru/mephi/week6/lesson2/CustomStreams/dest.txt");

        // Создаём исходный файл
        try (FileWriter fw = new FileWriter(source, StandardCharsets.UTF_8)) {
            fw.write("Данные для копирования\n");
            fw.write("Вторая строка\n");
        }

        // Копируем с логированием
        try (LoggingReader reader = new LoggingReader(
                new BufferedReader(new FileReader(source, StandardCharsets.UTF_8)),
                "SourceReader");
             LoggingWriter writer = new LoggingWriter(
                     new BufferedWriter(new FileWriter(dest, StandardCharsets.UTF_8)),
                     "DestWriter")) {

            System.out.println("--- Начинаем копирование ---\n");

            char[] buffer = new char[16];
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, charsRead);
            }

            System.out.println("\n--- Завершаем копирование ---\n");
            writer.flush();
        }

        System.out.println("\nКопирование завершено!");
    }

    /**
     * Пример 8: Логирование посимвольного чтения
     */
    public static void example8_CharByCharLogging() throws IOException {
        System.out.println("\n=== Пример 8: Посимвольное чтение с логированием ===\n");

        String text = "ABC123\n";
        try (LoggingReader reader = new LoggingReader(new StringReader(text), "CharByChar")) {

            System.out.println("--- Читаем по одному символу ---\n");
            int ch;
            while ((ch = reader.read()) != -1) {
                char character = (char) ch;
                System.out.println("Приложение обработало: '" + (character == '\n' ? "\\n" : character) + "'\n");
            }
        }
    }

    /**
     * Пример 9: Логирование работы с длинным текстом
     */
    public static void example9_LongTextLogging() throws IOException {
        System.out.println("\n=== Пример 9: Работа с длинным текстом ===\n");

        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            longText.append("Строка номер ").append(i).append("\n");
        }

        StringWriter output = new StringWriter();

        try (LoggingWriter writer = new LoggingWriter(
                new BufferedWriter(output, 128),
                "LongText")) {

            System.out.println("--- Записываем длинный текст ---\n");
            writer.write(longText.toString());
            writer.flush();
        }

        System.out.println("\nВсего записано символов: " + output.toString().length());
    }

    public static void main(String[] args) throws IOException {
        example1_BasicReaderLogging();
        example2_BasicWriterLogging();
        example3_FileReaderLogging();
        example4_FileWriterLogging();
        example5_BufferedWithLogging();
        example6_BufferedWriterWithLogging();
        example7_FileCopyWithLogging();
        example8_CharByCharLogging();
        example9_LongTextLogging();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("Все примеры выполнены!");
        System.out.println("=".repeat(70));
    }
}
