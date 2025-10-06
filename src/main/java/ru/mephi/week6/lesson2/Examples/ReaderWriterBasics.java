package ru.mephi.week6.lesson2.Examples;

import java.io.*;

public class ReaderWriterBasics {

    public static void exampleBasicReader() {
        System.out.println("=== Пример 1: Базовое чтение через Reader ===");
        String text = "Привет, Java!\nЭто пример работы с Reader.";

        try (Reader reader = new StringReader(text)) {
            int charCode;
            System.out.println("Посимвольное чтение:");

            while ((charCode = reader.read()) != -1) {
                char ch = (char) charCode;
                System.out.print(ch);
            }
            System.out.println("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Пример 2: Чтение массивом символов
     */
    public static void exampleReaderWithBuffer() {
        System.out.println("=== Пример 2: Чтение массивом символов ===");

        String text = "Буферизированное чтение эффективнее посимвольного!";

        try (Reader reader = new StringReader(text)) {
            char[] buffer = new char[10]; // Буфер на 10 символов
            int charsRead;

            System.out.println("Читаем блоками по 10 символов:");
            while ((charsRead = reader.read(buffer)) != -1) {
                System.out.println("Прочитано " + charsRead + " символов: " +
                                   new String(buffer, 0, charsRead));
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Пример 3: Базовая запись через Writer
     */
    public static void exampleBasicWriter() throws IOException {
        System.out.println("=== Пример 3: Базовая запись через Writer ===");

        // StringWriter - пишет в строку (накапливает в памяти)
        try (StringWriter writer = new StringWriter()) {

            // Запись отдельных символов
            writer.write('J');
            writer.write('a');
            writer.write('v');
            writer.write('a');

            // Запись строки
            writer.write("\n");
            writer.write("Можно писать целые строки!");

            // Запись части массива символов
            char[] chars = {'А', 'Б', 'В', 'Г', 'Д'};
            writer.write(chars, 1, 3); // Пишем БВГ (offset=1, length=3)
            // Важно: вызов flush() гарантирует запись данных
            writer.flush();

            System.out.println("Результат записи:");
            System.out.println(writer.toString());
            System.out.println();
        }
    }

    /**
     * Пример 4: CharArrayReader и CharArrayWriter
     */
    public static void exampleCharArrayReaderWriter() throws IOException {
        System.out.println("=== Пример 4: CharArrayReader/Writer ===");

        // CharArrayWriter - пишет в char массив
        CharArrayWriter charWriter = new CharArrayWriter();
        charWriter.write("Первая строка\n");
        charWriter.write("Вторая строка\n");
        charWriter.write("Третья строка");

        // Получаем массив символов
        char[] data = charWriter.toCharArray();
        System.out.println("Записано символов: " + data.length);

        // CharArrayReader - читает из char массива
        CharArrayReader charReader = new CharArrayReader(data);
        char[] buffer = new char[20];
        int read = charReader.read(buffer);

        System.out.println("Первые 20 символов: " + new String(buffer, 0, read));
        System.out.println();

        charWriter.close();
        charReader.close();
    }

    /**
     * Пример 5: InputStreamReader - мост между byte и char потоками
     */
    public static void exampleInputStreamReader() throws IOException {
        System.out.println("=== Пример 5: InputStreamReader (byte -> char) ===");

        // Создаём byte поток с данными в UTF-8
        String text = "Привет! Hello! 你好!";
        byte[] bytes = text.getBytes("UTF-8");
        ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);

        // Оборачиваем в Reader с указанием кодировки
        try (InputStreamReader reader = new InputStreamReader(byteStream, "UTF-8")) {
            char[] buffer = new char[50];
            int charsRead = reader.read(buffer);

            System.out.println("Прочитано символов: " + charsRead);
            System.out.println("Текст: " + new String(buffer, 0, charsRead));
            System.out.println();
        }
    }

    /**
     * Пример 6: OutputStreamWriter - мост между char и byte потоками
     */
    public static void exampleOutputStreamWriter() throws IOException {
        System.out.println("=== Пример 6: OutputStreamWriter (char -> byte) ===");

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        // Оборачиваем byte поток в Writer
        try (OutputStreamWriter writer = new OutputStreamWriter(byteStream, "UTF-8")) {
            writer.write("Текст на русском");
            writer.write("\n");
            writer.write("English text");
            writer.write("\n");
            writer.write("中文文本");
            writer.flush();

            // Получаем результат как byte массив
            byte[] result = byteStream.toByteArray();
            System.out.println("Записано байт: " + result.length);
            System.out.println("Результат: " + new String(result, "UTF-8"));
            System.out.println();
        }
    }

    /**
     * Пример 7: Использование skip() и ready()
     */
    public static void exampleSkipAndReady() throws IOException {
        System.out.println("=== Пример 7: Методы skip() и ready() ===");

        String text = "0123456789ABCDEFGHIJ";

        try (Reader reader = new StringReader(text)) {
            System.out.println("Исходный текст: " + text);

            // ready() проверяет, готов ли поток к чтению
            if (reader.ready()) {
                System.out.println("Поток готов к чтению");
            }

            // Читаем первые 5 символов
            char[] buffer = new char[5];
            reader.read(buffer);
            System.out.println("Прочитано: " + new String(buffer));

            // Пропускаем 3 символа
            long skipped = reader.skip(3);
            System.out.println("Пропущено символов: " + skipped);

            // Читаем оставшиеся
            reader.read(buffer);
            System.out.println("Прочитано после skip: " + new String(buffer));
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        exampleBasicReader();
        exampleReaderWithBuffer();
        exampleBasicWriter();
        exampleCharArrayReaderWriter();
        exampleInputStreamReader();
        exampleOutputStreamWriter();
        exampleSkipAndReady();
    }
}
