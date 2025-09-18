package ru.mephi.week3.lesson2.Examples.StringExamples;

public class StringOperationsExample {
    public static void main(String[] args) {
        System.out.println("=== ПРИМЕРЫ ОПЕРАЦИЙ СО СТРОКАМИ ===\n");

        System.out.println("1. Создание строк:");
        String str1 = "Привет";
        String str2 = new String("Мир");
        String str3 = "Привет";

        char[] charArray = {'J', 'a', 'v', 'a'};
        String str4 = new String(charArray);

        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
        System.out.println("str3: " + str3);
        System.out.println("str4: " + str4);
        System.out.println();

        System.out.println("2. Конкатенация строк:");
        String concatenated1 = str1 + " " + str2;
        String concatenated2 = str1.concat(" ").concat(str2);
        StringBuilder sb = new StringBuilder();
        sb.append(str1).append(" ").append(str2);
        String concatenated3 = sb.toString();

        System.out.println("+ оператор: " + concatenated1);
        System.out.println("concat(): " + concatenated2);
        System.out.println("StringBuilder: " + concatenated3);
        System.out.println();

        System.out.println("3. Длина строки и доступ к символам:");
        String text = "Программирование";
        System.out.println("Текст: " + text);
        System.out.println("Длина: " + text.length());
        System.out.println("Символ по индексу 0: " + text.charAt(0));
        System.out.println("Символ по индексу 5: " + text.charAt(5));
        System.out.println("Первый символ: " + text.charAt(0));
        System.out.println("Последний символ: " + text.charAt(text.length() - 1));
        System.out.println();

        System.out.println("4. Операции с подстроками:");
        String sample = "Язык программирования Java";
        System.out.println("Исходная строка: " + sample);
        System.out.println("substring(0, 4): " + sample.substring(0, 4));
        System.out.println("substring(5): " + sample.substring(5));
        System.out.println("substring(5, 20): " + sample.substring(5, 20));
        System.out.println();

        // 5. Операции поиска в строке
        System.out.println("5. Операции поиска в строке:");
        String searchText = "Java великолепна, Java мощная";
        System.out.println("Текст: " + searchText);
        System.out.println("indexOf('a'): " + searchText.indexOf('a'));
        System.out.println("lastIndexOf('a'): " + searchText.lastIndexOf('a'));
        System.out.println("indexOf(\"Java\"): " + searchText.indexOf("Java"));
        System.out.println("lastIndexOf(\"Java\"): " + searchText.lastIndexOf("Java"));
        System.out.println("indexOf(\"Python\"): " + searchText.indexOf("Python"));
        System.out.println("contains(\"великолепна\"): " + searchText.contains("великолепна"));
        System.out.println("startsWith(\"Java\"): " + searchText.startsWith("Java"));
        System.out.println("endsWith(\"мощная\"): " + searchText.endsWith("мощная"));
        System.out.println();

        // 6. Модификация строк (возвращает новую строку)
        System.out.println("6. Модификация строк:");
        String original = "  Привет Мир  ";
        System.out.println("Исходная: '" + original + "'");
        System.out.println("toLowerCase(): " + original.toLowerCase());
        System.out.println("toUpperCase(): " + original.toUpperCase());
        System.out.println("trim(): '" + original.trim() + "'");
        System.out.println("replace('и', 'И'): " + original.replace('и', 'И'));
        System.out.println("replace(\"Мир\", \"Java\"): " + original.replace("Мир", "Java"));
        System.out.println("replaceAll(\"\\\\s+\", \"-\"): " + original.replaceAll("\\s+", "-"));
        System.out.println("replaceFirst(\"и\", \"И\"): " + original.replaceFirst("и", "И"));
        System.out.println();

        // 7. Разделение строк
        System.out.println("7. Разделение строк:");
        String csvData = "яблоко,банан,вишня,дата";
        String[] fruits = csvData.split(",");
        System.out.println("CSV данные: " + csvData);
        System.out.print("Результат разделения: [");
        for (int i = 0; i < fruits.length; i++) {
            System.out.print("\"" + fruits[i] + "\"");
            if (i < fruits.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        String sentence = "Быстрая коричневая лиса";
        String[] words = sentence.split(" ");
        System.out.println("Предложение: " + sentence);
        System.out.print("Слова: [");
        for (int i = 0; i < words.length; i++) {
            System.out.print("\"" + words[i] + "\"");
            if (i < words.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.println();

        // 8. Объединение строк
        System.out.println("8. Объединение строк:");
        String[] colors = {"красный", "зеленый", "синий", "желтый"};
        String joinedWithComma = String.join(", ", colors);
        String joinedWithPipe = String.join(" | ", colors);
        System.out.println("Массив: [красный, зеленый, синий, желтый]");
        System.out.println("Объединено запятыми: " + joinedWithComma);
        System.out.println("Объединено вертикальной чертой: " + joinedWithPipe);
        System.out.println();

        // 9. Форматирование строк
        System.out.println("9. Форматирование строк:");
        String name = "Алиса";
        int age = 25;
        double salary = 50000.75;

        String formatted1 = String.format("Имя: %s, Возраст: %d, Зарплата: %.2f", name, age, salary);
        String formatted2 = String.format("Шестнадцатеричное: %x, Восьмеричное: %o, Научное: %e", 255, 64, 1234.5);

        System.out.println("Форматированная строка 1: " + formatted1);
        System.out.println("Форматированная строка 2: " + formatted2);
        System.out.println();

        // 10. Проверка строк
        System.out.println("10. Проверка строк:");
        String empty = "";
        String whitespace = "   ";
        String nullString = null;
        String validString = "Привет";

        System.out.println("Тестирование строк:");
        testString("пустая строка", empty);
        testString("пробелы", whitespace);
        testString("null", nullString);
        testString("обычная строка", validString);
    }

    private static void testString(String description, String str) {
        System.out.println(description + ":");
        System.out.println("  является null: " + (str == null));
        System.out.println("  isEmpty(): " + (str != null && str.isEmpty()));
        System.out.println("  isBlank(): " + (str != null && str.isBlank()));
        System.out.println("  длина: " + (str != null ? str.length() : "Н/Д"));
        System.out.println();
    }
}