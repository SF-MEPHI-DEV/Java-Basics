package ru.mephi.week7.lesson2.task1Solved;

public class Main {

    public static void main(String[] args) {
        example1_BasicOperations();
        example2_GrowthTest();
        example3_ScoreTracking();
        example4_FilteringData();
        example5_ExceptionHandling();

        Integer test = 5;
        Integer.compareUnsigned(test, 5);
    }

    public static void example1_BasicOperations() {
        System.out.println("=== Базовые операции ===");

        MyVector vector = new MyVector();

        vector.add(10);
        vector.add(20);
        vector.add(30);
        vector.add(40);
        vector.add(50);

        System.out.println("Вектор: " + vector);
        System.out.println("Размер: " + vector.size());
        System.out.println("Элемент [2]: " + vector.get(2));

        vector.remove(1);
        System.out.println("После remove(1): " + vector);
    }

    public static void example2_GrowthTest() {
        System.out.println("\n=== Тест роста capacity ===");

        MyVector vector = new MyVector();

        for (int i = 1; i <= 25; i++) {
            vector.add(i * 10);
            if (i == 10) {
                System.out.println("10 элементов: capacity удвоен до 20");
            }
            if (i == 20) {
                System.out.println("20 элементов: capacity удвоен до 40");
            }
        }

        System.out.println("Размер: " + vector.size());
    }

    public static void example3_ScoreTracking() {
        System.out.println("\n=== Учёт баллов студентов ===");

        MyVector scores = new MyVector();
        scores.add(85);
        scores.add(92);
        scores.add(78);
        scores.add(95);
        scores.add(88);

        int sum = 0;
        for (int i = 0; i < scores.size(); i++) {
            sum += scores.get(i);
        }
        double average = (double) sum / scores.size();
        System.out.println("Средний балл: " + average);
    }

    public static void example4_FilteringData() {
        System.out.println("\n=== Фильтрация чётных чисел ===");

        MyVector numbers = new MyVector();
        for (int i = 1; i <= 20; i++) {
            numbers.add(i);
        }

        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 != 0) {
                numbers.remove(i);
            }
        }

        System.out.println("Только чётные: " + numbers);
    }

    public static void example5_ExceptionHandling() {
        System.out.println("\n=== Обработка исключений ===");

        MyVector vector = new MyVector();
        vector.add(100);
        vector.add(200);
        vector.add(300);

        try {
            vector.get(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: индекс вне границ");
        }

        try {
            vector.remove(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ошибка: отрицательный индекс");
        }
    }

}
