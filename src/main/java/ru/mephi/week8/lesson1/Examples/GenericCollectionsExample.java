package ru.mephi.week8.lesson1.Examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GenericCollectionsExample {

    public static void main(String[] args) {
        arrayListExample();
        linkedListExample();
        hashMapExample();
        rawTypesProblems();
        heapPollution();
    }

    public static void arrayListExample() {
        System.out.println("=== ArrayList<T> ===");

        ArrayList<String> names = new ArrayList<>();
        names.add("Иван");
        names.add("Мария");
        names.add("Петр");

        System.out.println(names);
        System.out.println(names.get(0));

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        Integer num1 = numbers.get(0);
        Object num2 = (Integer) numbers.get(0);
        int sum = 0;
        for (Integer num : numbers) {
            sum += num;
        }
        System.out.println(sum);
    }

    public static void linkedListExample() {
        System.out.println("\n=== LinkedList<T> ===");

        LinkedList<String> queue = new LinkedList<>();
        queue.add("Первый");
        queue.add("Второй");
        queue.add("Третий");

        System.out.println(queue);
        System.out.println(queue.removeFirst());
        System.out.println(queue);

        LinkedList<Integer> stack = new LinkedList<>();
        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);

        System.out.println(stack);
        System.out.println(stack.removeFirst());
    }

    public static void hashMapExample() {
        System.out.println("\n=== HashMap<K, V> ===");

        HashMap<String, Integer> ages = new HashMap<>();
        ages.put("Иван", 25);
        ages.put("Мария", 30);
        ages.put("Петр", 22);

        System.out.println(ages.get("Мария"));
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        HashMap<Integer, String> idToName = new HashMap<>();
        idToName.put(101, "Admin");
        idToName.put(102, "User");
        idToName.put(103, "Guest");

        System.out.println(idToName.get(102));
    }

    public static void rawTypesProblems() {
        System.out.println("\n=== Проблемы Raw Types ===");

        List rawList = new ArrayList();
        rawList.add("String1");
        rawList.add("String2");
        rawList.add(123);
        rawList.add(3.14);

        System.out.println(rawList);
        List<String> stringList = rawList;
        try {
            String first = stringList.get(0);
            System.out.println(first);

            String second = stringList.get(1);
            System.out.println(second);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static void heapPollution() {
        System.out.println("\n=== Heap Pollution ===");

        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");

        List rawList = stringList;

        rawList.add(123);

        System.out.println(rawList);

        try {
            for (String s : stringList) {
                System.out.println(s);
            }
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: List<String> содержит Integer");
        }
    }
}
