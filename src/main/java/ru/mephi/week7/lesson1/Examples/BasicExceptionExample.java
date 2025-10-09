package ru.mephi.week7.lesson1.Examples;

public class BasicExceptionExample {

    public static void main(String[] args) {
        System.out.println("=== ArithmeticException ===");
        try {
            int result = 10 / 0;
            System.out.println("Результат: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль");
            System.out.println("getMessage(): " + e.getMessage());
        }

        System.out.println("\n=== NullPointerException ===");
        try {
            String text = null;
            int length = text.length();
            //int result = 10 / 0;
            System.out.println("Длина: " + length);
        } catch (Exception e) {
            System.out.println("Арифметическая ошибка");
            System.out.println("getMessage(): " + e.getMessage());
            System.out.println("getClass(): " + e.getClass().getName());
        }
        System.out.println("\n=== ArrayIndexOutOfBoundsException ===");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println("Элемент: " + numbers[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Индекс за границами массива");
            System.out.println("Детали: " + e.getMessage());
        }

        System.out.println("\n=== Продолжение работы ===");
        System.out.println("Программа продолжает выполнение после обработки исключений");
    }
}
