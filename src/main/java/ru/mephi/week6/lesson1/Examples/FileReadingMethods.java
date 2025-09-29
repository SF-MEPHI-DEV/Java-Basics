package ru.mephi.week6.lesson1.Examples;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileReadingMethods {

    // Создаем тестовый файл для примеров
    private static void createTestFile() throws IOException {
        try (PrintWriter writer = new PrintWriter("test.txt")) {
            writer.println("Первая строка");
            writer.println("Вторая строка");
            writer.println("123 456 789");
            writer.println("Последняя строка");
        }
    }

    public static void main(String[] args) {
        try {
            createTestFile();

            System.out.println("=== Различные способы чтения файлов ===\n");

            // 1. FileReader + BufferedReader (построчное чтение)
            readWithBufferedReader();

            // 2. Scanner (удобный парсинг)
            readWithScanner();

            // 3. Files.readAllLines() (все строки сразу)
            readAllLines();

            // 4. Files.readString() (весь файл как строка, Java 11+)
            readAsString();

            // 5. FileInputStream (байтовое чтение)
            readWithFileInputStream();

            // 6. Scanner для чтения чисел
            readNumbersWithScanner();

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // 1. BufferedReader - лучший выбор для больших файлов, читает построчно
    private static void readWithBufferedReader() throws IOException {
        System.out.println("1. BufferedReader (эффективно для больших файлов):");
        try (BufferedReader reader = new BufferedReader(new FileReader("test.txt"))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }
        }
        System.out.println();
    }

    // 2. Scanner - удобно для парсинга и интерактивного ввода
    private static void readWithScanner() throws IOException {
        System.out.println("2. Scanner (удобно для парсинга):");
        try (Scanner scanner = new Scanner(new File("test.txt"))) {
            while (scanner.hasNextLine()) {
                System.out.println("Строка: " + scanner.nextLine());
            }
        }
        System.out.println();
    }

    // 3. Files.readAllLines() - удобно для небольших файлов
    private static void readAllLines() throws IOException {
        System.out.println("3. Files.readAllLines() (все строки в память):");
        List<String> lines = Files.readAllLines(Paths.get("test.txt"));
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) + ": " + lines.get(i));
        }
        System.out.println();
    }

    // 4. Files.readString() - весь файл как одна строка (Java 11+)
    private static void readAsString() throws IOException {
        System.out.println("4. Files.readString() (весь файл как строка):");
        try {
            String content = Files.readString(Paths.get("test.txt"));
            System.out.println("Содержимое файла:\n" + content);
        } catch (Exception e) {
            System.out.println("Метод недоступен в данной версии Java");
        }
        System.out.println();
    }

    // 5. FileInputStream - для байтового чтения
    private static void readWithFileInputStream() throws IOException {
        System.out.println("5. FileInputStream (байтовое чтение):");
        try (FileInputStream fis = new FileInputStream("test.txt")) {
            int byteValue;
            StringBuilder content = new StringBuilder();
            while ((byteValue = fis.read()) != -1) {
                content.append((char) byteValue);
            }
            System.out.println("Прочитано байтов: " + content.length());
            System.out.println("Содержимое: " + content.toString().substring(0, Math.min(50, content.length())) + "...");
        }
        System.out.println();
    }

    // 6. Чтение чисел с помощью Scanner
    private static void readNumbersWithScanner() throws IOException {
        try (PrintWriter writer = new PrintWriter("numbers.txt")) {
            writer.println("10 20 30");
            writer.println("40 50");
        }

        System.out.println("6. Scanner для чтения чисел:");
        try (Scanner scanner = new Scanner(new File("numbers.txt"))) {
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                System.out.println("Число: " + number);
            }
        }
        System.out.println();
    }
}