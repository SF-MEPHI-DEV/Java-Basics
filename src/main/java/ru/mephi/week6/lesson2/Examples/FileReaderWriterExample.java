package ru.mephi.week6.lesson2.Examples;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Подробные примеры работы с FileReader и FileWriter
 * FileReader/FileWriter - классы для чтения/записи текстовых файлов
 */
public class FileReaderWriterExample {

    private static final String EXAMPLE_DIR = "src/main/java/ru/mephi/week6/lesson3/Examples/files/";

    /**
     * Создаём директорию для примеров
     */
    private static void ensureDirectoryExists() {
        File dir = new File(EXAMPLE_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Пример 1: Базовая запись в файл через FileWriter
     */
    public static void exampleBasicFileWriter() {
        System.out.println("=== Пример 1: Базовая запись через FileWriter ===");
        ensureDirectoryExists();

        String fileName = EXAMPLE_DIR + "example1.txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Первая строка текста\n");
            writer.write("Вторая строка текста\n");
            writer.write("Третья строка текста");

            // flush() принудительно сбрасывает буфер на диск
            writer.flush();

            System.out.println("Данные записаны в файл: " + fileName);

        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * Пример 2: Базовое чтение из файла через FileReader
     */
    public static void exampleBasicFileReader() {
        System.out.println("=== Пример 2: Базовое чтение через FileReader ===");

        String fileName = EXAMPLE_DIR + "example1.txt";

        // Посимвольное чтение (неэффективно для больших файлов!)
        try (FileReader reader = new FileReader(fileName)) {
            int charCode;
            System.out.println("Содержимое файла:");

            while ((charCode = reader.read()) != -1) {
                System.out.print((char) charCode);
            }
            System.out.println("\n");

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка чтения: " + e.getMessage());
        }
    }

    /**
     * Пример 3: Чтение файла блоками (более эффективно)
     */
    public static void exampleFileReaderWithBuffer() {
        System.out.println("=== Пример 3: Чтение файла блоками ===");

        String fileName = EXAMPLE_DIR + "example1.txt";

        try (FileReader reader = new FileReader(fileName)) {
            char[] buffer = new char[128]; // Буфер 128 символов
            int charsRead;
            int totalChars = 0;

            System.out.println("Читаем блоками:");
            while ((charsRead = reader.read(buffer)) != -1) {
                totalChars += charsRead;
                System.out.print(new String(buffer, 0, charsRead));
            }

            System.out.println("\nВсего прочитано символов: " + totalChars);
            System.out.println();

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Пример 4: Дозапись в файл (append mode)
     */
    public static void exampleAppendMode() {
        System.out.println("=== Пример 4: Дозапись в файл (append) ===");
        ensureDirectoryExists();

        String fileName = EXAMPLE_DIR + "example2.txt";

        // Первая запись
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Исходное содержимое файла\n");
            System.out.println("Создан файл с исходным содержимым");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Дозапись (append = true)
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Дописанная строка 1\n");
            writer.write("Дописанная строка 2\n");
            System.out.println("Данные дописаны в конец файла");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Чтение результата
        System.out.println("\nИтоговое содержимое:");
        try (FileReader reader = new FileReader(fileName)) {
            char[] buffer = new char[256];
            int read = reader.read(buffer);
            System.out.println(new String(buffer, 0, read));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Пример 5: Указание кодировки (ВАЖНО!)
     * FileReader использует системную кодировку по умолчанию - это опасно!
     * Лучше использовать InputStreamReader/OutputStreamWriter с явной кодировкой
     */
    public static void exampleEncodingIssues() {
        System.out.println("=== Пример 5: Проблема кодировок ===");
        ensureDirectoryExists();

        String fileName = EXAMPLE_DIR + "example3.txt";
        String russianText = "Привет! Текст на русском языке.";

        // ПЛОХО: FileWriter использует системную кодировку
        System.out.println("FileWriter (системная кодировка):");
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(russianText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ХОРОШО: OutputStreamWriter с явной кодировкой UTF-8
        String fileNameUtf8 = EXAMPLE_DIR + "example3_utf8.txt";
        System.out.println("OutputStreamWriter (UTF-8):");

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(fileNameUtf8),
                StandardCharsets.UTF_8)) {
            writer.write(russianText);
            System.out.println("Записано в UTF-8: " + fileNameUtf8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Чтение с явной кодировкой
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(fileNameUtf8),
                StandardCharsets.UTF_8)) {
            char[] buffer = new char[256];
            int read = reader.read(buffer);
            System.out.println("Прочитано из UTF-8: " + new String(buffer, 0, read));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    /**
     * Пример 6: Копирование текстового файла
     */
    public static void exampleFileCopy() {
        System.out.println("=== Пример 6: Копирование файла ===");
        ensureDirectoryExists();

        String sourceFile = EXAMPLE_DIR + "source.txt";
        String destFile = EXAMPLE_DIR + "destination.txt";

        // Создаём исходный файл
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write("Строка 1: Lorem ipsum dolor sit amet\n");
            writer.write("Строка 2: consectetur adipiscing elit\n");
            writer.write("Строка 3: sed do eiusmod tempor incididunt\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Копируем
        try (FileReader reader = new FileReader(sourceFile);
             FileWriter writer = new FileWriter(destFile)) {

            char[] buffer = new char[64];
            int charsRead;

            while ((charsRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, charsRead);
            }

            System.out.println("Файл скопирован: " + sourceFile + " -> " + destFile);

        } catch (IOException e) {
            System.err.println("Ошибка копирования: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * Пример 7: Обработка больших файлов блоками
     */
    public static void exampleLargeFileProcessing() {
        System.out.println("=== Пример 7: Обработка большого файла ===");
        ensureDirectoryExists();

        String fileName = EXAMPLE_DIR + "large_file.txt";

        // Создаём "большой" файл
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int i = 0; i < 1000; i++) {
                writer.write("Строка номер " + i + ": данные данные данные\n");
            }
            System.out.println("Создан файл с 1000 строками");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Читаем блоками и считаем символы
        long totalChars = 0;
        int bufferSize = 512;

        try (FileReader reader = new FileReader(fileName)) {
            char[] buffer = new char[bufferSize];
            int charsRead;

            while ((charsRead = reader.read(buffer)) != -1) {
                totalChars += charsRead;
            }

            System.out.println("Всего символов в файле: " + totalChars);
            System.out.println("Размер буфера: " + bufferSize);
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Пример 8: Правильная обработка исключений и закрытие ресурсов
     */
    public static void exampleProperExceptionHandling() {
        System.out.println("=== Пример 8: Обработка исключений ===");
        ensureDirectoryExists();

        String fileName = EXAMPLE_DIR + "example_exceptions.txt";

        // Вариант 1: try-with-resources (РЕКОМЕНДУЕТСЯ!)
        System.out.println("Вариант 1: try-with-resources");
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Автоматическое закрытие ресурса");
            // writer.close() вызовется автоматически
            System.out.println("✓ Ресурс закроется автоматически");
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        // Вариант 2: Старый способ с finally (НЕ РЕКОМЕНДУЕТСЯ)
        System.out.println("\nВариант 2: try-finally (устаревший)");
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, true);
            writer.write("\nРучное закрытие в finally");
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    System.out.println("✓ Ресурс закрыт в finally");
                } catch (IOException e) {
                    System.err.println("Ошибка при закрытии: " + e.getMessage());
                }
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        exampleBasicFileWriter();
        exampleBasicFileReader();
        exampleFileReaderWithBuffer();
        exampleAppendMode();
        exampleEncodingIssues();
        exampleFileCopy();
        exampleLargeFileProcessing();
        exampleProperExceptionHandling();

        System.out.println("Все примеры выполнены! Проверьте директорию: " + EXAMPLE_DIR);
    }
}
