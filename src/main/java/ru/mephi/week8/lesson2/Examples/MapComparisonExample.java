package ru.mephi.week8.lesson2.Examples;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapComparisonExample {

    public static void main(String[] args) {
        orderComparison();
        performanceComparison();
        whenToUse();
    }
    
    public static void orderComparison() {
        System.out.println("\n=== Сравнение порядка ===");

        System.out.println("\nДобавляем: 3, 1, 4, 2");

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(3, "Три");
        hashMap.put(1, "Один");
        hashMap.put(4, "Четыре");
        hashMap.put(2, "Два");

        System.out.print("HashMap: ");
        for (Integer key : hashMap.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>();
        linkedMap.put(3, "Три");
        linkedMap.put(1, "Один");
        linkedMap.put(4, "Четыре");
        linkedMap.put(2, "Два");

        System.out.print("LinkedHashMap: ");
        for (Integer key : linkedMap.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "Три");
        treeMap.put(1, "Один");
        treeMap.put(4, "Четыре");
        treeMap.put(2, "Два");

        System.out.print("TreeMap: ");
        for (Integer key : treeMap.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println();
    }

    public static void performanceComparison() {
        System.out.println("\n=== Производительность ===");

        int iterations = 100000;

        long start = System.nanoTime();
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < iterations; i++) {
            hashMap.put(i, "Value" + i);
        }
        for (int i = 0; i < iterations; i++) {
            hashMap.get(i);
        }
        long end = System.nanoTime();
        System.out.println("HashMap: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>();
        for (int i = 0; i < iterations; i++) {
            linkedMap.put(i, "Value" + i);
        }
        for (int i = 0; i < iterations; i++) {
            linkedMap.get(i);
        }
        end = System.nanoTime();
        System.out.println("LinkedHashMap: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < iterations; i++) {
            treeMap.put(i, "Value" + i);
        }
        for (int i = 0; i < iterations; i++) {
            treeMap.get(i);
        }
        end = System.nanoTime();
        System.out.println("TreeMap: " + (end - start) / 1_000_000.0 + " мс");

        System.out.println("\nВывод: HashMap самый быстрый, TreeMap самый медленный");
    }

    public static void whenToUse() {
        System.out.println("\n=== Примеры использования ===");

        System.out.println("\nПорядок вставки с LinkedHashMap:");
        LinkedHashMap<String, Integer> grades = new LinkedHashMap<>();
        grades.put("Математика", 5);
        grades.put("Физика", 4);
        grades.put("Химия", 5);
        grades.put("История", 4);

        System.out.println("Оценки (в порядке добавления): " + grades);

        System.out.println("\nДиапазон ключей с TreeMap:");
        TreeMap<Integer, String> tree = new TreeMap<>();
        tree.put(10, "Ten");
        tree.put(20, "Twenty");
        tree.put(30, "Thirty");
        tree.put(40, "Forty");

        System.out.println("От 15 до 35: " + tree.subMap(15, 35));
    }
}
