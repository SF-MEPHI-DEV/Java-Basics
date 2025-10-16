package ru.mephi.week8.lesson2.Examples;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetExample {

    public static void main(String[] args) {
        naturalOrderExample();
        customComparatorExample();
        navigationMethods();
        subSetExample();
    }

    public static void naturalOrderExample() {
        System.out.println("=== Естественная сортировка ===");

        TreeSet<Integer> numbers = new TreeSet<>();

        numbers.add(5);
        numbers.add(1);
        numbers.add(3);
        numbers.add(2);
        numbers.add(4);

        System.out.println("TreeSet автоматически сортирует:");
        System.out.println(numbers);

        TreeSet<String> words = new TreeSet<>();
        words.add("Banana");
        words.add("Apple");
        words.add("Cherry");
        words.add("Date");

        System.out.println("\nСтроки в алфавитном порядке:");
        for (String word : words) {
            System.out.println("  " + word);
        }
    }

    public static void customComparatorExample() {
        System.out.println("\n=== Свой компаратор ===");

        TreeSet<String> reverseSet = new TreeSet<>(Comparator.reverseOrder());

        reverseSet.add("A");
        reverseSet.add("B");
        reverseSet.add("C");
        reverseSet.add("D");

        System.out.println("Обратный порядок: " + reverseSet);

        TreeSet<String> lengthSet = new TreeSet<>(
            Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder())
        );

        lengthSet.add("Apple");
        lengthSet.add("Banana");
        lengthSet.add("Fig");
        lengthSet.add("Cherry");
        lengthSet.add("Date");

        System.out.println("\nПо длине, затем алфавиту:");
        for (String word : lengthSet) {
            System.out.println("  " + word + " (length: " + word.length() + ")");
        }
    }

    public static void navigationMethods() {
        System.out.println("\n=== Навигационные методы ===");

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 10; i <= 50; i += 10) {
            set.add(i);
        }

        System.out.println("Set: " + set);

        System.out.println("first: " + set.first());
        System.out.println("last: " + set.last());

        System.out.println("lower(30): " + set.lower(30));
        System.out.println("higher(30): " + set.higher(30));

        System.out.println("floor(35): " + set.floor(35));
        System.out.println("ceiling(35): " + set.ceiling(35));

        Integer polled = set.pollFirst();
        System.out.println("pollFirst: " + polled);
        System.out.println("После pollFirst: " + set);
    }

    public static void subSetExample() {
        System.out.println("\n=== SubSet ===");

        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i <= 10; i++) {
            set.add(i);
        }

        System.out.println("Полный set: " + set);

        System.out.println("subSet(3, 7): " + set.subSet(3, 7));
        System.out.println("headSet(5): " + set.headSet(5));
        System.out.println("tailSet(7): " + set.tailSet(7));

        System.out.println("\nИспользование для фильтрации:");
        TreeSet<Integer> scores = new TreeSet<>();
        scores.add(45);
        scores.add(67);
        scores.add(89);
        scores.add(92);
        scores.add(55);
        scores.add(78);

        System.out.println("Все баллы: " + scores);
        System.out.println("Баллы >= 70: " + scores.tailSet(70));
        System.out.println("Баллы < 70: " + scores.headSet(70));
    }
}
