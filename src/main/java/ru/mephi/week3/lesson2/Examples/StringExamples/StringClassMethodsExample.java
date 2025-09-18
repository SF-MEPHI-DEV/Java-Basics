package ru.mephi.week3.lesson2.Examples.StringExamples;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class StringClassMethodsExample {
    public static void main(String[] args) {
        System.out.println("=== ПРИМЕРЫ МЕТОДОВ КЛАССА STRING ===\n");

        System.out.println("1. Статические фабричные методы:");
        int number = 42;
        double decimal = 3.14159;
        boolean flag = true;
        char character = 'А';
        Object obj = new Object();

        System.out.println("valueOf(42): " + String.valueOf(number));
        System.out.println("valueOf(3.14): " + String.valueOf(decimal));
        System.out.println("valueOf(true): " + String.valueOf(flag));

        // join
        String[] words = {"Java", "это", "потрясающе"};
        System.out.println("String.join(\" \", words): " + String.join(" ", words));
        System.out.println("String.join(\"-\", words): " + String.join("-", words));
        System.out.println();

        // 2. Методы анализа символов
        System.out.println("2. Методы анализа символов:");
        String text = "Привет123 Мир!";
        System.out.println("Текст: '" + text + "'");

        System.out.println("length(): " + text.length());
        System.out.println("isEmpty(): " + text.isEmpty());
        System.out.println("isBlank(): " + text.isBlank());

        String emptyStr = "";
        String blankStr = "   ";
        System.out.println("Пустая строка isEmpty(): " + emptyStr.isEmpty());
        System.out.println("Строка с пробелами isBlank(): " + blankStr.isBlank());
        System.out.println();

        // 3. Методы доступа к символам
        System.out.println("3. Методы доступа к символам:");
        String sample = "Программирование";
        System.out.println("Образец: " + sample);
        System.out.println("charAt(0): " + sample.charAt(0));
        System.out.println("charAt(5): " + sample.charAt(5));

        // codePointAt - для Unicode
        System.out.println("codePointAt(0): " + sample.codePointAt(0));
        System.out.println("codePointCount(0, length): " + sample.codePointCount(0, sample.length()));

        // toCharArray
        char[] chars = sample.toCharArray();
        System.out.print("toCharArray(): [");
        for (int i = 0; i < chars.length; i++) {
            System.out.print("'" + chars[i] + "'");
            if (i < chars.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        // getChars
        char[] destination = new char[7];
        sample.getChars(0, 7, destination, 0);
        System.out.print("getChars(0, 7): [");
        for (int i = 0; i < destination.length; i++) {
            System.out.print("'" + destination[i] + "'");
            if (i < destination.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println();

        // 4. Методы сравнения
        System.out.println("4. Методы сравнения:");
        String str1 = "Яблоко";
        String str2 = "яблоко";
        String str3 = "Банан";

        System.out.println("str1: " + str1 + ", str2: " + str2 + ", str3: " + str3);
        System.out.println("str1.equals(str2): " + str1.equals(str2));
        System.out.println("str1.equalsIgnoreCase(str2): " + str1.equalsIgnoreCase(str2));
        System.out.println("str1.compareTo(str2): " + str1.compareTo(str2));
        System.out.println("str1.compareToIgnoreCase(str2): " + str1.compareToIgnoreCase(str2));
        System.out.println("str1.compareTo(str3): " + str1.compareTo(str3));
        System.out.println();

        // 5. Методы изменения регистра
        System.out.println("5. Методы изменения регистра:");
        String mixedCase = "Привет Мир 123!";
        System.out.println("Исходная: " + mixedCase);
        System.out.println("toLowerCase(): " + mixedCase.toLowerCase());
        System.out.println("toUpperCase(): " + mixedCase.toUpperCase());
        System.out.println("toLowerCase(Locale.ENGLISH): " + mixedCase.toLowerCase(Locale.ENGLISH));
        System.out.println();

        // 6. Методы обрезки и работы с пробелами
        System.out.println("6. Методы обрезки и работы с пробелами:");
        String whitespaceStr = "  \t  Привет Мир  \n  ";
        System.out.println("Исходная: '" + whitespaceStr + "'");
        System.out.println("trim(): '" + whitespaceStr.trim() + "'");
        System.out.println("strip(): '" + whitespaceStr.strip() + "'");
        System.out.println("stripLeading(): '" + whitespaceStr.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + whitespaceStr.stripTrailing() + "'");
        System.out.println();

        // 7. Методы подстрок
        System.out.println("7. Методы подстрок:");
        String longText = "Язык программирования Java";
        System.out.println("Текст: " + longText);
        System.out.println("substring(5): " + longText.substring(5));
        System.out.println("substring(5, 20): " + longText.substring(5, 20));

        // subSequence (возвращает CharSequence)
        CharSequence subSeq = longText.subSequence(5, 20);
        System.out.println("subSequence(5, 20): " + subSeq);
        System.out.println();

        // 8. Методы поиска
        System.out.println("8. Методы поиска:");
        String searchText = "Java программирование на Java";
        System.out.println("Текст: " + searchText);
        System.out.println("indexOf('а'): " + searchText.indexOf('а'));
        System.out.println("indexOf('а', 5): " + searchText.indexOf('а', 5));
        System.out.println("lastIndexOf('а'): " + searchText.lastIndexOf('а'));
        System.out.println("lastIndexOf('а', 15): " + searchText.lastIndexOf('а', 15));
        System.out.println("indexOf(\"Java\"): " + searchText.indexOf("Java"));
        System.out.println("lastIndexOf(\"Java\"): " + searchText.lastIndexOf("Java"));
        System.out.println("contains(\"программирование\"): " + searchText.contains("программирование"));
        System.out.println();

        // 9. Методы сопоставления шаблонов
        System.out.println("9. Методы сопоставления шаблонов:");
        String patternText = "Привет Мир";
        System.out.println("Текст: " + patternText);
        System.out.println("startsWith(\"Привет\"): " + patternText.startsWith("Привет"));
        System.out.println("startsWith(\"Мир\", 7): " + patternText.startsWith("Мир", 7));
        System.out.println("endsWith(\"Мир\"): " + patternText.endsWith("Мир"));
        System.out.println("matches(\".*ивет.*\"): " + patternText.matches(".*ивет.*"));
        System.out.println();

        // 10. Методы замены
        System.out.println("10. Методы замены:");
        String replaceText = "Привет Мир Привет";
        System.out.println("Исходная: " + replaceText);
        System.out.println("replace('и', 'И'): " + replaceText.replace('и', 'И'));
        System.out.println("replace(\"Привет\", \"Пока\"): " + replaceText.replace("Привет", "Пока"));
        System.out.println("replaceFirst(\"Привет\", \"Пока\"): " + replaceText.replaceFirst("Привет", "Пока"));
        System.out.println("replaceAll(\"[аеиоуыэюя]\", \"*\"): " + replaceText.replaceAll("[аеиоуыэюя]", "*"));
        System.out.println();

        // 11. Методы разделения
        System.out.println("11. Методы разделения:");
        String csvData = "яблоко,банан,вишня,дата,бузина";
        System.out.println("CSV: " + csvData);

        String[] fruits1 = csvData.split(",");
        System.out.print("split(\",\"): ");
        printStringArray(fruits1);

        String[] fruits2 = csvData.split(",", 3);
        System.out.print("split(\",\", 3): ");
        printStringArray(fruits2);
        System.out.println();

        // 12. Методы кодирования
        System.out.println("12. Методы кодирования:");
        String unicodeText = "Привет 世界";
        System.out.println("Текст: " + unicodeText);

        byte[] utf8Bytes = unicodeText.getBytes(StandardCharsets.UTF_8);
        byte[] asciiBytes = unicodeText.getBytes(StandardCharsets.US_ASCII);

        System.out.print("UTF-8 байты: ");
        printByteArray(utf8Bytes);
        System.out.print("ASCII байты: ");
        printByteArray(asciiBytes);

        // Создание строки из байтов
        String fromUtf8 = new String(utf8Bytes, StandardCharsets.UTF_8);
        System.out.println("Из UTF-8 байтов: " + fromUtf8);
        System.out.println();

        // 13. Метод intern
        System.out.println("13. Метод intern:");
        String str = new String("Привет").intern();
        String literal = "Привет";
        System.out.println("new String(\"Привет\").intern() == \"Привет\": " + (str == literal));
        System.out.println();

        // 14. Метод repeat (Java 11+)
        System.out.println("14. Метод repeat:");
        String repeatStr = "Java ";
        System.out.println("Исходная: '" + repeatStr + "'");
        System.out.println("repeat(3): '" + repeatStr.repeat(3) + "'");
        System.out.println("repeat(0): '" + repeatStr.repeat(0) + "'");
    }

    private static void printStringArray(String[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print("\"" + array[i] + "\"");
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    private static void printByteArray(byte[] array) {
        System.out.print("[");
        for (int i = 0; i < Math.min(array.length, 10); i++) {
            System.out.print(array[i]);
            if (i < Math.min(array.length, 10) - 1) System.out.print(", ");
        }
        if (array.length > 10) System.out.print("...");
        System.out.println("]");
    }
}