package ru.mephi.week7.lesson1.Examples;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Scanner;

public class TryWithResourcesExample {

    public static void main(String[] args) {
        example1_BasicUsage();
        example2_MultipleResources();
        example3_ResourceOrder();
        example4_ExceptionHandling();
        example5_CustomResource();
        example6_MyResource();
        example7_FileCopy();
        example8_Scanner();
        example9_SuppressedExceptions();
    }

    public static void example1_BasicUsage() {
        System.out.println("=== Пример 1: Базовое использование ===\n");

        System.out.println("Старый способ:");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new StringReader("Line 1\nLine 2\nLine 3"));
            String line = reader.readLine();
            System.out.println("Прочитано: " + line);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    System.out.println("Reader закрыт вручную");
                } catch (IOException e) {
                    System.out.println("Ошибка закрытия: " + e.getMessage());
                }
            }
        }

        System.out.println("\nНовый способ (try-with-resources):");
        try (BufferedReader reader2 = new BufferedReader(
                new StringReader("Line 1\nLine 2\nLine 3"))) {
            String line = reader2.readLine();
            System.out.println("Прочитано: " + line);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println("Reader закрыт автоматически\n");
    }

    public static void example2_MultipleResources() {
        System.out.println("=== Пример 2: Несколько ресурсов ===\n");

        try (FileWriter writer = new FileWriter("temp1.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {

            bufferedWriter.write("Строка 1\n");
            bufferedWriter.write("Строка 2\n");
            System.out.println("Записано в файл");

        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println("Оба ресурса закрыты автоматически\n");
    }

    public static void example3_ResourceOrder() {
        System.out.println("=== Пример 3: Порядок закрытия ресурсов ===\n");

        try (ResourceA a = new ResourceA("A");
             ResourceB b = new ResourceB("B");
             ResourceC c = new ResourceC("C")) {

            System.out.println("Использование ресурсов");

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println("Ресурсы закрываются в обратном порядке (C, B, A)\n");
    }

    public static void example4_ExceptionHandling() {
        System.out.println("=== Пример 4: Обработка исключений ===\n");

        System.out.println("Исключение в try:");
        try (BufferedReader reader = new BufferedReader(
                new FileReader("nonexistent.txt"))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        }

        System.out.println("Ресурс закрыт даже при исключении\n");
    }

    public static void example5_CustomResource() {
        System.out.println("=== Пример 5: Собственный ресурс ===\n");

        try (DatabaseConnection conn = new DatabaseConnection("localhost", 5432)) {
            conn.executeQuery("SELECT * FROM users");
            conn.executeQuery("SELECT * FROM orders");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println();
    }

    public static void example6_MyResource() {
        System.out.println("=== Пример 6: MyResource ===\n");

        System.out.println("Один ресурс:");
        try (MyResource resource = new MyResource("TestResource")) {
            resource.doSomething();
        }

        System.out.println("\nНесколько ресурсов:");
        try (MyResource r1 = new MyResource("First");
             MyResource r2 = new MyResource("Second");
             MyResource r3 = new MyResource("Third")) {

            r1.doSomething();
            r2.doSomething();
            r3.doSomething();
        }
        System.out.println();
    }

    public static void example7_FileCopy() {
        System.out.println("=== Пример 7: Копирование файла ===\n");

        try (FileWriter fw = new FileWriter("source.txt")) {
            fw.write("Это исходный файл\n");
            fw.write("Со строкой 2\n");
            fw.write("И строкой 3\n");
        } catch (IOException e) {
            System.out.println("Ошибка создания: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader("source.txt"));
             BufferedWriter writer = new BufferedWriter(
                     new FileWriter("destination.txt"))) {

            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
                lineCount++;
            }

            System.out.println("Скопировано строк: " + lineCount);

        } catch (IOException e) {
            System.out.println("Ошибка копирования: " + e.getMessage());
        }
        System.out.println();
    }

    public static void example8_Scanner() {
        System.out.println("=== Пример 8: Scanner ===\n");

        String input = "10 20 30 40 50";

        try (Scanner scanner = new Scanner(input)) {
            int sum = 0;
            while (scanner.hasNextInt()) {
                sum += scanner.nextInt();
            }
            System.out.println("Сумма: " + sum);
        }
        System.out.println("Scanner закрыт автоматически\n");
    }

    public static void example9_SuppressedExceptions() {
        System.out.println("=== Пример 9: Подавленные исключения ===\n");

        try (FailingResource resource = new FailingResource()) {
            System.out.println("Использование ресурса");
            resource.doWork();
        } catch (Exception e) {
            System.out.println("Основное исключение: " + e.getMessage());

            Throwable[] suppressed = e.getSuppressed();
            if (suppressed.length > 0) {
                System.out.println("Подавленные исключения:");
                for (Throwable t : suppressed) {
                    System.out.println("  - " + t.getMessage());
                }
            }
        }
        System.out.println();
    }
}

class ResourceA implements AutoCloseable {
    private final String name;

    public ResourceA(String name) {
        this.name = name;
        System.out.println("ResourceA (" + name + ") создан");
    }

    @Override
    public void close() {
        System.out.println("ResourceA (" + name + ") закрыт");
    }
}

class ResourceB implements AutoCloseable {
    private final String name;

    public ResourceB(String name) {
        this.name = name;
        System.out.println("ResourceB (" + name + ") создан");
    }

    @Override
    public void close() {
        System.out.println("ResourceB (" + name + ") закрыт");
    }
}

class ResourceC implements AutoCloseable {
    private final String name;

    public ResourceC(String name) {
        this.name = name;
        System.out.println("ResourceC (" + name + ") создан");
    }

    @Override
    public void close() {
        System.out.println("ResourceC (" + name + ") закрыт");
    }
}

class DatabaseConnection implements AutoCloseable {
    private final String host;
    private final int port;
    private boolean connected;

    public DatabaseConnection(String host, int port) {
        this.host = host;
        this.port = port;
        this.connected = true;
        System.out.println("Подключение к " + host + ":" + port);
    }

    public void executeQuery(String sql) {
        if (!connected) {
            throw new IllegalStateException("Соединение закрыто");
        }
        System.out.println("Выполнение: " + sql);
    }

    @Override
    public void close() {
        if (connected) {
            System.out.println("Закрытие соединения с " + host + ":" + port);
            connected = false;
        }
    }
}

class FailingResource implements AutoCloseable {
    public void doWork() throws Exception {
        throw new Exception("Ошибка при работе");
    }

    @Override
    public void close() throws Exception {
        throw new Exception("Ошибка при закрытии");
    }
}
