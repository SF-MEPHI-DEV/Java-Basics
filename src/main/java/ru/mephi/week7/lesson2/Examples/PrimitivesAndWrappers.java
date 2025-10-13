package ru.mephi.week7.lesson2.Examples;

import java.util.ArrayList;

public class PrimitivesAndWrappers {

    public static void main(String[] args) {
        autoboxingExample();
        performanceComparison();
        memoryComparison();
    }

    public static void autoboxingExample() {
        System.out.println("=== Autoboxing ===");

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(Integer.valueOf(20));

        int value = numbers.get(0);
        System.out.println("value: " + value);

        int sum = numbers.get(0) + numbers.get(1);
        System.out.println("sum: " + sum);

        System.out.println("\nInteger cache:");
        Integer a = 127;
        Integer b = 127;
        System.out.println("127 == 127: " + (a == b));

        Integer c = 128;
        Integer d = 128;
        System.out.println("128 == 128: " + (c == d));
        System.out.println("128 equals 128: " + c.equals(d));
    }

    public static void performanceComparison() {
        System.out.println("\n=== Производительность ===");

        int iterations = 10_000_000;

        long start = System.nanoTime();
        int sum1 = 0;
        for (int i = 0; i < iterations; i++) {
            sum1 += i;
        }
        long end = System.nanoTime();
        System.out.println("int: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        Integer sum2 = 0;
        for (int i = 0; i < iterations; i++) {
            sum2 += i;
        }
        end = System.nanoTime();
        System.out.println("Integer: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        end = System.nanoTime();
        System.out.println("\nint[]: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
        end = System.nanoTime();
        System.out.println("ArrayList<Integer>: " + (end - start) / 1_000_000.0 + " мс");
    }

    public static void memoryComparison() {
        System.out.println("\n=== Память ===");

        System.out.println("int: 4 байта");
        System.out.println("Integer: ~16 байт");

        System.out.println("\nМассив из 1000:");
        System.out.println("int[1000]: " + (1000 * 4) + " байт");
        System.out.println("ArrayList<Integer>: ~" + (1000 * 16 + 1000 * 4) + " байт");
    }
}
