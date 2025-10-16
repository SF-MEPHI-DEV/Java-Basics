package ru.mephi.week8.lesson2.Examples;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetComparisonExample {

    public static void main(String[] args) {
        orderComparison();
        performanceComparison();
        whenToUse();
    }

    public static void orderComparison() {
        System.out.println("\n=== Сравнение порядка ===");

        System.out.println("\nДобавляем: 3, 1, 4, 2, 5");

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(3);
        hashSet.add(1);
        hashSet.add(4);
        hashSet.add(2);
        hashSet.add(5);

        System.out.print("HashSet: ");
        for (Integer num : hashSet) {
            System.out.print(num + " ");
        }
        System.out.println();

        LinkedHashSet<Integer> linkedSet = new LinkedHashSet<>();
        linkedSet.add(3);
        linkedSet.add(1);
        linkedSet.add(4);
        linkedSet.add(2);
        linkedSet.add(5);

        System.out.print("LinkedHashSet: ");
        for (Integer num : linkedSet) {
            System.out.print(num + " ");
        }
        System.out.println();

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(4);
        treeSet.add(2);
        treeSet.add(5);

        System.out.print("TreeSet: ");
        for (Integer num : treeSet) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void performanceComparison() {
        System.out.println("\n=== Производительность ===");

        int iterations = 100000;

        long start = System.nanoTime();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < iterations; i++) {
            hashSet.add(i);
        }
        for (int i = 0; i < iterations; i++) {
            hashSet.contains(i);
        }
        long end = System.nanoTime();
        System.out.println("HashSet: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        LinkedHashSet<Integer> linkedSet = new LinkedHashSet<>();
        for (int i = 0; i < iterations; i++) {
            linkedSet.add(i);
        }
        for (int i = 0; i < iterations; i++) {
            linkedSet.contains(i);
        }
        end = System.nanoTime();
        System.out.println("LinkedHashSet: " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < iterations; i++) {
            treeSet.add(i);
        }
        for (int i = 0; i < iterations; i++) {
            treeSet.contains(i);
        }
        end = System.nanoTime();
        System.out.println("TreeSet: " + (end - start) / 1_000_000.0 + " мс");

        System.out.println("\nВывод: HashSet самый быстрый, TreeSet самый медленный");
    }

    public static void whenToUse() {
        System.out.println("\n=== Практические примеры ===");

        System.out.println("\nУдаление дубликатов:");
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        HashSet<Integer> unique = new HashSet<>();
        for (int num : array) {
            unique.add(num);
        }
        System.out.println("Исходный: [3, 1, 4, 1, 5, 9, 2, 6, 5, 3]");
        System.out.println("Уникальные: " + unique);

        System.out.println("\nТоп-N элементов:");
        TreeSet<Integer> topScores = new TreeSet<>();
        topScores.add(85);
        topScores.add(92);
        topScores.add(78);
        topScores.add(95);
        topScores.add(88);

        System.out.println("Все баллы: " + topScores);
        System.out.println("Топ-3: " + topScores.tailSet(topScores.descendingSet().toArray(new Integer[0])[2]));
    }
}
