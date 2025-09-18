package ru.mephi.week3.lesson2.Examples.StringExamples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class RegexPatternMatcherExample {
    public static void main(String[] args) {
        System.out.println("=== ПРИМЕРЫ РЕГУЛЯРНЫХ ВЫРАЖЕНИЙ С PATTERN И MATCHER ===\n");

        System.out.println("1. Pattern и Matcher:");
        basicPatternMatching();
        System.out.println();

        System.out.println("2. Флаги:");
        patternFlags();
        System.out.println();


        System.out.println("3. Поиск всех:");
        findingAllMatches();
        System.out.println();

        System.out.println("4. Группы:");
        capturingGroups();
        System.out.println();

        System.out.println("5. Именованные группы:");
        namedCapturingGroups();
        System.out.println();

        System.out.println("6. Замена:");
        regexReplacement();
        System.out.println();

        // 7. Разделение строк с помощью Pattern
        System.out.println("7. Разделение строк с помощью Pattern:");
        patternSplitting();
        System.out.println();

        // 8. Пример проверки email
        System.out.println("8. Пример проверки email:");
        emailValidation();
        System.out.println();

        // 9. Извлечение номеров телефонов
        System.out.println("9. Извлечение номеров телефонов:");
        phoneNumberExtraction();
        System.out.println();

        // 10. Парсинг URL
        System.out.println("10. Парсинг URL:");
        urlParsing();
        System.out.println();

        // 11. Обработка лог-файлов
        System.out.println("11. Обработка лог-файлов:");
        logFileProcessing();
        System.out.println();

        // 12. Продвинутые возможности регулярных выражений
        System.out.println("12. Продвинутые возможности регулярных выражений:");
        advancedRegexFeatures();
        System.out.println();

        // 13. Соображения производительности
        System.out.println("13. Соображения производительности:");
        performanceConsiderations();
    }

    private static void basicPatternMatching() {
        String text = "Год 2023 был хорошим, но 2024 будет лучше!";
        String regex = "\\d{4}";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Текст: " + text);
        System.out.println("Шаблон: " + regex + " (4-значные числа)");
        System.out.println();

        System.out.println("Использование find() для поиска совпадений:");
        while (matcher.find()) {
            System.out.println("Найдено: '" + matcher.group() + "' в позиции " +
                             matcher.start() + "-" + matcher.end());
        }

        matcher.reset();
        System.out.println();
        System.out.println("Использование matches() для проверки всей строки: " + matcher.matches());

        Pattern exactPattern = Pattern.compile("\\d{4}");
        Matcher exactMatcher = exactPattern.matcher("2023");
        System.out.println("Точное совпадение для '2023': " + exactMatcher.matches());
    }

    private static void patternFlags() {
        String text = "Привет МИР привет мир ПРИВЕТ";

        System.out.println("Текст: " + text);
        System.out.println();

        // С учетом регистра (по умолчанию)
        Pattern caseSensitive = Pattern.compile("привет");
        Matcher csMatcher = caseSensitive.matcher(text);
        System.out.print("С учетом регистра 'привет': ");
        printMatches(csMatcher);

        // Без учета регистра
        Pattern caseInsensitive = Pattern.compile("привет", Pattern.CASE_INSENSITIVE);
        Matcher ciMatcher = caseInsensitive.matcher(text);
        System.out.print("Без учета регистра 'привет': ");
        printMatches(ciMatcher);

        // Несколько флагов
        String multilineText = "Строка 1: Привет\nСтрока 2: МИР\nСтрока 3: привет";
        Pattern multiFlag = Pattern.compile("^привет",
                                          Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
        Matcher mfMatcher = multiFlag.matcher(multilineText);
        System.out.println();
        System.out.println("Многострочный текст: " + multilineText.replace("\n", "\\n"));
        System.out.print("Шаблон '^привет' с CASE_INSENSITIVE | MULTILINE: ");
        printMatches(mfMatcher);
    }

    private static void findingAllMatches() {
        String text = "Свяжитесь с нами по адресу support@company.com или sales@company.org";
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Текст: " + text);
        System.out.println("Шаблон email: " + emailRegex);
        System.out.println();

        List<String> emails = new ArrayList<>();
        while (matcher.find()) {
            emails.add(matcher.group());
            System.out.println("Найден email: " + matcher.group() +
                             " в позиции " + matcher.start() + "-" + matcher.end());
        }

        System.out.println("Всего найдено email: " + emails.size());
        System.out.println("Email адреса: " + emails);
    }

    private static void capturingGroups() {
        String text = "Иван Петров (30), Анна Смирнова (25), Петр Иванов (35)";
        String regex = "(\\w+)\\s+(\\w+)\\s+\\((\\d+)\\)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Текст: " + text);
        System.out.println("Шаблон: " + regex);
        System.out.println("Группы: (имя) (фамилия) (возраст)");
        System.out.println();

        while (matcher.find()) {
            System.out.println("Полное совпадение: " + matcher.group(0));
            System.out.println("  Имя: " + matcher.group(1));
            System.out.println("  Фамилия: " + matcher.group(2));
            System.out.println("  Возраст: " + matcher.group(3));
            System.out.println("  Количество групп: " + matcher.groupCount());
            System.out.println();
        }
    }

    private static void namedCapturingGroups() {
        String text = "Товар: iPhone 15, Цена: 999$, Количество: 5";
        String regex = "Товар:\\s+(?<product>[^,]+),\\s+Цена:\\s+(?<price>\\d+)\\$,\\s+Количество:\\s+(?<quantity>\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Текст: " + text);
        System.out.println("Шаблон с именованными группами: " + regex);
        System.out.println();

        if (matcher.find()) {
            System.out.println("Использование именованных групп:");
            System.out.println("  Товар: " + matcher.group("product"));
            System.out.println("  Цена: " + matcher.group("price") + "$");
            System.out.println("  Количество: " + matcher.group("quantity"));
            System.out.println();

            System.out.println("Использование нумерованных групп:");
            System.out.println("  Товар: " + matcher.group(1));
            System.out.println("  Цена: " + matcher.group(2) + "$");
            System.out.println("  Количество: " + matcher.group(3));
        }
    }

    private static void regexReplacement() {
        String text = "Встреча назначена на 2023-12-25, а крайний срок 2024-01-15.";

        System.out.println("Исходный текст: " + text);
        System.out.println();

        // Простая замена
        String replaced1 = text.replaceAll("\\d{4}", "ГОД");
        System.out.println("Заменить годы на 'ГОД': " + replaced1);

        // Замена с группами
        String replaced2 = text.replaceAll("(\\d{4})-(\\d{2})-(\\d{2})", "$3/$2/$1");
        System.out.println("Преобразовать ГГГГ-ММ-ДД в ДД/ММ/ГГГГ: " + replaced2);

        // Использование Matcher для сложной замены
        Pattern pattern = Pattern.compile("(\\d{4})-(\\d{2})-(\\d{2})");
        Matcher matcher = pattern.matcher(text);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String year = matcher.group(1);
            String month = matcher.group(2);
            String day = matcher.group(3);
            String monthName = getMonthName(Integer.parseInt(month));
            matcher.appendReplacement(result, monthName + " " + day + ", " + year);
        }
        matcher.appendTail(result);

        System.out.println("Преобразовать в читаемый формат: " + result.toString());
    }

    private static void patternSplitting() {
        String csvData = "яблоко,банан;вишня:апельсин|виноград";
        String logData = "2023-12-25 10:30:45 ИНФО Приложение успешно запущено";

        System.out.println("CSV данные: " + csvData);
        Pattern csvPattern = Pattern.compile("[,;:|]");
        String[] fruits = csvPattern.split(csvData);
        System.out.println("Разделение по нескольким разделителям: ");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }
        System.out.println();

        System.out.println("Лог данные: " + logData);
        Pattern logPattern = Pattern.compile("\\s+");
        String[] logParts = logPattern.split(logData, 5);
        System.out.println("Разделить лог на максимум 5 частей:");
        for (int i = 0; i < logParts.length; i++) {
            System.out.println("  Часть " + i + ": " + logParts[i]);
        }
    }

    private static void emailValidation() {
        String[] emails = {
            "пользователь@example.com",
            "test.email@domain.co.uk",
            "неверный.email",
            "пользователь@домен",
            "user.name+tag@example.org",
            "неверный@.com",
            "valid123@test-domain.info"
        };
        String domainRegex = "[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*";
        String emailRegex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@"+domainRegex+"$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        System.out.println("Проверка email с помощью регулярных выражений:");
        System.out.println("Шаблон: " + emailRegex);
        System.out.println();

        for (String email : emails) {
            Matcher matcher = emailPattern.matcher(email);
            System.out.printf("%-30s : %s%n", email, matcher.matches() ? "ВЕРНЫЙ" : "НЕВЕРНЫЙ");
        }
    }

    private static void phoneNumberExtraction() {
        String text = "Контактные номера: (555) 123-4567, 555.123.4567, 555-123-4567, +7-555-123-4567, 5551234567";
        String phoneRegex = "(?:\\+7[-\\s]?)?\\(?([0-9]{3})\\)?[-\\s\\.]?([0-9]{3})[-\\s\\.]?([0-9]{4})";

        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Текст: " + text);
        System.out.println("Регулярное выражение для телефона: " + phoneRegex);
        System.out.println();

        System.out.println("Извлеченные номера телефонов:");
        while (matcher.find()) {
            String fullMatch = matcher.group(0);
            String areaCode = matcher.group(1);
            String exchange = matcher.group(2);
            String number = matcher.group(3);

            System.out.println("Найдено: " + fullMatch);
            System.out.println("  Отформатировано: (" + areaCode + ") " + exchange + "-" + number);
            System.out.println();
        }
    }

    private static void urlParsing() {
        String text = "Посетите https://www.example.com:8080/path/to/page?param=value#section или http://test.org";
        String urlRegex = "(https?)://([^/:]+)(?::(\\d+))?(/[^?#]*)?(?:\\?([^#]*))?(?:#(.*))?";

        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Текст: " + text);
        System.out.println("URL регулярное выражение: " + urlRegex);
        System.out.println();

        while (matcher.find()) {
            System.out.println("Найден URL: " + matcher.group(0));
            System.out.println("  Протокол: " + matcher.group(1));
            System.out.println("  Домен: " + matcher.group(2));
            System.out.println("  Порт: " + (matcher.group(3) != null ? matcher.group(3) : "по умолчанию"));
            System.out.println("  Путь: " + (matcher.group(4) != null ? matcher.group(4) : "/"));
            System.out.println("  Запрос: " + (matcher.group(5) != null ? matcher.group(5) : "нет"));
            System.out.println("  Фрагмент: " + (matcher.group(6) != null ? matcher.group(6) : "нет"));
            System.out.println();
        }
    }

    private static void logFileProcessing() {
        String[] logLines = {
            "2023-12-25 10:30:45 [ИНФО] Приложение успешно запущено",
            "2023-12-25 10:31:02 [ОШИБКА] Соединение с БД не удалось: таймаут",
            "2023-12-25 10:31:15 [ПРЕДУПРЕЖДЕНИЕ] Высокое использование памяти: 85%",
            "2023-12-25 10:32:00 [ОТЛАДКА] Обработка пользовательского запроса для ID: 12345"
        };

        String logRegex = "(\\d{4}-\\d{2}-\\d{2})\\s+(\\d{2}:\\d{2}:\\d{2})\\s+\\[(\\w+)\\]\\s+(.*)";
        Pattern pattern = Pattern.compile(logRegex);

        System.out.println("Обработка лог-файла:");
        System.out.println("Шаблон: " + logRegex);
        System.out.println();

        for (String logLine : logLines) {
            Matcher matcher = pattern.matcher(logLine);
            if (matcher.find()) {
                String date = matcher.group(1);
                String time = matcher.group(2);
                String level = matcher.group(3);
                String message = matcher.group(4);

                System.out.println("Запись лога:");
                System.out.println("  Дата: " + date);
                System.out.println("  Время: " + time);
                System.out.println("  Уровень: " + level);
                System.out.println("  Сообщение: " + message);
                System.out.println();
            }
        }
    }

    private static void advancedRegexFeatures() {
        String text = "Быстрая коричневая лиса прыгает через ленивую собаку";

        System.out.println("Текст: " + text);
        System.out.println();

        // Положительный просмотр вперед и назад
        Pattern lookahead = Pattern.compile("\\w+(?=\\s+коричневая)");
        Matcher la = lookahead.matcher(text);
        System.out.print("Слова, за которыми следует 'коричневая' (положительный просмотр вперед): ");
        printMatches(la);

        Pattern lookbehind = Pattern.compile("(?<=коричневая\\s)\\w+");
        Matcher lb = lookbehind.matcher(text);
        System.out.print("Слова, перед которыми 'коричневая' (положительный просмотр назад): ");
        printMatches(lb);

        // Незахватывающие группы
        Pattern nonCapturing = Pattern.compile("(?:быстрая|ленивая)\\s+(\\w+)");
        Matcher nc = nonCapturing.matcher(text);
        System.out.println();
        System.out.println("Незахватывающие группы - прилагательные + слова:");
        while (nc.find()) {
            System.out.println("  Полное совпадение: " + nc.group(0) + ", Захваченное слово: " + nc.group(1));
        }

        // Границы слов
        Pattern wordBoundary = Pattern.compile("\\bсобака\\b");
        String testText = "У собаки есть собачка";
        Matcher wb = wordBoundary.matcher(testText);
        System.out.println();
        System.out.println("Текст: " + testText);
        System.out.print("Граница слова \\bсобака\\b: ");
        printMatches(wb);

        // Ленивые квантификаторы
        String htmlText = "<div>содержимое1</div><div>содержимое2</div>";
        Pattern greedy = Pattern.compile("<div>.*</div>");
        Pattern reluctant = Pattern.compile("<div>.*?</div>");

        System.out.println();
        System.out.println("HTML: " + htmlText);
        System.out.print("Жадный <div>.*</div>: ");
        printMatches(greedy.matcher(htmlText));
        System.out.print("Ленивый <div>.*?</div>: ");
        printMatches(reluctant.matcher(htmlText));
    }

    private static void performanceConsiderations() {
        String text = "Это тестовая строка для проверки производительности с множеством слов для сопоставления";
        String regex = "\\b\\w{4,}\\b";

        // Компилировать шаблон один раз против множественной компиляции
        long startTime, endTime;
        int iterations = 100000;

        // Плохая практика - компилировать шаблон каждый раз
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            Pattern.compile(regex).matcher(text).find();
        }
        endTime = System.nanoTime();
        long badPracticeTime = (endTime - startTime) / 1_000_000;

        // Хорошая практика - компилировать шаблон один раз
        Pattern compiledPattern = Pattern.compile(regex);
        startTime = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            compiledPattern.matcher(text).find();
        }
        endTime = System.nanoTime();
        long goodPracticeTime = (endTime - startTime) / 1_000_000;

        System.out.println("Сравнение производительности для " + iterations + " итераций:");
        System.out.println("Компилировать шаблон каждый раз: " + badPracticeTime + " мс");
        System.out.println("Компилировать шаблон один раз: " + goodPracticeTime + " мс");
        System.out.println("Улучшение производительности: " + (badPracticeTime / (double) goodPracticeTime) + "x быстрее");
    }

    private static void printMatches(Matcher matcher) {
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        System.out.println(matches);
    }

    private static String getMonthName(int month) {
        String[] months = {"", "января", "февраля", "марта", "апреля", "мая", "июня",
                          "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        return months[month];
    }
}