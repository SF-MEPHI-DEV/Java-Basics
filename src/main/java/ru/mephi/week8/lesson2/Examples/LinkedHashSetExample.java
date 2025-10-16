package ru.mephi.week8.lesson2.Examples;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class LinkedHashSetExample {

    public static void main(String[] args) {
        insertionOrderExample();
        hashSetVsLinkedHashSet();
        removeDuplicatesWithOrder();
    }

    public static void insertionOrderExample() {
        System.out.println("=== Порядок вставки ===");

        LinkedHashSet<String> set = new LinkedHashSet<>();

        set.add("Третий");
        set.add("Первый");
        set.add("Второй");
        set.add("Четвертый");

        System.out.println("LinkedHashSet сохраняет порядок:");
        for (String item : set) {
            System.out.println("  " + item);
        }

        set.add("Первый");
        System.out.println("\nПосле повторного add(Первый):");
        for (String item : set) {
            System.out.println("  " + item);
        }
    }

    public static void hashSetVsLinkedHashSet() {
        System.out.println("\n=== HashSet vs LinkedHashSet ===");

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(5);
        hashSet.add(1);
        hashSet.add(3);
        hashSet.add(2);
        hashSet.add(4);

        System.out.println("HashSet (порядок не гарантирован):");
        System.out.print("  ");
        for (Integer num : hashSet) {
            System.out.print(num + " ");
        }
        System.out.println();

        LinkedHashSet<Integer> linkedSet = new LinkedHashSet<>();
        linkedSet.add(5);
        linkedSet.add(1);
        linkedSet.add(3);
        linkedSet.add(2);
        linkedSet.add(4);

        System.out.println("LinkedHashSet (порядок вставки):");
        System.out.print("  ");
        for (Integer num : linkedSet) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void removeDuplicatesWithOrder() {
        System.out.println("\n=== Удаление дубликатов с сохранением порядка ===");

        String[] words = {"apple", "banana", "apple", "cherry", "banana", "date"};

        System.out.print("Исходный массив: [");
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i]);
            if (i < words.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

        LinkedHashSet<String> uniqueWords = new LinkedHashSet<>();
        for (String word : words) {
            uniqueWords.add(word);
        }

        System.out.println("Уникальные (порядок сохранен): " + uniqueWords);

        HashSet<String> hashSet = new HashSet<>();
        for (String word : words) {
            hashSet.add(word);
        }

        System.out.println("HashSet (порядок изменен): " + hashSet);
    }
}
