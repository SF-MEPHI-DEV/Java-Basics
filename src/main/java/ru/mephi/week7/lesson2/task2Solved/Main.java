package ru.mephi.week7.lesson2.task2Solved;

public class Main {

    public static void main(String[] args) {
        example1_BasicOperations();
        example2_SequentialProcessing();
        example3_RemovePattern();
        example4_SearchAndReplace();
        example5_LinkedListVsVector();
    }

    public static void example1_BasicOperations() {
        System.out.println("=== Базовые операции ===");

        MyList list = new MyList();

        list.add(5);
        list.add(10);
        list.add(15);
        list.add(20);

        System.out.println("Размер: " + list.size());
        System.out.println("Элемент [2]: " + list.get(2));

        list.remove(1);
        System.out.println("После remove(1): " + list.get(1));
    }

    public static void example2_SequentialProcessing() {
        System.out.println("\n=== Обработка списка ===");

        MyList numbers = new MyList();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        System.out.println("Размер: " + numbers.size());

        int sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }
        System.out.println("Сумма: " + sum);

        numbers.remove(2);
        System.out.println("После удаления [2]: size=" + numbers.size());
    }

    public static void example3_RemovePattern() {
        System.out.println("\n=== Удаление кратных 3 ===");

        MyList numbers = new MyList();
        for (int i = 1; i <= 15; i++) {
            numbers.add(i);
        }

        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 3 == 0) {
                numbers.remove(i);
            }
        }

        System.out.println("Размер после удаления: " + numbers.size());
    }

    public static void example4_SearchAndReplace() {
        System.out.println("\n=== Поиск и замена ===");

        MyList list = new MyList();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(20);
        list.add(40);

        int target = 20;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == target) {
                System.out.println("Найдено на позиции: " + i);
                list.remove(i);
                list.add(99);
                break;
            }
        }

        System.out.println("Размер: " + list.size());
    }

    public static void example5_LinkedListVsVector() {
        System.out.println("\n=== Удаление из начала: O(1) ===");

        MyList list = new MyList();
        for (int i = 0; i < 10; i++) {
            list.add(i * 10);
        }

        list.remove(0);
        list.remove(0);
        list.remove(0);

        System.out.println("Размер после удалений: " + list.size());
        System.out.println("Новый первый: " + list.get(0));
    }

}
