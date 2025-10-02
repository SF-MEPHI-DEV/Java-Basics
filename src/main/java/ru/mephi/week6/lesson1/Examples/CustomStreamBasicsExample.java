package ru.mephi.week6.lesson1.Examples;

import java.io.*;

/**
 * <h2>Базовые примеры создания собственных потоков</h2>
 * <p>
 * Этот пример показывает, как создавать простые собственные потоки,
 * наследуясь от базовых классов InputStream и OutputStream.
 * Потоки будут простыми, без сложной логики - только для демонстрации концепции.
 * </p>
 */
public class CustomStreamBasicsExample {

    public static void main(String[] args) {
        System.out.println("=== Создание собственных потоков: базовые примеры ===\n");

        try {
            // 1. Простейший поток ввода - читает из массива байтов
            demonstrateByteArrayInputStream();

            // 2. Простейший поток вывода - пишет в массив байтов
            demonstrateByteArrayOutputStream();

            // 3. Создание собственного потока ввода
            demonstrateCustomInputStream();

            // 4. Создание собственного потока вывода
            demonstrateCustomOutputStream();

            // 5. Создание собственного потока вывода
            demonstrateMultiplyStream();

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void demonstrateByteArrayInputStream() throws IOException {
        System.out.println("1. Чтение из массива байтов (ByteArrayInputStream)\n");

        // Создаем массив байтов с данными
        byte[] data = {65, 66, 67, 68, 69};  // A, B, C, D, E
        System.out.println("   Исходный массив байтов: " + java.util.Arrays.toString(data));

        // Создаем поток для чтения из этого массива
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data)) {
            System.out.println("   Чтение по одному байту:");

            int byteValue;
            int position = 0;
            while ((byteValue = bais.read()) != -1) {
                System.out.println("     Позиция " + position + ": байт=" + byteValue + ", символ='" + (char) byteValue + "'");
                position++;
            }
        }

        System.out.println("\n   Вывод: InputStream читает данные из источника (здесь - из массива)\n");
    }
    private static void demonstrateByteArrayOutputStream() throws IOException {
        System.out.println("2. Запись в массив байтов (ByteArrayOutputStream)\n");

        // Создаем поток для записи в память
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            System.out.println("   Запись байтов в поток:");

            // Записываем отдельные байты
            baos.write(72);  // H
            baos.write(101); // e
            baos.write(108); // l
            baos.write(108); // l
            baos.write(111); // o

            System.out.println("   Записано 5 байтов");

            // Получаем массив байтов из потока
            byte[] result = baos.toByteArray();
            System.out.println("   Результат: " + java.util.Arrays.toString(result));
            System.out.println("   Как строка: \"" + new String(result) + "\"");
        }

        System.out.println("\n   Вывод: OutputStream записывает данные в приемник (здесь - в массив)\n");
    }
    private static void demonstrateCustomInputStream() throws IOException {
        System.out.println("3. Собственный поток ввода (NumberInputStream)\n");

        System.out.println("   NumberInputStream - генерирует последовательность чисел от 1 до N");
        System.out.println("   Создаем поток, который выдаст числа от 1 до 10:\n");

        // Создаем наш собственный поток
        try (NumberInputStream nis = new NumberInputStream(10)) {
            int value;
            int count = 0;
            while ((value = nis.read()) != -1) {
                count++;
                System.out.println("     Прочитано значение #" + count + ": " + value);
            }
        }

        System.out.println("\n   Вывод: мы создали поток, который генерирует данные по своим правилам\n");
    }
    private static void demonstrateCustomOutputStream() throws IOException {
        System.out.println("4. Собственный поток вывода (SumOutputStream)\n");

        System.out.println("   SumOutputStream - подсчитывает сумму всех записанных байтов");
        System.out.println("   Записываем несколько значений:\n");

        // Создаем наш собственный поток
        try (SumOutputStream sos = new SumOutputStream()) {
            int[] values = {10, 20, 30, 40, 50};

            for (int value : values) {
                sos.write(value);
                System.out.println("     Записано: " + value + ", текущая сумма: " + sos.getSum());
            }

            System.out.println("\n   Итоговая сумма всех записанных байтов: " + sos.getSum());
        }

        System.out.println("\n   Вывод: мы создали поток, который обрабатывает данные по-своему\n");
    }

    /**
     * Собственный поток ввода, который генерирует последовательность чисел от 1 до N
     * Наследуемся от InputStream и переопределяем метод read()
     */
    static class NumberInputStream extends InputStream {
        private int current;      // Текущее число
        private final int max;    // Максимальное число
        private boolean closed = false;
        public NumberInputStream(int max) {
            this.current = 1;
            this.max = max;
        }
        @Override
        public int available() throws IOException {
            // Возвращаем, сколько чисел осталось для чтения
            return max - (current - 1);
        }
        @Override
        public int read() throws IOException {
            if(closed){
                return -1;
            }
            if (current > max) {
                return -1;  // Конец потока
            }
            return current++;  // Возвращаем текущее значение и увеличиваем счетчик
        }

        @Override
        public void close() throws IOException {
            super.close();
            closed = true;
            System.out.println("     [NumberInputStream закрыт]");
        }
    }

    static class SumOutputStream extends OutputStream {
        private long sum;  // Сумма всех записанных байтов
        public SumOutputStream() {
            this.sum = 0;
        }
        @Override
        public void write(int b) throws IOException {
            sum += b;
        }
        public long getSum() {
            return sum;
        }
        public void reset() {
            sum = 0;
        }

        @Override
        public void close() throws IOException {
            super.close();
            System.out.println("     [SumOutputStream закрыт, финальная сумма: " + sum + "]");
        }
    }

    static class MultiplyInputStream extends InputStream {
        private final InputStream source;  // Исходный поток
        private final int multiplier;      // Коэффициент умножения

        public MultiplyInputStream(InputStream source, int multiplier) {
            this.source = source;
            this.multiplier = multiplier;
        }

        @Override
        public int read() throws IOException {
            int value = source.read();
            if (value == -1) {
                return -1;
            }
            return value * multiplier ;
        }

        @Override
        public void close() throws IOException {
            source.close();
        }
    }

    static void demonstrateMultiplyStream() throws IOException {
        System.out.println("Поток с умножением значений\n");

        byte[] data = {1, 2, 3, 4, 5};
        System.out.println("   Исходные данные: " + java.util.Arrays.toString(data));
        System.out.println("   Умножаем каждое значение на 10:\n");

        try (MultiplyInputStream mis = new MultiplyInputStream(
                new ByteArrayInputStream(data), 10)) {

            int value;
            int position = 0;
            while ((value = mis.read()) != -1) {
                System.out.println("     Позиция " + position + ": " + value);
                position++;
            }
        }

        System.out.println("\n   Вывод: потоки можно оборачивать друг в друга (декоратор)\n");
    }
}
