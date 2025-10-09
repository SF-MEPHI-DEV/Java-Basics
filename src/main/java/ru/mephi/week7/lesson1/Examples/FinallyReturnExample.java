package ru.mephi.week7.lesson1.Examples;

public class FinallyReturnExample {

    public static void main(String[] args) {
        System.out.println("=== Пример 1: Return в finally перезаписывает результат ===");
        int result1 = example1_ReturnOverride();
        System.out.println("Результат: " + result1);
        System.out.println("Ожидали: 10, получили: " + result1 + "\n");

        System.out.println("=== Пример 2: Return в finally перезаписывает catch ===");
        int result2 = example2_CatchOverride();
        System.out.println("Результат: " + result2);
        System.out.println("Ожидали: -1 (ошибка), получили: " + result2 + "\n");

        System.out.println("=== Пример 3: Return в finally проглатывает исключение ===");
        try {
            int result3 = example3_ExceptionSwallowed();
            System.out.println("Результат: " + result3);
            System.out.println("ОШИБКА: исключение не выброшено!\n");
        } catch (RuntimeException e) {
            System.out.println("Поймано: " + e.getMessage());
        }

        System.out.println("=== Пример 4: Правильное использование finally ===");
        int result4 = example4_CorrectUsage();
        System.out.println("Результат: " + result4);
        System.out.println("Корректно: получили ожидаемое значение\n");

        System.out.println("=== Пример 5: Finally изменяет изменяемый объект ===");
        example5_MutableObject();
    }

    // Пример 1: Return в finally перезаписывает return из try
    public static int example1_ReturnOverride() {
        try {
            System.out.println("Try: возвращаю 10");
            return 10;
        } finally {
            System.out.println("Finally: возвращаю 20");
            return 20;  // ПЛОХО: перезаписывает 10
        }
    }

    // Пример 2: Return в finally перезаписывает return из catch
    public static int example2_CatchOverride() {
        try {
            System.out.println("Try: делю на 0");
            int result = 10 / 0;
            return result;
        } catch (ArithmeticException e) {
            System.out.println("Catch: обработка ошибки, возвращаю -1");
            return -1;
        } finally {
            System.out.println("Finally: возвращаю 999");
            return 999;  // ПЛОХО: перезаписывает -1
        }
    }

    // Пример 3: Return в finally проглатывает исключение
    public static int example3_ExceptionSwallowed() {
        try {
            System.out.println("Try: выбрасываю RuntimeException");
            throw new RuntimeException("Критическая ошибка!");
        } finally {
            System.out.println("Finally: возвращаю 0");
            return 0;  // ОЧЕНЬ ПЛОХО: исключение потеряно!
        }
    }

    // Пример 4: Правильное использование - НЕТ return в finally
    public static int example4_CorrectUsage() {
        int result = 0;
        try {
            System.out.println("Try: вычисление");
            result = 42;
            return result;
        } finally {
            System.out.println("Finally: очистка ресурсов (без return)");
            // Здесь только очистка, НЕТ return
        }
    }

    // Пример 5: Finally может изменить изменяемый объект (но не примитив)
    public static void example5_MutableObject() {
        System.out.println("\nВозврат изменяемого объекта:");
        StringBuilder sb = getMutableObject();
        System.out.println("Результат: \"" + sb.toString() + "\"");
        System.out.println("Finally изменил объект (добавил текст)\n");
    }

    private static StringBuilder getMutableObject() {
        StringBuilder sb = new StringBuilder();
        try {
            System.out.println("Try: создаю StringBuilder с 'Hello'");
            sb.append("Hello");
            return sb;
        } finally {
            System.out.println("Finally: добавляю ' World'");
            sb.append(" World");  // Изменяет объект, который уже возвращается
            // НЕТ return - это правильно
        }
    }
}
