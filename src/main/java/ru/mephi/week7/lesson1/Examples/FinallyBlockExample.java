package ru.mephi.week7.lesson1.Examples;

import java.io.*;

public class FinallyBlockExample {

    public static void main(String[] args) {
        example1Normal();
        example2WithException();
        example3WithReturn();
        example4ResourceCleanup();
        example5TryWithResources();
    }

    public static void example1Normal() {
        try {
            System.out.println("Try: выполнение операции");
            int result = 10 / 2;
            System.out.println("Try: результат = " + result);
        } catch (Exception e) {
            System.out.println("Catch: обработка исключения");
        } finally {
            System.out.println("Finally: выполняется всегда");
        }
        System.out.println("Продолжение работы");
    }

    public static void example2WithException() {
        try {
            System.out.println("Try: начало");
            int result = 10 / 0;
            System.out.println("Try: эта строка не выполнится");
        } catch (ArithmeticException e) {
            System.out.println("Catch Arithmetic: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Catch exception: " + e.getMessage());
        } finally {
            System.out.println("Finally: выполняется даже после исключения");
        }
        System.out.println("Продолжение работы");
    }

    public static void example3WithReturn() {
        System.out.println("\nРезультат: " + getNumber());
    }

    private static int getNumber() {
        try {
            System.out.println("Try: возврат 10");
            return 10;
        } catch (Exception e) {
            System.out.println("Catch: исключение");
            return -1;
        } finally {
            System.out.println("Finally: выполняется перед return");
        }
    }

    public static void example4ResourceCleanup() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("test.txt"));
            String line = reader.readLine();
            System.out.println("Прочитано: " + line);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
        } finally {
            if (reader != null) {
                closeClosable(reader);
            }
        }
    }
    public static void closeClosable(Closeable closeable) {
        try {
            closeable.close();
            System.out.println("Reader закрыт");
        } catch (IOException e) {
            System.out.println("Ошибка закрытия");
        }
    }

    public static void example5TryWithResources() {
        try (BufferedReader reader = new BufferedReader(new StringReader("Строка для чтения"))) {
            String line = reader.readLine();
            System.out.println("Прочитано: " + line);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println("Ресурсы закрыты автоматически");
    }
}
