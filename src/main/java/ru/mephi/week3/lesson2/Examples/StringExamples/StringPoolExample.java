package ru.mephi.week3.lesson2.Examples.StringExamples;

public class StringPoolExample {
    public static void main(String[] args) {
        System.out.println("=== ПРИМЕРЫ РАБОТЫ С ПУЛОМ СТРОК ===\n");

        System.out.println("1. Литералы vs new String():");

        String literal1 = "Привет";
        String literal2 = "Привет";

        String newString1 = new String("Привет");
        String newString2 = new String("Привет");

        System.out.println("String literal1 = \"Привет\"");
        System.out.println("String literal2 = \"Привет\"");
        System.out.println("String newString1 = new String(\"Привет\")");
        System.out.println("String newString2 = new String(\"Привет\")");
        System.out.println();

        System.out.println("Ссылки (==):");
        System.out.println("literal1 == literal2: " + (literal1 == literal2));
        System.out.println("newString1 == newString2: " + (newString1 == newString2));
        System.out.println("literal1 == newString1: " + (literal1 == newString1));
        System.out.println();

        literal1.equals(newString1);

        System.out.println("Сравнение содержимого (.equals()):");
        System.out.println("literal1.equals(literal2): " + literal1.equals(literal2));
        System.out.println("newString1.equals(newString2): " + newString1.equals(newString2));
        System.out.println("literal1.equals(newString1): " + literal1.equals(newString1));
        System.out.println();

        System.out.println("2. Метод intern():");

        String internedString = newString1.intern();
        System.out.println("String internedString = newString1.intern()");
        System.out.println("literal1 == internedString: " + (literal1 == internedString));
        System.out.println("newString1 == internedString: " + (newString1 == internedString));
        System.out.println();

        System.out.println("3. Конкатенация и пул:");

        String str1 = "Java";
        String str2 = "Программирование";

        String compileTime1 = "Java" + "Программирование";
        String compileTime2 = "JavaПрограммирование";

        // Конкатенация во время выполнения
        String runtime1 = str1 + str2;
        String runtime2 = str1.concat(str2);

        System.out.println("Конкатенация на этапе компиляции:");
        System.out.println("\"Java\" + \"Программирование\" == \"JavaПрограммирование\": " + (compileTime1 == compileTime2));
        System.out.println();

        System.out.println("Конкатенация во время выполнения:");
        System.out.println("str1 + str2 == \"JavaПрограммирование\": " + (runtime1 == compileTime2));
        System.out.println("str1.concat(str2) == \"JavaПрограммирование\": " + (runtime2 == compileTime2));
        System.out.println("(str1 + str2).intern() == \"JavaПрограммирование\": " + (runtime1.intern() == compileTime2));
        System.out.println();

        // 4. Final переменные и пул строк
        System.out.println("4. Final переменные и пул строк:");

        final String finalStr1 = "Java";
        final String finalStr2 = "Программирование";
        String finalConcat = finalStr1 + finalStr2;

        System.out.println("final String finalStr1 = \"Java\"");
        System.out.println("final String finalStr2 = \"Программирование\"");
        System.out.println("String finalConcat = finalStr1 + finalStr2");
        System.out.println("finalConcat == \"JavaПрограммирование\": " + (finalConcat == "JavaПрограммирование"));
        System.out.println();

        // 5. StringBuilder и StringBuffer не используют пул строк
        System.out.println("5. StringBuilder и StringBuffer не используют пул строк:");

        StringBuilder sb = new StringBuilder();
        sb.append("Привет").append("Мир");
        String sbResult = sb.toString();

        StringBuffer buffer = new StringBuffer();
        buffer.append("Привет").append("Мир");
        String bufferResult = buffer.toString();

        String literal = "ПриветМир";

        System.out.println("Результат StringBuilder == литерал: " + (sbResult == literal));
        System.out.println("Результат StringBuffer == литерал: " + (bufferResult == literal));
        System.out.println("Результат StringBuilder.intern() == литерал: " + (sbResult.intern() == literal));
        System.out.println();

        // 6. Демонстрация влияния на память
        System.out.println("6. Демонстрация влияния на память:");

        demonstrateMemoryUsage();
        System.out.println();

        // 7. Пул строк с разным регистром символов
        System.out.println("7. Пул строк с разным регистром символов:");

        String lower = "привет";
        String upper = "ПРИВЕТ";
        String mixed = "Привет";

        System.out.println("\"привет\" == \"ПРИВЕТ\": " + (lower == upper));
        System.out.println("\"привет\" == \"Привет\": " + (lower == mixed));
        System.out.println("\"привет\".equals(\"ПРИВЕТ\"): " + lower.equals(upper));
        System.out.println("\"привет\".equalsIgnoreCase(\"ПРИВЕТ\"): " + lower.equalsIgnoreCase(upper));
        System.out.println();

        // 8. Пул строк с escape-последовательностями
        System.out.println("8. Пул строк с escape-последовательностями:");

        String tab1 = "Привет\tМир";
        String tab2 = "Привет\tМир";
        String newline1 = "Привет\nМир";
        String newline2 = "Привет\nМир";

        System.out.println("Строки с \\t объединены в пул: " + (tab1 == tab2));
        System.out.println("Строки с \\n объединены в пул: " + (newline1 == newline2));
        System.out.println();

        // 9. Большие строки и соображения производительности intern
        System.out.println("9. Соображения производительности:");
        performanceTest();
    }

    private static void demonstrateMemoryUsage() {
        // Это демонстрирует, почему пул строк экономит память
        String[] literals = new String[1000];
        String[] newStrings = new String[1000];

        // Все литералы указывают на один объект в пуле строк
        for (int i = 0; i < 1000; i++) {
            literals[i] = "ОдинаковаяСтрока";
        }

        // Каждый new String создает новый объект
        for (int i = 0; i < 1000; i++) {
            newStrings[i] = new String("ОдинаковаяСтрока");
        }

        // Проверяем, что все литералы это один объект
        boolean allLiteralsSame = true;
        for (int i = 1; i < 1000; i++) {
            if (literals[0] != literals[i]) {
                allLiteralsSame = false;
                break;
            }
        }

        // Проверяем, что все new String это разные объекты
        boolean allNewStringsDifferent = true;
        for (int i = 1; i < 1000; i++) {
            if (newStrings[0] == newStrings[i]) {
                allNewStringsDifferent = false;
                break;
            }
        }

        System.out.println("Все 1000 строковых литералов указывают на один объект: " + allLiteralsSame);
        System.out.println("Все 1000 new String() создают разные объекты: " + allNewStringsDifferent);
    }

    private static void performanceTest() {
        long startTime, endTime;
        String base = "ТестоваяСтрока";

        // Тестирование производительности intern()
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            String str = new String(base).intern();
        }
        endTime = System.nanoTime();
        System.out.println("Время intern() для 100,000 операций: " + (endTime - startTime) / 1_000_000 + " мс");

        // Тестирование производительности обычного new String()
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            String str = new String(base);
        }
        endTime = System.nanoTime();
        System.out.println("Время new String() для 100,000 операций: " + (endTime - startTime) / 1_000_000 + " мс");

        // Тестирование производительности строкового литерала
        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            String str = "ТестоваяСтрока";
        }
        endTime = System.nanoTime();
        System.out.println("Время строкового литерала для 100,000 операций: " + (endTime - startTime) / 1_000_000 + " мс");
    }
}