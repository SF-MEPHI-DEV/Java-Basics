package ru.mephi.week7.lesson2.Examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListExample {

    public static void main(String[] args) {
        basicOperations();
        iterationExamples();
        sortingAndSearching();
        sublistExample();
    }

    public static void basicOperations() {
        System.out.println("=== Базовые операции ===");

        ArrayList<String> cities = new ArrayList<>();
        cities.add("Москва");
        cities.add("Санкт-Петербург");
        cities.add("Казань");
        System.out.println("Список: " + cities);

        cities.add(1, "Новосибирск");
        System.out.println("Вставка: " + cities);

        String removed = cities.remove(2);
        System.out.println("Удалён: " + removed);

        cities.set(0, "МОСКВА");
        System.out.println("После изменения: " + cities);

        System.out.println("Размер: " + cities.clone());
    }

    public static void iterationExamples() {
        System.out.println("\n=== Способы обхода ===");

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        System.out.print("For-each: ");
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        System.out.print("forEach: ");
        numbers.forEach(num -> System.out.print(num + " "));
        System.out.println();
    }

    public static void sortingAndSearching() {
        System.out.println("\n=== Сортировка и поиск ===");

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(50);
        numbers.add(20);
        numbers.add(80);
        numbers.add(10);
        numbers.add(30);

        System.out.println("Исходный: " + numbers);

        Collections.sort(numbers);
        System.out.println("Отсортированный: " + numbers);
        int max = Collections.max(numbers);
        int min = Collections.min(numbers);
        System.out.println("Max: " + max + ", Min: " + min);

        Collections.shuffle(numbers);
        System.out.println("Перемешанный: " + numbers);
    }

    public static void sublistExample() {
        System.out.println("\n=== Sublists ===");

        ArrayList<String> days = new ArrayList<>();
        days.add("Пн");
        days.add("Вт");
        days.add("Ср");
        days.add("Чт");
        days.add("Пт");
        days.add("Сб");
        days.add("Вс");

        List<String> workDays = days.subList(0, 5);
        System.out.println("Будни: " + workDays);

        List<String> weekend = days.subList(5, 7);
        System.out.println("Выходные: " + weekend);
    }
}
