package ru.mephi.week6.lesson2.Examples;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Примеры использования BufferedReader и BufferedWriter
 */
public class BufferedReaderWriterExample {

    /**
     * Пример 1: Разница между FileReader и BufferedReader
     */
    public static void example1_WithoutBuffer() throws IOException {
        System.out.println("=== Пример 1: FileReader БЕЗ буферизации ===\n");

        File file = new File("src/main/java/ru/mephi/week6/lesson2/Examples/test1.txt");
        try (FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8)) {
            fw.write("Line 1\n");
            fw.write("Line 2\n");
            fw.write("Line 3\n");
        }

        // Читаем БЕЗ буфера - каждый read() обращается к диску
        long start = System.nanoTime();
        try (FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            int ch;
            int count = 0;
            while ((ch = reader.read()) != -1) {
                count++;
            }
            System.out.println("Прочитано символов: " + count);
        }
        long end = System.nanoTime();
        System.out.println("Время без буфера: " + (end - start) / 1_000_000.0 + " мс\n");
    }

    /**
     * Пример 2: С BufferedReader - намного быстрее
     */
    public static void example2_WithBuffer() throws IOException {
        System.out.println("=== Пример 2: BufferedReader С буферизацией ===\n");

        File file = new File("src/main/java/ru/mephi/week6/lesson2/Examples/test1.txt");

        // Читаем С буфером - загружает большие блоки
        long start = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
            int ch;
            int count = 0;
            while ((ch = reader.read()) != -1) {
                count++;
            }
            System.out.println("Прочитано символов: " + count);
        }
        long end = System.nanoTime();
        System.out.println("Время с буфером: " + (end - start) / 1_000_000.0 + " мс\n");
    }

    /**
     * Пример 3: readLine() - главное преимущество BufferedReader
     */
    public static void example3_ReadLine() throws IOException {
        System.out.println("=== Пример 3: Чтение построчно с readLine() ===\n");

        File file = new File("src/main/java/ru/mephi/week6/lesson2/Examples/test2.txt");
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write("Первая строка");
            writer.newLine();
            writer.write("Вторая строка");
            writer.newLine();
            writer.write("Третья строка");
            writer.newLine();
        }

        // readLine() - читает целую строку до \n
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8))) {

            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Строка " + lineNumber + ": " + line);
                lineNumber++;
            }
        }
        System.out.println();
    }

    /**
     * Пример 4: BufferedWriter с newLine()
     */
    public static void example4_NewLine() throws IOException {
        System.out.println("=== Пример 4: BufferedWriter.newLine() ===\n");

        File file = new File("src/main/java/ru/mephi/week6/lesson2/Examples/test3.txt");

        // newLine() - платформонезависимый перевод строки
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {

            writer.write("Строка 1");
            writer.newLine();  // На Windows: \r\n, на Linux/Mac: \n
            writer.write("Строка 2");
            writer.newLine();
            writer.write("Строка 3");
            writer.newLine();

            System.out.println("Записано 3 строки с newLine()");
        }

        // Проверяем
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Прочитано: " + line);
            }
        }
        System.out.println();
    }

    /**
     * Пример 5: Размер буфера
     */
    public static void example5_BufferSize() throws IOException {
        System.out.println("=== Пример 5: Разные размеры буфера ===\n");

        File file = new File("src/main/java/ru/mephi/week6/lesson2/Examples/test4.txt");

        // Создаём файл побольше
        try (FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8)) {
            for (int i = 0; i < 1000; i++) {
                fw.write("Строка номер " + i + "\n");
            }
        }

        // Маленький буфер (16 байт)
        long start1 = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8), 16)) {
            while (reader.readLine() != null) {
            }
        }
        long end1 = System.nanoTime();
        System.out.println("Буфер 16 байт: " + (end1 - start1) / 1_000_000.0 + " мс");

        // Большой буфер (8192 байта - стандартный)
        long start2 = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8), 8192)) {
            while (reader.readLine() != null) {
            }
        }
        long end2 = System.nanoTime();
        System.out.println("Буфер 8192 байта: " + (end2 - start2) / 1_000_000.0 + " мс");

        // Огромный буфер (64KB)
        long start3 = System.nanoTime();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8), 65536)) {
            while (reader.readLine() != null) {
            }
        }
        long end3 = System.nanoTime();
        System.out.println("Буфер 65536 байт: " + (end3 - start3) / 1_000_000.0 + " мс\n");
    }

    /**
     * Пример 6: Копирование файла построчно
     */
    public static void example6_CopyFile() throws IOException {
        System.out.println("=== Пример 6: Копирование файла построчно ===\n");

        File source = new File("src/main/java/ru/mephi/week6/lesson2/Examples/source.txt");
        File dest = new File("src/main/java/ru/mephi/week6/lesson2/Examples/dest.txt");

        // Создаём исходный файл
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(source, StandardCharsets.UTF_8))) {
            for (int i = 1; i <= 5; i++) {
                writer.write("Строка " + i);
                writer.newLine();
            }
        }

        // Копируем построчно
        try (BufferedReader reader = new BufferedReader(
                new FileReader(source, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(
                     new FileWriter(dest, StandardCharsets.UTF_8))) {

            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                lineCount++;
            }

            System.out.println("Скопировано строк: " + lineCount);
        }

        // Проверяем
        System.out.println("\nСодержимое скопированного файла:");
        try (BufferedReader reader = new BufferedReader(
                new FileReader(dest, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        System.out.println();
    }

    /**
     * Пример 7: Чтение из консоли с BufferedReader
     */
    public static void example7_ConsoleInput() throws IOException {
        System.out.println("=== Пример 7: Чтение из консоли ===\n");

        // Имитируем консольный ввод через StringReader
        String input = "Привет\nМир\nJava\n";
        Reader stringReader = new StringReader(input);

        try (BufferedReader reader = new BufferedReader(stringReader)) {
            System.out.println("Читаем строки:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  > " + line);
            }
        }
        System.out.println();
    }

    /**
     * Пример 8: Обработка больших файлов
     */
    public static void example8_LargeFile() throws IOException {
        System.out.println("=== Пример 8: Обработка большого файла ===\n");

        File file = new File("src/main/java/ru/mephi/week6/lesson2/Examples/large.txt");

        // Создаём большой файл
        System.out.println("Создаём файл на 10000 строк...");
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {
            for (int i = 0; i < 10000; i++) {
                writer.write("Line " + i + ": Some data here");
                writer.newLine();
            }
        }

        // Читаем и считаем строки
        System.out.println("Читаем файл...");
        long start = System.nanoTime();

        int totalLines = 0;
        int totalChars = 0;
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalLines++;
                totalChars += line.length();
            }
        }

        long end = System.nanoTime();

        System.out.println("Всего строк: " + totalLines);
        System.out.println("Всего символов: " + totalChars);
        System.out.println("Время чтения: " + (end - start) / 1_000_000.0 + " мс");
        System.out.println("Размер файла: " + file.length() + " байт\n");
    }

    /**
     * Пример 9: Фильтрация строк при чтении
     */
    public static void example9_FilterLines() throws IOException {
        System.out.println("=== Пример 9: Фильтрация строк ===\n");

        File file = new File("src/main/java/ru/mephi/week6/lesson2/Examples/filter.txt");

        // Создаём файл с разными строками
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write("INFO: Application started");
            writer.newLine();
            writer.write("DEBUG: Loading config");
            writer.newLine();
            writer.write("ERROR: Connection failed");
            writer.newLine();
            writer.write("INFO: Retrying...");
            writer.newLine();
            writer.write("ERROR: Timeout");
            writer.newLine();
        }

        // Читаем и фильтруем только ERROR
        System.out.println("Только строки с ERROR:");
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ERROR")) {
                    System.out.println("  " + line);
                }
            }
        }
        System.out.println();
    }

    /**
     * Пример 10: flush() для BufferedWriter
     */
    public static void example10_Flush() throws IOException {
        System.out.println("=== Пример 10: Важность flush() ===\n");

        File file = new File("src/main/java/ru/mephi/week6/lesson2/Examples/flush_test.txt");

        BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8));

        // Пишем данные (остаются в буфере!)
        writer.write("Строка 1\n");
        writer.write("Строка 2\n");

        System.out.println("Данные записаны в BufferedWriter (в буфер)");
        System.out.println("Размер файла ДО flush: " + file.length() + " байт");

        // Сбрасываем буфер
        writer.flush();

        System.out.println("Вызван flush()");
        System.out.println("Размер файла ПОСЛЕ flush: " + file.length() + " байт");

        writer.write("Строка 3\n");
        System.out.println("Добавлена ещё одна строка (в буфер)");
        System.out.println("Размер файла: " + file.length() + " байт");

        // close() автоматически вызывает flush()
        writer.close();
        System.out.println("Вызван close() (автоматический flush)");
        System.out.println("Размер файла ПОСЛЕ close: " + file.length() + " байт\n");
    }

    public static void main(String[] args) throws IOException {
        example1_WithoutBuffer();
        example2_WithBuffer();
        example3_ReadLine();
        example4_NewLine();
        example5_BufferSize();
        example6_CopyFile();
        example7_ConsoleInput();
        example8_LargeFile();
        example9_FilterLines();
        example10_Flush();

        System.out.println("=".repeat(70));
        System.out.println("Все примеры выполнены!");
        System.out.println("=".repeat(70));
    }
}
