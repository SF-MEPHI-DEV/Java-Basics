package ru.mephi.week6.lesson2.CustomStreams;

import ru.mephi.week6.lesson2.CustomStreams.Buffer.MyBufferedInputStream;
import ru.mephi.week6.lesson2.CustomStreams.Buffer.MyBufferedOutputStream;
import ru.mephi.week6.lesson2.CustomStreams.CheckSum.ChecksumOutputStream;

import java.io.*;


public class DecoratorPatternDemo {

    /**
     * Пример 1: Базовое использование без декораторов
     */
    public static void example1_BaseStream() throws IOException {
        System.out.println("\n=== Пример 1: Базовый поток без декораторов ===\n");

        byte[] data = "Hello, Java Streams!".getBytes();

        // Создаём базовый поток
        ByteArrayInputStream input = new ByteArrayInputStream(data);

        byte[] buffer = new byte[5];
        int read = input.read(buffer);

        System.out.println("Прочитано: " + new String(buffer, 0, read));
        input.close();
    }

    /**
     * Пример 2: Один декоратор - добавляем буферизацию
     */
    public static void example2_SingleDecorator() throws IOException {
        System.out.println("\n=== Пример 2: Один декоратор (буферизация) ===\n");

        byte[] data = "Buffered reading is more efficient!".getBytes();

        // Оборачиваем базовый поток в буферизированный
        InputStream input = new MyBufferedInputStream(
                new ByteArrayInputStream(data),
                16  // Буфер 16 байт
        );

        byte[] buffer = new byte[10];
        while (input.read(buffer) != -1) {
            System.out.println("Блок: " + new String(buffer));
        }

        input.close();
    }

    /**
     * Пример 3: Два декоратора - полная матрёшка для вывода
     */
    public static void example3_TwoDecorators() throws IOException {
        System.out.println("\n=== Пример 3: Два декоратора для вывода ===\n");

        // МАТРЁШКА для записи:
        // ChecksumOutputStream -> MyBufferedOutputStream -> ByteArrayOutputStream
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        ChecksumOutputStream checksum = new ChecksumOutputStream(
                new MyBufferedOutputStream(
                        byteOutput,
                        16  // Буфер 16 байт
                )
        );

        // Пишем данные
        checksum.write("First line\n".getBytes());
        checksum.write("Second line\n".getBytes());
        checksum.write("Third line".getBytes());

        System.out.println("\n--- Вызываем flush() ---");
        checksum.flush();

        System.out.println("Контрольная сумма: " + checksum.getChecksumValue());
        System.out.println("Записано байт: " + byteOutput.size());

        checksum.close();
    }

    public static void main(String[] args) throws IOException {
        example1_BaseStream();
        example2_SingleDecorator();
        example3_TwoDecorators();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("ИТОГО: Паттерн Декоратор позволяет гибко комбинировать функциональность!");
        System.out.println("=".repeat(70));
    }
}
