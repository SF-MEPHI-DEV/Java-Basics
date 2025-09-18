package ru.mephi.week3.lesson2.Examples.StringExamples;

public class SpecialSymbolsExample {
    public static void main(String[] args) {
        System.out.println("=== ПРИМЕРЫ СПЕЦИАЛЬНЫХ СИМВОЛОВ В СТРОКАХ ===\n");

        System.out.println("1. Escape символы:");

        String newline = "Первая строка\nВторая строка";
        String tab = "Колонка1\tКолонка2\tКолонка3";

        System.out.println("\\n:");
        System.out.println(newline);
        System.out.println();


        System.out.println("\\t:");
        System.out.println(tab);
        System.out.println();
        System.out.println();

        System.out.println("2. Кавычки:");

        String singleQuote = "Это прекрасный день";
        String doubleQuote = "Он сказал: \"Привет Мир!\"";
        String bothQuotes = "Смесь 'одинарных' и \"двойных\" кавычек";
        String escapedQuotes = "Она сказала: \"Это потрясающе!\"";

        System.out.println("Одинарная кавычка в строке: " + singleQuote);
        System.out.println("Двойная кавычка (\\\"): " + doubleQuote);
        System.out.println("Обе кавычки: " + bothQuotes);
        System.out.println("Экранированные кавычки: " + escapedQuotes);
        System.out.println();

        // 3. Экранирование обратного слеша
        System.out.println("3. Экранирование обратного слеша:");

        String backslash = "Путь к файлу: C:\\Users\\Documents\\file.txt";
        String doubleBackslash = "Сетевой путь: \\\\server\\share\\folder";
        String rawString = "Регулярное выражение: \\d{3}-\\d{2}-\\d{4}";

        System.out.println("Одинарный обратный слеш (\\\\): " + backslash);
        System.out.println("Двойной обратный слеш: " + doubleBackslash);
        System.out.println("Строка с обратными слешами: " + rawString);
        System.out.println();

        // 4. Unicode escape-последовательности
        System.out.println("4. Unicode escape-последовательности:");

        String unicodeHeart = "Я люблю Java \u2665";
        String unicodeArrow = "Направление: \u2192";
        String unicodeMath = "Символ пи: \u03C0, Бесконечность: \u221E";
        String unicodeCurrency = "Валюты: \u0024 \u00A3 \u20AC \u00A5";
        String unicodeEmoji = "Эмодзи: \uD83D\uDE00 \uD83C\uDF89 \uD83C\uDF86";

        System.out.println("Unicode сердце: " + unicodeHeart);
        System.out.println("Unicode стрелка: " + unicodeArrow);
        System.out.println("Unicode математические символы: " + unicodeMath);
        System.out.println("Unicode валюты: " + unicodeCurrency);
        System.out.println("Unicode эмодзи: " + unicodeEmoji);
        System.out.println();

        // 5. Восьмеричные escape-последовательности
        System.out.println("5. Восьмеричные escape-последовательности:");

        String octalA = "\101";  // Восьмеричное 101 = 'A'
        String octalNewline = "\012";  // Восьмеричное 012 = перевод строки
        String octalTab = "\011";  // Восьмеричное 011 = табуляция

        System.out.println("Восьмеричное 101 (A): " + octalA);
        System.out.println("Восьмеричное 012 (перевод строки): До" + octalNewline + "После");
        System.out.println("Восьмеричное 011 (табуляция): До" + octalTab + "После");
        System.out.println();

        // 6. Управляющие символы
        System.out.println("6. Управляющие символы:");

        String bell = "Звуковой сигнал: \u0007";
        String verticalTab = "Вертикальная\u000Bтабуляция";
        String nullChar = "Нулевой символ: \u0000 (между словами)";

        System.out.println("Символ звонка: " + bell);
        System.out.println("Вертикальная табуляция: " + verticalTab);
        System.out.println("Нулевой символ: " + nullChar);
        System.out.println();

        // 7. HTML/XML специальные символы
        System.out.println("7. HTML/XML специальные символы:");

        String htmlEntities = "HTML: &lt;тег&gt; содержимое &amp; больше";
        String xmlContent = "XML: <элемент атрибут=\"значение\">текст</элемент>";
        String escapedHtml = "Экранированное: &quot;Привет &amp; Добро пожаловать&quot;";

        System.out.println("Представление HTML сущностей: " + htmlEntities);
        System.out.println("XML содержимое: " + xmlContent);
        System.out.println("Экранированный HTML: " + escapedHtml);
        System.out.println();

        // 8. Примеры разделителей строк
        System.out.println("8. Примеры разделителей строк:");

        String multilineText = "Строка 1" + System.lineSeparator() +
                              "Строка 2" + System.lineSeparator() +
                              "Строка 3";

        String windowsStyle = "Строка 1\r\nСтрока 2\r\nСтрока 3";
        String unixStyle = "Строка 1\nСтрока 2\nСтрока 3";
        String macStyle = "Строка 1\rСтрока 2\rСтрока 3";

        System.out.println("Системный разделитель строк:");
        System.out.println(multilineText);
        System.out.println();

        System.out.println("Стиль Windows (\\r\\n):");
        System.out.println(windowsStyle);
        System.out.println();

        System.out.println("Стиль Unix (\\n):");
        System.out.println(unixStyle);
        System.out.println();

        // 9. Специальные символы в операциях со строками
        System.out.println("9. Специальные символы в операциях со строками:");

        String textWithSpecials = "Привет\tМир\nКак дела?";

        System.out.println("Исходная строка со специальными символами:");
        System.out.println("'" + textWithSpecials + "'");
        System.out.println();

        System.out.println("Длина: " + textWithSpecials.length());
        System.out.println("Индекс табуляции: " + textWithSpecials.indexOf('\t'));
        System.out.println("Индекс перевода строки: " + textWithSpecials.indexOf('\n'));
        System.out.println();

        System.out.println("Замена табуляции на пробел:");
        System.out.println("'" + textWithSpecials.replace('\t', ' ') + "'");
        System.out.println();

        System.out.println("Замена перевода строки на пробел:");
        System.out.println("'" + textWithSpecials.replace('\n', ' ') + "'");
        System.out.println();

        // 10. Экранирование в регулярных выражениях
        System.out.println("10. Экранирование в регулярных выражениях:");

        String regexText = "Цена: $123.45 (скидка: 10%)";

        System.out.println("Текст: " + regexText);
        System.out.println("Содержит знак доллара: " + regexText.matches(".*\\$.*"));
        System.out.println("Содержит скобки: " + regexText.matches(".*\\(.*\\).*"));
        System.out.println("Содержит процент: " + regexText.matches(".*\\d+%.*"));
        System.out.println();

        // 11. Примеры текстовых блоков (Java 13+)
        System.out.println("11. Примеры текстовых блоков:");

        String textBlock = """
                Это текстовый блок
                с несколькими строками
                и "кавычки" не нужно экранировать
                если только это не тройные кавычки
                """;

        String jsonExample = """
                {
                    "имя": "Иван",
                    "возраст": 30,
                    "город": "Москва"
                }
                """;

        System.out.println("Текстовый блок:");
        System.out.println(textBlock);

        System.out.println("JSON в текстовом блоке:");
        System.out.println(jsonExample);

        // 12. Анализ кодов символов
        System.out.println("12. Анализ кодов символов:");
        analyzeCharacterCodes();
    }

    private static void analyzeCharacterCodes() {
        String specialChars = "А\t\n\r\"\\";

        System.out.println("Анализ кодов символов:");
        for (int i = 0; i < specialChars.length(); i++) {
            char ch = specialChars.charAt(i);
            System.out.printf("Индекс %d: '%c' (Unicode: U+%04X, Десятичное: %d)%n",
                    i,
                    Character.isISOControl(ch) ? '?' : ch,
                    (int) ch,
                    (int) ch);
        }
        System.out.println();

        // Пример суррогатных пар Unicode
        String emojiString = "😀🎉";
        System.out.println("Анализ эмодзи: " + emojiString);
        System.out.println("Длина строки: " + emojiString.length());
        System.out.println("Количество кодовых точек: " + emojiString.codePointCount(0, emojiString.length()));

        for (int i = 0; i < emojiString.length(); ) {
            int codePoint = emojiString.codePointAt(i);
            System.out.printf("Кодовая точка: U+%X (%s)%n",
                    codePoint,
                    new String(Character.toChars(codePoint)));
            i += Character.charCount(codePoint);
        }
    }
}