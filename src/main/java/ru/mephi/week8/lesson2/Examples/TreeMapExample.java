package ru.mephi.week8.lesson2.Examples;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {

    public static void main(String[] args) {
        naturalOrderExample();
        customComparatorExample();
        navigationMethods();
        subMapExample();
    }

    public static void naturalOrderExample() {
        System.out.println("=== Естественная сортировка ===");

        TreeMap<Integer, String> map = new TreeMap<>();

        map.put(5, "Пять");
        map.put(1, "Один");
        map.put(3, "Три");
        map.put(2, "Два");
        map.put(4, "Четыре");

        System.out.println("TreeMap отсортирован по ключу:");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        TreeMap<String, Integer> stringMap = new TreeMap<>();
        stringMap.put("Banana", 2);
        stringMap.put("Apple", 1);
        stringMap.put("Cherry", 3);

        System.out.println("\nСтроки в алфавитном порядке:");
        for (String key : stringMap.keySet()) {
            System.out.println("  " + key + " = " + stringMap.get(key));
        }
    }

    public static void customComparatorExample() {
        System.out.println("\n=== Свой компаратор ===");

        TreeMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);

        System.out.println("Обратный порядок:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        TreeMap<Student, Integer> students = new TreeMap<>(
            Comparator.comparingInt(Student::getAge)
        );

        students.put(new Student("Иван", 25), 90);
        students.put(new Student("Мария", 22), 95);
        students.put(new Student("Петр", 28), 85);

        System.out.println("\nСтуденты по возрасту:");
        for (Map.Entry<Student, Integer> entry : students.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void navigationMethods() {
        System.out.println("\n=== Навигационные методы ===");

        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(10, "Десять");
        map.put(20, "Двадцать");
        map.put(30, "Тридцать");
        map.put(40, "Сорок");
        map.put(50, "Пятьдесят");

        System.out.println("firstKey: " + map.firstKey());
        System.out.println("lastKey: " + map.lastKey());

        System.out.println("lowerKey(30): " + map.lowerKey(30));
        System.out.println("higherKey(30): " + map.higherKey(30));

        System.out.println("floorKey(35): " + map.floorKey(35));
        System.out.println("ceilingKey(35): " + map.ceilingKey(35));

        Map.Entry<Integer, String> first = map.pollFirstEntry();
        System.out.println("pollFirstEntry: " + first);
        System.out.println("После удаления первого: " + map.keySet());
    }

    public static void subMapExample() {
        System.out.println("\n=== SubMap ===");

        TreeMap<Integer, String> map = new TreeMap<>();
        for (int i = 1; i <= 10; i++) {
            map.put(i, "Value " + i);
        }

        System.out.println("Полная map: " + map.keySet());

        Map<Integer, String> subMap = map.subMap(3, 7);
        System.out.println("subMap(3, 7): " + subMap);

        Map<Integer, String> headMap = map.headMap(5);
        System.out.println("headMap(5): " + headMap);

        Map<Integer, String> tailMap = map.tailMap(7);
        System.out.println("tailMap(7): " + tailMap);
    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
