package ru.mephi.week8.lesson2.Examples;

import java.util.HashSet;
import java.util.Set;

public class HashSetExample {

    public static void main(String[] args) {
        basicOperations();
        uniquenessExample();
        setOperations();
        hashCodeEqualsExample();
    }

    public static void basicOperations() {
        System.out.println("=== Базовые операции HashSet ===");

        HashSet<String> set = new HashSet<>();

        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        System.out.println("Set: " + set);

        set.add("Apple");
        System.out.println("После add(Apple): " + set);
        System.out.println("Размер: " + set.size());

        System.out.println("Содержит Banana? " + set.contains("Banana"));
        System.out.println("Содержит Orange? " + set.contains("Orange"));

        set.remove("Banana");
        System.out.println("После remove(Banana): " + set);

        System.out.println("\nОбход:");
        for (String item : set) {
            System.out.println("  " + item);
        }
    }

    public static void uniquenessExample() {
        System.out.println("\n=== Уникальность элементов ===");

        HashSet<Integer> numbers = new HashSet<>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(2);
        numbers.add(1);

        System.out.println("Set: " + numbers);
        System.out.println("Размер: " + numbers.size());

        int[] array = {5, 2, 8, 2, 9, 5, 1};
        HashSet<Integer> uniqueNumbers = new HashSet<>();
        for (int num : array) {
            uniqueNumbers.add(num);
        }

        System.out.println("Массив: [5, 2, 8, 2, 9, 5, 1]");
        System.out.println("Уникальные: " + uniqueNumbers);
    }

    public static void setOperations() {
        System.out.println("\n=== Операции над множествами ===");

        HashSet<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set1.add(4);

        HashSet<Integer> set2 = new HashSet<>();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);

        System.out.println("Set1: " + set1);
        System.out.println("Set2: " + set2);

        HashSet<Integer> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Объединение: " + union);

        HashSet<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Пересечение: " + intersection);

        HashSet<Integer> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Разность (set1 - set2): " + difference);
    }

    public static void hashCodeEqualsExample() {
        System.out.println("\n=== hashCode и equals ===");

        HashSet<Person> people = new HashSet<>();

        Person p1 = new Person("Иван", 25);
        Person p2 = new Person("Мария", 30);
        Person p3 = new Person("Иван", 25);

        people.add(p1);
        people.add(p2);
        people.add(p3);

        System.out.println("Размер set: " + people.size());
        System.out.println("p1.equals(p3): " + p1.equals(p3));
        System.out.println("p1.hashCode() == p3.hashCode(): " + (p1.hashCode() == p3.hashCode()));

        System.out.println("\nЛюди в set:");
        for (Person p : people) {
            System.out.println("  " + p);
        }
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
