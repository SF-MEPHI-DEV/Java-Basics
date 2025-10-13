package ru.mephi.week7.lesson2.Examples;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayVsArrayList {

    public static void main(String[] args) {
        System.out.println("=== Обычный массив ===");
        arrayExample();

        System.out.println("\n=== ArrayList ===");
        arrayListExample();

        System.out.println("\n=== Производительность ===");
        performanceComparison();
    }

    public static void arrayExample() {
        int[] numbers = new int[5];
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;

        System.out.println("Размер: " + numbers.length);
        System.out.println("Массив: " + Arrays.toString(numbers));

        int[] copy = Arrays.copyOf(numbers, 10);
        System.out.println("Расширенный: " + Arrays.toString(copy));
    }

    public static void arrayListExample() {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println("Размер: " + numbers.size());

        numbers.add(1, 15);
        System.out.println("После вставки: " + numbers);

        numbers.remove(2);
        System.out.println("После удаления: " + numbers);

        System.out.println("Содержит 20? " + numbers.contains(20));
    }

    public static void performanceComparison() {
        int size = 100000;

        long start = System.nanoTime();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        long end = System.nanoTime();
        System.out.println("Массив заполнение: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        end = System.nanoTime();
        System.out.println("ArrayList заполнение: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        int sum1 = 0;
        for (int i = 0; i < size; i++) {
            sum1 += array[i];
        }
        end = System.nanoTime();
        System.out.println("Массив доступ: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        int sum2 = 0;
        for (int i = 0; i < size; i++) {
            sum2 += list.get(i);
        }
        end = System.nanoTime();
        System.out.println("ArrayList доступ: " + (end - start) / 1_000_000.0 + " мс");
    }
}
