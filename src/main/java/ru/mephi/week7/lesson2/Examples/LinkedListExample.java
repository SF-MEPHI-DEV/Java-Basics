package ru.mephi.week7.lesson2.Examples;

import java.util.LinkedList;

public class LinkedListExample {

    public static void main(String[] args) {
        basicOperations();
        performanceTest();
    }

    public static void basicOperations() {
        System.out.println("=== Базовые операции ===");

        LinkedList<String> list = new LinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.addFirst("START");
        list.addLast("END");
        System.out.println("Список: " + list);

        System.out.println("getFirst: " + list.getFirst());
        System.out.println("get(2): " + list.get(2));

        list.removeFirst();
        list.removeLast();
        System.out.println("После удаления: " + list);
    }

    public static void performanceTest() {
        System.out.println("\n=== Производительность ===");

        int size = 10000;

        long start = System.nanoTime();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.addFirst(i);
        }
        long end = System.nanoTime();
        System.out.println("addFirst: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            list.get(i);
        }
        end = System.nanoTime();
        System.out.println("get(i): " + (end - start) / 1_000_000.0 + " мс (медленно)");

        start = System.nanoTime();
        for (Integer num : list) {
            int temp = num;
        }
        end = System.nanoTime();
        System.out.println("for-each: " + (end - start) / 1_000_000.0 + " мс (быстро)");
    }
}
