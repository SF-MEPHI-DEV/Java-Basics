package ru.mephi.week5.lesson2.Examples;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class FileWritingMethods {
    public static void main(String[] args) {
        System.out.println("=== Различные способы записи в файлы ===\n");

        try {
            // 1. FileWriter (простая запись текста)
            writeWithFileWriter();

            // 2. BufferedWriter (эффективная запись)
            writeWithBufferedWriter();

            // 3. PrintWriter (форматированная запись)
            writeWithPrintWriter();

            // 4. Files.write() (современный способ)
            writeWithFiles();

            // 5. FileOutputStream (байтовая запись)
            writeWithFileOutputStream();

            // 6. Запись в файл с добавлением (append mode)
            appendToFile();

            // 7. Запись списка строк
            writeListOfStrings();

        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // 1. FileWriter - простой способ записи текста
    private static void writeWithFileWriter() throws IOException {
        System.out.println("1. FileWriter (простая запись):");
        try (FileWriter writer = new FileWriter("output_filewriter.txt")) {
            writer.write("Привет, мир!\n");
            writer.write("Вторая строка\n");
        }
        System.out.println("Файл записан через FileWriter");
        printFileContent("output_filewriter.txt");
        System.out.println();
    }

    // 2. BufferedWriter - эффективный для больших объемов данных
    private static void writeWithBufferedWriter() throws IOException {
        System.out.println("2. BufferedWriter (эффективная запись):");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output_buffered.txt"))) {
            writer.write("Строка 1");
            writer.newLine(); // Переход на новую строку
            writer.write("Строка 2");
            writer.newLine();
            writer.write("Строка 3");
        }
        System.out.println("Файл записан через BufferedWriter");
        printFileContent("output_buffered.txt");
        System.out.println();
    }

    // 3. PrintWriter - удобен для форматированного вывода
    private static void writeWithPrintWriter() throws IOException {
        System.out.println("3. PrintWriter (форматированный вывод):");
        try (PrintWriter writer = new PrintWriter("output_printwriter.txt")) {
            writer.println("Форматированная строка");
            writer.printf("Число: %d, Строка: %s%n", 42, "тест");
            writer.println("Последняя строка");
        }
        System.out.println("Файл записан через PrintWriter");
        printFileContent("output_printwriter.txt");
        System.out.println();
    }

    // 4. Files.write() - современный способ (Java 7+)
    private static void writeWithFiles() throws IOException {
        System.out.println("4. Files.write() (современный способ):");
        String content = "Содержимое через Files.write()\nВторая строка\nТретья строка";
        Files.write(Paths.get("output_files.txt"), content.getBytes());
        System.out.println("Файл записан через Files.write()");
        printFileContent("output_files.txt");
        System.out.println();
    }

    // 5. FileOutputStream - байтовая запись
    private static void writeWithFileOutputStream() throws IOException {
        System.out.println("5. FileOutputStream (байтовая запись):");
        try (FileOutputStream fos = new FileOutputStream("output_stream.txt")) {
            String data = "Данные через FileOutputStream\nВторая строка\n";
            fos.write(data.getBytes());
        }
        System.out.println("Файл записан через FileOutputStream");
        printFileContent("output_stream.txt");
        System.out.println();
    }

    // 6. Добавление в файл (append mode)
    private static void appendToFile() throws IOException {
        System.out.println("6. Добавление в существующий файл:");

        // Создаем исходный файл
        try (PrintWriter writer = new PrintWriter("append_example.txt")) {
            writer.println("Первоначальное содержимое");
        }

        // Добавляем в файл через FileWriter с append=true
        try (FileWriter writer = new FileWriter("append_example.txt", true)) {
            writer.write("Добавленная строка 1\n");
            writer.write("Добавленная строка 2\n");
        }

        // Добавляем через Files.write()
        String additionalContent = "Добавлено через Files.write()\n";
        Files.write(Paths.get("append_example.txt"),
                   additionalContent.getBytes(),
                   StandardOpenOption.APPEND);

        System.out.println("Содержимое после добавления:");
        printFileContent("append_example.txt");
        System.out.println();
    }

    // 7. Запись списка строк
    private static void writeListOfStrings() throws IOException {
        System.out.println("7. Запись списка строк:");
        List<String> lines = Arrays.asList(
            "Первая строка из списка",
            "Вторая строка из списка",
            "Третья строка из списка"
        );

        Files.write(Paths.get("output_list.txt"), lines);
        System.out.println("Список строк записан в файл");
        printFileContent("output_list.txt");
        System.out.println();
    }

    // Вспомогательный метод для вывода содержимого файла
    private static void printFileContent(String filename) throws IOException {
        System.out.println("Содержимое файла " + filename + ":");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  " + line);
            }
        }
    }
}