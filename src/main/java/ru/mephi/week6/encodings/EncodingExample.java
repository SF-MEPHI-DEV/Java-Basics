package ru.mephi.week6.encodings;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * <h2>Работа с кодировками символов</h2>
 * <p>
 * Кодировка - это способ представления символов в виде байтов.
 * Разные кодировки используют разное количество байтов для представления символов.
 * </p>
 * <p>
 * Основные кодировки:
 * <ul>
 *     <li>UTF-8 - универсальная, поддерживает все языки (1-4 байта на символ)</li>
 *     <li>UTF-16 - используется внутри Java (2 или 4 байта на символ)</li>
 *     <li>Windows-1251 - для кириллицы в Windows (1 байт на символ)</li>
 *     <li>ISO-8859-1 (Latin-1) - западноевропейские языки (1 байт на символ)</li>
 *     <li>US-ASCII - только английские буквы и цифры (1 байт на символ)</li>
 * </ul>
 * </p>
 */
public class EncodingExample {

    public static void main(String[] args) {
        System.out.println("=== Работа с кодировками символов ===\n");

        try {
            // 1. Какая кодировка используется по умолчанию?
            demonstrateDefaultEncoding();

            // 2. Сравнение размера текста в разных кодировках
            compareEncodingSizes();

            // 3. Проблема "кракозябры" - неправильная кодировка
            demonstrateEncodingProblems();

            // 4. Правильная работа с кодировками
            demonstrateCorrectEncoding();

            // 5. Конвертация между кодировками
            demonstrateEncodingConversion();

            // 6. Ограничения кодировок
            demonstrateEncodingLimitations();

            // 7. Определение кодировки файла
            demonstrateEncodingDetection();

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 1. Какая кодировка используется по умолчанию?
     */
    private static void demonstrateDefaultEncoding() {
        System.out.println("1. Кодировка по умолчанию в системе\n");

        // Кодировка по умолчанию в системе
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("   Системная кодировка: " + defaultCharset);
        System.out.println("   Имя: " + defaultCharset.displayName());

        // Все доступные кодировки
        System.out.println("\n   Популярные доступные кодировки:");
        String[] popularEncodings = {"UTF-8", "UTF-16", "Windows-1251", "ISO-8859-1", "US-ASCII"};
        for (String encoding : popularEncodings) {
            if (Charset.isSupported(encoding)) {
                System.out.println("     ✓ " + encoding);
            } else {
                System.out.println("     ✗ " + encoding + " (не поддерживается)");
            }
        }

        System.out.println("\n   Важно: кодировка по умолчанию зависит от ОС!");
        System.out.println("   Windows (русская): обычно Windows-1251");
        System.out.println("   Linux/Mac: обычно UTF-8");
        System.out.println("   Рекомендация: указывайте кодировку явно\n");
    }

    /**
     * 2. Сравнение размера текста в разных кодировках
     */
    private static void compareEncodingSizes() throws IOException {
        System.out.println("2. Сравнение размера текста в разных кодировках\n");

        String text = "Привет, мир! Hello, world! 你好世界";
        System.out.println("   Исходный текст: \"" + text + "\"");
        System.out.println("   Количество символов: " + text.length());

        // UTF-8
        byte[] utf8Bytes = text.getBytes(StandardCharsets.UTF_8);
        System.out.println("\n   UTF-8:");
        System.out.println("     Размер: " + utf8Bytes.length + " байт");
        System.out.println("     Особенности: 1-4 байта на символ");
        System.out.println("     - ASCII символы: 1 байт");
        System.out.println("     - Кириллица: 2 байта");
        System.out.println("     - Китайские иероглифы: 3-4 байта");

        // UTF-16
        byte[] utf16Bytes = text.getBytes(StandardCharsets.UTF_16);
        System.out.println("\n   UTF-16:");
        System.out.println("     Размер: " + utf16Bytes.length + " байт");
        System.out.println("     Особенности: 2 или 4 байта на символ");
        System.out.println("     + 2 байта BOM (Byte Order Mark) в начале");

        // ISO-8859-1 (Latin-1) - только для латиницы
        String latinText = "Hello, world!";
        byte[] latin1Bytes = latinText.getBytes(StandardCharsets.ISO_8859_1);
        System.out.println("\n   ISO-8859-1 (Latin-1):");
        System.out.println("     Текст: \"" + latinText + "\"");
        System.out.println("     Размер: " + latin1Bytes.length + " байт");
        System.out.println("     Особенности: только 1 байт на символ");
        System.out.println("     Ограничение: НЕ поддерживает кириллицу и китайские символы!");

        System.out.println("\n   Вывод: UTF-8 - универсальная, компактная для ASCII");
        System.out.println("   UTF-16 - используется внутри Java (String хранится в UTF-16)\n");
    }

    /**
     * 3. Проблема "кракозябры" - что происходит при неправильной кодировке
     */
    private static void demonstrateEncodingProblems() throws IOException {
        System.out.println("3. Проблема \"кракозябры\" - неправильная кодировка\n");

        String text = "Привет, мир!";
        String filename = "encoding_test.txt";

        // Записываем в UTF-8
        System.out.println("   Исходный текст: \"" + text + "\"");
        System.out.println("   Записываем в UTF-8...");
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filename), StandardCharsets.UTF_8)) {
            writer.write(text);
        }

        // Читаем как UTF-8 (правильно)
        System.out.println("\n   Читаем как UTF-8 (правильно):");
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename), StandardCharsets.UTF_8)) {
            char[] buffer = new char[100];
            int charsRead = reader.read(buffer);
            String result = new String(buffer, 0, charsRead);
            System.out.println("     Результат: \"" + result + "\" ✓");
        }

        // Читаем как ISO-8859-1 (неправильно)
        System.out.println("\n   Читаем как ISO-8859-1 (неправильно):");
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename), StandardCharsets.ISO_8859_1)) {
            char[] buffer = new char[100];
            int charsRead = reader.read(buffer);
            String result = new String(buffer, 0, charsRead);
            System.out.println("     Результат: \"" + result + "\" ✗ Кракозябры!");
        }

        // Читаем как Windows-1251 (неправильно)
        if (Charset.isSupported("Windows-1251")) {
            System.out.println("\n   Читаем как Windows-1251 (неправильно):");
            try (InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename), Charset.forName("Windows-1251"))) {
                char[] buffer = new char[100];
                int charsRead = reader.read(buffer);
                String result = new String(buffer, 0, charsRead);
                System.out.println("     Результат: \"" + result + "\" ✗ Кракозябры!");
            }
        }

        System.out.println("\n   Причина: каждая кодировка интерпретирует байты по-своему");
        System.out.println("   Решение: ВСЕГДА используйте ту же кодировку для чтения и записи!\n");
    }

    /**
     * 4. Правильная работа с кодировками
     */
    private static void demonstrateCorrectEncoding() throws IOException {
        System.out.println("4. Правильная работа с кодировками\n");

        String text = "Текст с кириллицей: Привет! English: Hello! Цифры: 123";

        System.out.println("   Исходный текст: \"" + text + "\"");

        // Правильный способ 1: OutputStreamWriter с явной кодировкой
        String file1 = "correct_utf8.txt";
        System.out.println("\n   Способ 1: OutputStreamWriter + явная кодировка");
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(file1), StandardCharsets.UTF_8)) {
            writer.write(text);
            System.out.println("   ✓ Записано в UTF-8");
        }

        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(file1), StandardCharsets.UTF_8)) {
            char[] buffer = new char[200];
            int charsRead = reader.read(buffer);
            String result = new String(buffer, 0, charsRead);
            System.out.println("   ✓ Прочитано: \"" + result + "\"");
        }

        // Правильный способ 2: Files.write/readString с кодировкой (Java 11+)
        String file2 = "correct_utf8_v2.txt";
        System.out.println("\n   Способ 2: Files.write/readString + кодировка (Java 11+)");
        Files.write(Paths.get(file2), text.getBytes(StandardCharsets.UTF_8));
        System.out.println("   ✓ Записано в UTF-8");

        String readText = new String(Files.readAllBytes(Paths.get(file2)), StandardCharsets.UTF_8);
        System.out.println("   ✓ Прочитано: \"" + readText + "\"");

        // Правильный способ 3: BufferedWriter с кодировкой
        String file3 = "correct_utf8_v3.txt";
        System.out.println("\n   Способ 3: BufferedWriter с кодировкой (рекомендуется)");
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file3), StandardCharsets.UTF_8))) {
            writer.write(text);
            System.out.println("   ✓ Записано в UTF-8 с буферизацией");
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file3), StandardCharsets.UTF_8))) {
            String result = reader.readLine();
            System.out.println("   ✓ Прочитано: \"" + result + "\"");
        }

        System.out.println("\n   Вывод: ВСЕГДА указывайте StandardCharsets.UTF_8 явно!\n");
    }

    /**
     * 5. Конвертация между кодировками
     */
    private static void demonstrateEncodingConversion() throws IOException {
        System.out.println("5. Конвертация текста между кодировками\n");

        String text = "Привет, мир!";
        String fileWindows1251 = "windows1251.txt";
        String fileUtf8 = "utf8_converted.txt";

        // Проверяем поддержку Windows-1251
        if (!Charset.isSupported("Windows-1251")) {
            System.out.println("   Windows-1251 не поддерживается в системе");
            System.out.println("   Пропускаем пример конвертации\n");
            return;
        }

        Charset windows1251 = Charset.forName("Windows-1251");

        // Записываем в Windows-1251
        System.out.println("   Исходный текст: \"" + text + "\"");
        System.out.println("   Шаг 1: Записываем в Windows-1251");
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(fileWindows1251), windows1251)) {
            writer.write(text);
        }
        long size1 = new File(fileWindows1251).length();
        System.out.println("   ✓ Размер файла: " + size1 + " байт");

        // Конвертируем в UTF-8
        System.out.println("\n   Шаг 2: Конвертируем Windows-1251 → UTF-8");
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(fileWindows1251), windows1251);
             OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileUtf8), StandardCharsets.UTF_8)) {
            char[] buffer = new char[1024];
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, charsRead);
            }
        }
        long size2 = new File(fileUtf8).length();
        System.out.println("   ✓ Размер файла: " + size2 + " байт");

        // Проверяем результат
        System.out.println("\n   Шаг 3: Проверяем результат конвертации");
        String convertedText = new String(
                Files.readAllBytes(Paths.get(fileUtf8)), StandardCharsets.UTF_8);
        System.out.println("   Результат: \"" + convertedText + "\"");

        if (text.equals(convertedText)) {
            System.out.println("   ✓ Конвертация успешна!");
        } else {
            System.out.println("   ✗ Ошибка конвертации");
        }

        System.out.println("\n   Разница в размере: Windows-1251 (" + size1 + " байт) vs UTF-8 (" + size2 + " байт)");
        System.out.println("   Windows-1251 использует 1 байт на кириллический символ");
        System.out.println("   UTF-8 использует 2 байта на кириллический символ\n");
    }

    /**
     * 6. Ограничения разных кодировок
     */
    private static void demonstrateEncodingLimitations() {
        System.out.println("6. Ограничения кодировок\n");

        // Разные тестовые строки
        String russian = "Привет";
        String english = "Hello";
        String chinese = "你好";
        String emoji = "😀🎉";
        String mixed = "Hello Привет 你好 😀";

        System.out.println("   Тестируем поддержку разных символов:\n");

        // ASCII
        System.out.println("   US-ASCII (только английские буквы и цифры):");
        testEncoding("US-ASCII", english, true);
        testEncoding("US-ASCII", russian, false);
        testEncoding("US-ASCII", chinese, false);
        testEncoding("US-ASCII", emoji, false);

        // ISO-8859-1
        System.out.println("\n   ISO-8859-1 (Latin-1, западноевропейские языки):");
        testEncoding("ISO-8859-1", english, true);
        testEncoding("ISO-8859-1", russian, false);
        testEncoding("ISO-8859-1", chinese, false);
        testEncoding("ISO-8859-1", emoji, false);

        // Windows-1251
        if (Charset.isSupported("Windows-1251")) {
            System.out.println("\n   Windows-1251 (кириллица):");
            testEncoding("Windows-1251", english, true);
            testEncoding("Windows-1251", russian, true);
            testEncoding("Windows-1251", chinese, false);
            testEncoding("Windows-1251", emoji, false);
        }

        // UTF-8
        System.out.println("\n   UTF-8 (универсальная):");
        testEncoding("UTF-8", english, true);
        testEncoding("UTF-8", russian, true);
        testEncoding("UTF-8", chinese, true);
        testEncoding("UTF-8", emoji, true);
        testEncoding("UTF-8", mixed, true);

        System.out.println("\n   Вывод:");
        System.out.println("   ✓ UTF-8 - единственная универсальная кодировка");
        System.out.println("   ✓ Поддерживает ВСЕ символы Unicode (>140,000 символов)");
        System.out.println("   ✓ Рекомендуется для всех новых проектов\n");
    }

    /**
     * 7. Попытка определения кодировки файла
     */
    private static void demonstrateEncodingDetection() throws IOException {
        System.out.println("7. Определение кодировки файла\n");

        System.out.println("   Проблема: Java НЕ может автоматически определить кодировку!");
        System.out.println("   Кодировка - это не часть файла, это соглашение.\n");

        // Создаем файлы в разных кодировках
        String text = "Привет";

        // UTF-8
        String fileUtf8 = "detect_utf8.txt";
        Files.write(Paths.get(fileUtf8), text.getBytes(StandardCharsets.UTF_8));

        // UTF-8 с BOM (Byte Order Mark)
        String fileUtf8BOM = "detect_utf8_bom.txt";
        try (FileOutputStream fos = new FileOutputStream(fileUtf8BOM)) {
            fos.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}); // UTF-8 BOM
            fos.write(text.getBytes(StandardCharsets.UTF_8));
        }

        // Читаем первые байты
        System.out.println("   Анализ первых байтов файлов:");

        byte[] bytesUtf8 = Files.readAllBytes(Paths.get(fileUtf8));
        System.out.println("\n   UTF-8 без BOM:");
        System.out.println("     Первые байты: " + Arrays.toString(Arrays.copyOf(bytesUtf8, Math.min(6, bytesUtf8.length))));
        System.out.println("     Невозможно определить кодировку!");

        byte[] bytesUtf8BOM = Files.readAllBytes(Paths.get(fileUtf8BOM));
        System.out.println("\n   UTF-8 с BOM:");
        System.out.println("     Первые байты: " + Arrays.toString(Arrays.copyOf(bytesUtf8BOM, 6)));
        System.out.println("     Сигнатура: EF BB BF → UTF-8 с BOM ✓");

        System.out.println("\n   Способы определения кодировки:");
        System.out.println("   1. BOM (Byte Order Mark) - специальные байты в начале");
        System.out.println("      UTF-8: EF BB BF");
        System.out.println("      UTF-16 BE: FE FF");
        System.out.println("      UTF-16 LE: FF FE");
        System.out.println("   2. Эвристика (угадывание) - ненадежно!");
        System.out.println("   3. Метаданные (HTTP headers, XML declaration)");
        System.out.println("   4. Пользовательский выбор\n");

        System.out.println("   Рекомендация:");
        System.out.println("   ✓ Всегда документируйте кодировку");
        System.out.println("   ✓ Используйте UTF-8 для всех новых файлов");
        System.out.println("   ✓ Явно указывайте кодировку при чтении/записи\n");
    }

    // ==================== Вспомогательные методы ====================

    /**
     * Тестирует, может ли кодировка правильно закодировать и декодировать текст
     */
    private static void testEncoding(String encodingName, String text, boolean shouldWork) {
        try {
            Charset charset = Charset.forName(encodingName);
            byte[] encoded = text.getBytes(charset);
            String decoded = new String(encoded, charset);

            boolean worksCorrectly = text.equals(decoded);
            String status = worksCorrectly ? "✓" : "✗";
            String result = worksCorrectly ? "OK" : "потеря данных";

            System.out.println("     " + status + " \"" + text + "\" → " + result);

            if (shouldWork && !worksCorrectly) {
                System.out.println("       Ожидалось: работает, Получено: " + decoded);
            }
        } catch (Exception e) {
            System.out.println("     ✗ \"" + text + "\" → ошибка");
        }
    }
}
