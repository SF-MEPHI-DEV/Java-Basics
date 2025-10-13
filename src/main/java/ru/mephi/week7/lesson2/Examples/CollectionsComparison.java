package ru.mephi.week7.lesson2.Examples;

import java.util.*;

public class CollectionsComparison {

    public static void main(String[] args) {
        System.out.println("=== Сравнение коллекций ===");
        comparisonTable();

        System.out.println("\n=== Когда использовать ===");
        whenToUseArray();
        whenToUseArrayList();
        whenToUseLinkedList();
    }

    public static void comparisonTable() {
        System.out.println("Операция          | Массив | ArrayList | LinkedList");
        System.out.println("----------------------------------------------------");
        System.out.println("Доступ по индексу | O(1)   | O(1)      | O(n)");
        System.out.println("Добавление в конец| -      | O(1)*     | O(1)");
        System.out.println("Вставка в начало  | -      | O(n)      | O(1)");
        System.out.println("Вставка в середину| -      | O(n)      | O(n)");
        System.out.println("Удаление начала   | -      | O(n)      | O(1)");
        System.out.println("Удаление конца    | -      | O(1)      | O(1)");
        System.out.println("Память            | min    | средняя   | большая");
        System.out.println("Размер фиксирован?| Да     | Нет       | Нет");
    }

    public static void whenToUseArray() {
        System.out.println("\nМассив:");
        System.out.println("- Фиксированный размер");
        System.out.println("- Максимальная производительность");
        System.out.println("- Работа с примитивами");

        int[] scores = new int[100];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = i * 10;
        }
    }

    public static void whenToUseArrayList() {
        System.out.println("\nArrayList:");
        System.out.println("- Частый доступ по индексу");
        System.out.println("- Добавление в конец");
        System.out.println("- Динамический размер");

        ArrayList<String> students = new ArrayList<>();
        students.add("Иван");
        students.add("Мария");
        students.add("Петр");
    }

    public static void whenToUseLinkedList() {
        System.out.println("\nLinkedList:");
        System.out.println("- Частая вставка/удаление в начале");
        System.out.println("- Не нужен доступ по индексу");
        System.out.println("- Последовательная обработка");

        LinkedList<String> tasks = new LinkedList<>();
        tasks.addFirst("Задача 1");
        tasks.addFirst("Срочная задача");
        tasks.addLast("Задача 3");
    }
}
