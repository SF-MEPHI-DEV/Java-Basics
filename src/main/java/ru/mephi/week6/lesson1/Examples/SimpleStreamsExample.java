package ru.mephi.week6.lesson1.Examples;

import java.io.*;

public class SimpleStreamsExample {

    public static void main(String[] args) {
        try {
            basicByteArray();
            customNumberStream();
            customSumStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void basicByteArray() throws IOException {
        System.out.println("Работа с ByteArrayInputStream/OutputStream\n");

        // Записываем данные в память
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write("Test".getBytes());
        byte[] data = baos.toByteArray();

        System.out.println("Записали: " + new String(data));

        // Читаем обратно
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        int b;
        System.out.print("Читаем: ");
        while ((b = bais.read()) != -1) {
            System.out.print((char) b);
        }
        System.out.println("\n");
    }

    static void customNumberStream() throws IOException {
        System.out.println("Свой поток - генератор чисел\n");

        NumberStream ns = new NumberStream(5);
        int num;
        while ((num = ns.read()) != -1) {
            System.out.println("Число: " + num);
        }
        ns.close();
        System.out.println();
    }

    static void customSumStream() throws IOException {
        System.out.println("Свой поток - счётчик суммы\n");

        SumStream ss = new SumStream();
        int[] values = {10, 20, 30};

        for (int v : values) {
            ss.write(v);
            System.out.println("Записали " + v + ", сумма: " + ss.getSum());
        }
        ss.close();
        System.out.println();
    }

    // Простой поток который выдает числа от 1 до max
    static class NumberStream extends InputStream {
        private int current = 1;
        private int max;

        NumberStream(int max) {
            this.max = max;
        }

        @Override
        public int read() {
            if (current > max) return -1;
            return current++;
        }
    }

    // Поток который считает сумму записанных байтов
    static class SumStream extends OutputStream {
        private long sum = 0;

        @Override
        public void write(int b) {
            sum += b;
        }

        long getSum() {
            return sum;
        }
    }
}
