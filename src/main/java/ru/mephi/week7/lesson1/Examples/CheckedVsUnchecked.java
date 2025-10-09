package ru.mephi.week7.lesson1.Examples;

import java.io.*;

public class CheckedVsUnchecked {

    public static void main(String[] args) {
        System.out.println("=== Unchecked исключения ===");
        uncheckedExamples();

        System.out.println("\n=== Checked исключения ===");
        checkedExamples();

        System.out.println("\n=== Собственные исключения ===");
        customExceptionExamples();
    }

    public static void uncheckedExamples() {
        try {
            String text = null;
            System.out.println(text.length());
        } catch (NullPointerException e) {
            System.out.println("1. NullPointerException");
        }

        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("2. ArrayIndexOutOfBoundsException");
        }

        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("3. ArithmeticException");
        }

        try {
            int num = Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("4. NumberFormatException");
        }
    }

    public static void checkedExamples() {
        readFileMethod1();

        try {
            readFileMethod2();
        } catch (IOException e) {
            System.out.println("2. IOException: " + e.getMessage());
        }

        try {
            Class.forName("com.NonExistent");
        } catch (ClassNotFoundException e) {
            System.out.println("3. ClassNotFoundException");
        }

        //НЕ СКОМПИЛИРУЕТСЯ - checked исключение должно быть обработано!
        //FileReader reader = new FileReader("file.txt");
        //reader.close();

        // НЕ СКОМПИЛИРУЕТСЯ - метод объявляет checked исключение!
        // readFileMethod2();

        // НЕ СКОМПИЛИРУЕТСЯ - ClassNotFoundException это checked!
        // Class.forName("com.SomeClass");
    }

    public static void readFileMethod1() {
        try {
            FileReader reader = new FileReader("nonexistent.txt");
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("1. FileNotFoundException");
        } catch (IOException e) {
            System.out.println("1. IOException");
        }
    }

    public static void readFileMethod2() throws IOException {
        FileReader reader = new FileReader("nonexistent.txt");
        reader.close();
    }

    public static void customExceptionExamples() {
        try {
            throw new MyCheckedException("Checked исключение");
        } catch (MyCheckedException e) {
            System.out.println("Поймано: " + e.getMessage());
        }

        try {
            throw new MyUncheckedException("Unchecked исключение");
        } catch (MyUncheckedException e) {
            System.out.println("Поймано: " + e.getMessage());
        }
    }
}

class MyCheckedException extends Exception {
    public MyCheckedException(String message) {
        super(message);
    }
}

class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) {
        super(message);
    }
}
