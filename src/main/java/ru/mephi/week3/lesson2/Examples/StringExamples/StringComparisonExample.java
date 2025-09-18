package ru.mephi.week3.lesson2.Examples.StringExamples;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

public class StringComparisonExample {
    public static void main(String[] args) {
        System.out.println("=== ПРИМЕРЫ СРАВНЕНИЯ СТРОК ===\n");

        System.out.println("1. == vs equals():");

        String str1 = "Привет";
        String str2 = "Привет";
        String str3 = new String("Привет");
        String str4 = new String("Привет");

        System.out.println("str1 = \"Привет\" (литерал)");
        System.out.println("str2 = \"Привет\" (литерал)");
        System.out.println("str3 = new String(\"Привет\")");
        System.out.println("str4 = new String(\"Привет\")");
        System.out.println();

        System.out.println("Ссылки (==):");
        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println("str1 == str3: " + (str1 == str3));
        System.out.println("str3 == str4: " + (str3 == str4));
        System.out.println();

        System.out.println("Сравнение содержимого (.equals()):");
        System.out.println("str1.equals(str2): " + str1.equals(str2));
        System.out.println("str1.equals(str3): " + str1.equals(str3));
        System.out.println("str3.equals(str4): " + str3.equals(str4));
        System.out.println();

        System.out.println("2. Регистр:");

        String word1 = "Программа";
        String word2 = "программа";
        String word3 = "ПРОГРАММА";
        String word4 = "ПрОгРаМмА";

        System.out.println("word1 = \"Программа\"");
        System.out.println("word2 = \"программа\"");
        System.out.println("word3 = \"ПРОГРАММА\"");
        System.out.println("word4 = \"ПрОгРаМмА\"");
        System.out.println();

        System.out.println("Сравнение с учетом регистра (.equals()):");
        System.out.println("word1.equals(word2): " + word1.equals(word2));
        System.out.println("word1.equals(word3): " + word1.equals(word3));
        System.out.println("word1.equals(word4): " + word1.equals(word4));
        System.out.println();

        System.out.println("Сравнение без учета регистра (.equalsIgnoreCase()):");
        System.out.println("word1.equalsIgnoreCase(word2): " + word1.equalsIgnoreCase(word2));
        System.out.println("word1.equalsIgnoreCase(word3): " + word1.equalsIgnoreCase(word3));
        System.out.println("word1.equalsIgnoreCase(word4): " + word1.equalsIgnoreCase(word4));
        System.out.println();

        // 3. Лексикографическое сравнение с compareTo()
        System.out.println("3. Лексикографическое сравнение с compareTo():");

        String[] words = {"яблоко", "банан", "вишня", "Яблоко", "Банан"};
        System.out.println("Слова: " + Arrays.toString(words));
        System.out.println();

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                int result = words[i].compareTo(words[j]);
                String comparison;
                if (result < 0) {
                    comparison = "меньше чем";
                } else if (result > 0) {
                    comparison = "больше чем";
                } else {
                    comparison = "равно";
                }
                System.out.printf("'%s'.compareTo('%s') = %d (%s)%n",
                        words[i], words[j], result, comparison);
            }
        }
        System.out.println();

        // 4. Лексикографическое сравнение без учета регистра
        System.out.println("4. Лексикографическое сравнение без учета регистра:");

        System.out.println("Сравнения без учета регистра:");
        String[] testWords = {"Яблоко", "банан", "Вишня"};
        for (int i = 0; i < testWords.length - 1; i++) {
            for (int j = i + 1; j < testWords.length; j++) {
                int result = testWords[i].compareToIgnoreCase(testWords[j]);
                String comparison;
                if (result < 0) {
                    comparison = "меньше чем";
                } else if (result > 0) {
                    comparison = "больше чем";
                } else {
                    comparison = "равно";
                }
                System.out.printf("'%s'.compareToIgnoreCase('%s') = %d (%s)%n",
                        testWords[i], testWords[j], result, comparison);
            }
        }
        System.out.println();

        System.out.println("5. Сравнение с null:");

        String nullStr = null;
        String validStr = "Привет";
        String emptyStr = "";

        System.out.println("nullStr = null");
        System.out.println("validStr = \"Привет\"");
        System.out.println("emptyStr = \"\"");
        System.out.println();

        System.out.println("Проверка null:");
        System.out.println("validStr.equals(nullStr): " + validStr.equals(nullStr));
        if (nullStr != null) {
            System.out.println("nullStr.equals(validStr): " + nullStr.equals(validStr));
        } else {
            System.out.println("nullStr.equals(validStr): null не может вызывать equals");
        }
        System.out.println();
        System.out.println("Безопасные сравнения:");
        System.out.println("safeEquals(nullStr, validStr): " + safeEquals(nullStr, validStr));
        System.out.println("safeEquals(nullStr, nullStr): " + safeEquals(nullStr, nullStr));
        System.out.println("safeEquals(validStr, emptyStr): " + safeEquals(validStr, emptyStr));
        System.out.println();

        // 6. Частичное сравнение строк
        System.out.println("6. Частичное сравнение строк:");

        String text = "Программирование на Java";
        System.out.println("Текст: " + text);
        System.out.println("startsWith(\"Программирование\"): " + text.startsWith("Программирование"));
        System.out.println("startsWith(\"Java\", 17): " + text.startsWith("Java", 17));
        System.out.println("endsWith(\"Java\"): " + text.endsWith("Java"));
        System.out.println("contains(\"на\"): " + text.contains("на"));
        System.out.println();

        // 7. Сопоставление шаблонов с matches()
        System.out.println("7. Сопоставление шаблонов с matches():");

        String[] testStrings = {"123", "абв", "123абв", "АБВ123", ""};
        System.out.println("Тестовые строки: " + Arrays.toString(testStrings));
        System.out.println();

        String[] patterns = {
                "\\d+",           // только цифры
                "[а-я]+",         // только строчные русские буквы
                "\\w+",           // словесные символы
                ".*\\d.*",        // содержит хотя бы одну цифру
                ".{3,}"           // минимум 3 символа
        };

        String[] patternNames = {
                "только цифры",
                "только строчные русские",
                "словесные символы",
                "содержит цифру",
                "минимум 3 символа"
        };

        for (int i = 0; i < patterns.length; i++) {
            System.out.println("Шаблон '" + patterns[i] + "' (" + patternNames[i] + "):");
            for (String testStr : testStrings) {
                System.out.printf("  '%s'.matches('%s'): %b%n",
                        testStr, patterns[i], testStr.matches(patterns[i]));
            }
            System.out.println();
        }

        // 8. Сравнение с учетом локали
        System.out.println("8. Сравнение с учетом локали:");
        localeSpecificComparison();
        System.out.println();

        // 9. Сравнение по длине
        System.out.println("9. Сравнение по длине:");
        String[] lengthTest = {"а", "ав", "авг", "авгд"};
        System.out.println("Строки: " + Arrays.toString(lengthTest));
        for (int i = 0; i < lengthTest.length - 1; i++) {
            for (int j = i + 1; j < lengthTest.length; j++) {
                int lengthDiff = lengthTest[i].length() - lengthTest[j].length();
                System.out.printf("'%s'.length() - '%s'.length() = %d%n",
                        lengthTest[i], lengthTest[j], lengthDiff);
            }
        }
        System.out.println();

        // 10. Сравнение производительности
        System.out.println("10. Сравнение производительности:");
        performanceComparison();
    }

    private static boolean safeEquals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        return str1.equals(str2);
    }

    private static void localeSpecificComparison() {
        String german1 = "ß";
        String german2 = "ss";
        String turkish1 = "i";
        String turkish2 = "İ";

        System.out.println("Немецкое сравнение (ß против ss):");
        System.out.println("Стандартное equals: " + german1.equals(german2));

        Collator germanCollator = Collator.getInstance(Locale.GERMAN);
        germanCollator.setStrength(Collator.PRIMARY);
        System.out.println("Немецкий Collator: " + (germanCollator.compare(german1, german2) == 0));
        System.out.println();

        System.out.println("Турецкое сравнение (i против İ):");
        System.out.println("Стандартное equalsIgnoreCase: " + turkish1.equalsIgnoreCase(turkish2));

        Locale turkishLocale = new Locale("tr", "TR");
        System.out.println("Турецкое toLowerCase: " + turkish2.toLowerCase(turkishLocale));
        System.out.println("Турецкое сравнение: " +
                turkish1.equals(turkish2.toLowerCase(turkishLocale)));
    }

    private static void performanceComparison() {
        String str1 = "Это тестовая строка для сравнения производительности";
        String str2 = "Это тестовая строка для сравнения производительности";
        String str3 = new String(str1);

        int iterations = 1000000;
        long startTime, endTime;

        // Тест производительности ==
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            boolean result = str1 == str2;
        }
        endTime = System.nanoTime();
        System.out.println("Время сравнения ==: " + (endTime - startTime) / 1_000_000 + " мс");

        // Тест производительности .equals() с одинаковой ссылкой
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            boolean result = str1.equals(str2);
        }
        endTime = System.nanoTime();
        System.out.println("Время .equals() с одинаковой ссылкой: " + (endTime - startTime) / 1_000_000 + " мс");

        // Тест производительности .equals() с разными ссылками
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            boolean result = str1.equals(str3);
        }
        endTime = System.nanoTime();
        System.out.println("Время .equals() с разными ссылками: " + (endTime - startTime) / 1_000_000 + " мс");

        // Тест производительности .compareTo()
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            int result = str1.compareTo(str3);
        }
        endTime = System.nanoTime();
        System.out.println("Время .compareTo(): " + (endTime - startTime) / 1_000_000 + " мс");
    }
}