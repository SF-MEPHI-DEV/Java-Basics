package ru.mephi.week7.lesson1.Examples;

import java.io.IOException;

public class ExceptionMethodsExample {

    public static void main(String[] args) {
        System.out.println("=== Методы Exception ===\n");

        example1BasicMethods();
        example2PrintStackTrace();
        example3GetStackTrace();
        example4ExceptionChaining();
        example5SuppressedExceptions();
    }

    public static void example1BasicMethods() {
        System.out.println("--- getMessage() и toString() ---");
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("getMessage(): " + e.getMessage());
            System.out.println("toString(): " + e.toString());
            System.out.println("getClass(): " + e.getClass().getName());
            System.out.println("getSimpleName(): " + e.getClass().getSimpleName());
        }
        System.out.println();
    }

    public static void example2PrintStackTrace() {
        System.out.println("--- printStackTrace() ---");
        try {
            method1();
        } catch (Exception e) {
            System.out.println("Сообщение: " + e.getMessage());
            System.out.println("\nСтек вызовов:");
            e.printStackTrace();
        }
        System.out.println();
    }

    private static void method1() {
        method2();
    }

    private static void method2() {
        method3();
    }

    private static void method3() {
        throw new RuntimeException("Ошибка в method3");
    }

    public static void example3GetStackTrace() {
        System.out.println("--- getStackTrace() ---");
        try {
            methodA();
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (int i = 0; i < Math.min(stackTrace.length, 3); i++) {
                StackTraceElement element = stackTrace[i];
                System.out.println(i + ". " + element.getClassName());
                System.out.println("   " + element.getMethodName());
                System.out.println("   " + element.getFileName() + ":" + element.getLineNumber());
            }
        }
        System.out.println();
    }

    private static void methodA() {
        methodB();
    }

    private static void methodB() {
        methodC();
    }

    private static void methodC() {
        throw new IllegalStateException("Ошибка состояния");
    }

    public static void example4ExceptionChaining() {
        System.out.println("--- Цепочка исключений ---");
        try {
            processOrder();
        } catch (Exception e) {
            System.out.println("Верхнеуровневое:");
            System.out.println("  " + e.getClass().getSimpleName());
            System.out.println("  " + e.getMessage());

            Throwable cause = e.getCause();
            if (cause != null) {
                System.out.println("\nПричина:");
                System.out.println("  " + cause.getClass().getSimpleName());
                System.out.println("  " + cause.getMessage());

                Throwable rootCause = cause.getCause();
                if (rootCause != null) {
                    System.out.println("\nКорневая причина:");
                    System.out.println("  " + rootCause.getClass().getSimpleName());
                    System.out.println("  " + rootCause.getMessage());
                }
            }
        }
        System.out.println();
    }

    private static void processOrder() throws Exception {
        try {
            connectToDatabase();
        } catch (IOException e) {
            throw new Exception("Не удалось обработать заказ", e);
        }
    }

    private static void connectToDatabase() throws IOException {
        try {
            openConnection();
        } catch (RuntimeException e) {
            throw new IOException("Ошибка подключения к БД", e);
        }
    }

    private static void openConnection() {
        throw new RuntimeException("Таймаут соединения");
    }

    public static void example5SuppressedExceptions() {
        System.out.println("--- Подавленные исключения ---");
        try {
            tryWithResourcesExample();
        } catch (Exception e) {
            System.out.println("Основное: " + e.getMessage());

            Throwable[] suppressed = e.getSuppressed();
            if (suppressed.length > 0) {
                System.out.println("\nПодавленные:");
                for (int i = 0; i < suppressed.length; i++) {
                    System.out.println((i + 1) + ". " + suppressed[i].getMessage());
                }
            }
        }
        System.out.println();
    }

    private static void tryWithResourcesExample() throws Exception {
        try (ProblematicResource resource = new ProblematicResource()) {
            resource.use();
        }
    }
}

class ProblematicResource implements AutoCloseable {
    public void use() throws Exception {
        throw new Exception("Ошибка при использовании");
    }

    @Override
    public void close() throws Exception {
        throw new Exception("Ошибка при закрытии");
    }
}
