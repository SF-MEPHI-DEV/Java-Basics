package ru.mephi.week7.lesson1.Examples;

public class ThrowExample {

    public static void main(String[] args) {
        System.out.println("=== Проверка возраста ===");
        try {
            checkAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Деление ===");
        try {
            divide(10, 0);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Платеж ===");
        try {
            processPayment(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Повторный выброс ===");
        try {
            processDataWithRethrow("invalid");
        } catch (Exception e) {
            System.out.println("Поймано в main: " + e.getMessage());
        }

        System.out.println("\n=== Успешные вызовы ===");
        try {
            checkAge(25);
            System.out.println("Возраст корректен");

            int result = divide(10, 2);
            System.out.println("Результат: " + result);

            processPayment(100);
            System.out.println("Платёж обработан");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void checkAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть отрицательным");
        }
        if (age < 18) {
            throw new IllegalArgumentException("Возраст меньше 18 лет");
        }
        if (age > 150) {
            throw new IllegalArgumentException("Возраст слишком большой");
        }
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Делитель не может быть равен нулю");
        }
        return a / b;
    }

    public static void processPayment(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("Сумма должна быть больше нуля");
        }
        if (amount > 1_000_000) {
            throw new IllegalArgumentException("Сумма превышает максимум: " + amount);
        }
    }

    public static void processDataWithRethrow(String data) {
        try {
            if (data == null || data.isEmpty()) {
                throw new IllegalArgumentException("Данные не могут быть пустыми");
            }
            if (data.equals("invalid")) {
                throw new RuntimeException("Недопустимые данные");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Локальная обработка: " + e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            System.out.println("Критическая ошибка");
            throw e;
        }
    }
}
