package ru.mephi.week7.lesson1.Examples;

public class MultipleCatchExample {

    public static void main(String[] args) {
        System.out.println("=== Несколько catch блоков ===");
        processArray(new int[]{1, 2, 3}, 5);
        processArray(new int[]{1, 2, 3}, 1);
        processArray(null, 0);

        System.out.println("\n=== Multi-catch (Java 7+) ===");
        testMultiCatch("123");
        testMultiCatch("abc");
        testMultiCatch(null);

        System.out.println("\n=== Иерархия исключений ===");
        testExceptionHierarchy(0);
        testExceptionHierarchy(1);
    }

    public static void processArray(int[] array, int index) {
        try {
            System.out.println("\nПолучение элемента по индексу " + index);
            int value = array[index];
            int result = 100 / value;
            System.out.println("Результат: " + result);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Неверный индекс: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Деление на ноль");
        } catch (NullPointerException e) {
            System.out.println("Массив равен null");
        }
    }

    public static void testMultiCatch(String numberStr) {
        try {
            System.out.println("\nОбработка: " + numberStr);
            int number = Integer.parseInt(numberStr);
            int result = 100 / number;
            System.out.println("Результат: " + result);
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println("Ошибка: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Строка равна null");
        }
    }

    public static void testExceptionHierarchy(int variant) {
        try {
            System.out.println("\nВариант: " + variant);
            if (variant == 0) {
                int result = 10 / 0;
            } else {
                throw new RuntimeException("Общее исключение");
            }
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("RuntimeException: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
