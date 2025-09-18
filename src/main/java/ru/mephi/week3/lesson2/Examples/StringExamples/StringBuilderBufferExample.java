package ru.mephi.week3.lesson2.Examples.StringExamples;

public class StringBuilderBufferExample {
    public static void main(String[] args) {
        System.out.println("=== ПРИМЕРЫ РАБОТЫ СО STRINGBUILDER И STRINGBUFFER ===\n");

        System.out.println("1. Основы StringBuilder:");
        demonstrateStringBuilderBasics();
        System.out.println();

        System.out.println("2. Основы StringBuffer:");
        demonstrateStringBufferBasics();
        System.out.println();

        System.out.println("3. Сравнение производительности:");
        performanceComparison();
        System.out.println();

        System.out.println("4. Методы StringBuilder/StringBuffer:");
        demonstrateMethods();
        System.out.println();

        System.out.println("5. Практические примеры:");
        practicalExamples();
        System.out.println();

        System.out.println("6. Лучшие практики:");
        bestPractices();
    }

    private static void demonstrateStringBuilderBasics() {
        System.out.println("StringBuilder - изменяемая последовательность символов:");

        // Создание StringBuilder
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder("Начальная строка1");
        StringBuilder sb3 = new StringBuilder(50); // с начальной емкостью
        System.out.println("");
        System.out.println("Пустой StringBuilder: '" + sb1 + "'");
        System.out.println("С начальной строкой: '" + sb2 + "'");
        System.out.println("Длина sb2: " + sb2.length());
        System.out.println("Емкость sb2: " + sb2.capacity());
        System.out.println("Емкость sb3: " + sb3.capacity());

        // Добавление содержимого
        sb1.append("Привет");
        sb1.append(" ");
        sb1.append("Мир");
        sb1.append("!");
        //sb1.replace(1,1,"a");
        System.out.println("После добавления: '" + sb1 + "'");

        // Цепочка методов (method chaining)
        StringBuilder sb4 = new StringBuilder()
                .append("Цепочка")
                .append(" ")
                .append("методов")
                .append(" ")
                .append("работает!");

        System.out.println("Цепочка методов: '" + sb4 + "'");
    }

    private static void demonstrateStringBufferBasics() {
        System.out.println("StringBuffer - потокобезопасная изменяемая последовательность:");

        // StringBuffer имеет те же методы что и StringBuilder
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer("Начальная строка");
        StringBuffer buffer3 = new StringBuffer(50);

        System.out.println("Пустой StringBuffer: '" + buffer1 + "'");
        System.out.println("С начальной строкой: '" + buffer2 + "'");
        System.out.println("Длина buffer2: " + buffer2.length());
        System.out.println("Емкость buffer2: " + buffer2.capacity());

        // Добавление содержимого
        buffer1.append("StringBuffer");
        buffer1.append(" ");
        buffer1.append("синхронизирован");

        System.out.println("После добавления: '" + buffer1 + "'");

        // Цепочка методов также работает
        StringBuffer buffer4 = new StringBuffer()
                .append("Потокобезопасный")
                .append(" ")
                .append("буфер");

        System.out.println("Цепочка методов: '" + buffer4 + "'");
    }

    private static void performanceComparison() {
        int iterations = 10000;
        String baseString = "тест";

        // Тест String конкатенации (медленно)
        long startTime = System.nanoTime();
        String result1 = "";
        for (int i = 0; i < iterations; i++) {
            result1 += baseString;
        }
        long stringTime = System.nanoTime() - startTime;

        // Тест StringBuilder (быстро)
        startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(baseString);
        }
        String result2 = sb.toString();
        long sbTime = System.nanoTime() - startTime;

        // Тест StringBuffer (медленнее StringBuilder)
        startTime = System.nanoTime();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            buffer.append(baseString);
        }
        String result3 = buffer.toString();
        long bufferTime = System.nanoTime() - startTime;

        System.out.println("Тестирование " + iterations + " конкатенаций:");
        System.out.println("String +=: " + stringTime / 1_000_000 + " мс");
        System.out.println("StringBuilder: " + sbTime / 1_000_000 + " мс");
        System.out.println("StringBuffer: " + bufferTime / 1_000_000 + " мс");
        System.out.println();

        System.out.println("Соотношение производительности:");
        System.out.println("StringBuilder быстрее String в " + (stringTime / sbTime) + " раз");
        System.out.println("StringBuilder быстрее StringBuffer в " + (bufferTime / sbTime) + " раз");
        System.out.println("Все результаты одинаковы: " +
                          (result1.equals(result2) && result2.equals(result3)));
    }

    private static void demonstrateMethods() {
        StringBuilder sb = new StringBuilder("Java Программирование");

        System.out.println("Исходная строка: '" + sb + "'");
        System.out.println("Длина: " + sb.length());
        System.out.println("Емкость: " + sb.capacity());

        // append() - добавление в конец
        sb.append(" 2024");
        System.out.println("После append(' 2024'): '" + sb + "'");

        // insert() - вставка в указанную позицию
        sb.insert(0, "Изучаем ");
        System.out.println("После insert(0, 'Изучаем '): '" + sb + "'");

        // delete() - удаление диапазона символов
        sb.delete(8, 13); // удаляем "Java "
        System.out.println("После delete(8, 13): '" + sb + "'");

        // deleteCharAt() - удаление одного символа
        sb.deleteCharAt(sb.length() - 1); // удаляем последний символ
        System.out.println("После deleteCharAt(последний): '" + sb + "'");

        // replace() - замена диапазона символов
        sb.replace(8, 23, "JavaScript");
        System.out.println("После replace(8, 23, 'JavaScript'): '" + sb + "'");

        // reverse() - переворот строки
        StringBuilder sb2 = new StringBuilder("палиндром");
        System.out.println("До reverse(): '" + sb2 + "'");
        sb2.reverse();
        System.out.println("После reverse(): '" + sb2 + "'");

        // setCharAt() - изменение символа по индексу
        sb.setCharAt(0, 'И');
        System.out.println("После setCharAt(0, 'И'): '" + sb + "'");

        // setLength() - изменение длины
        StringBuilder sb3 = new StringBuilder("Длинная строка");
        System.out.println("До setLength(): '" + sb3 + "' (длина: " + sb3.length() + ")");
        sb3.setLength(8);
        System.out.println("После setLength(8): '" + sb3 + "' (длина: " + sb3.length() + ")");

        // ensureCapacity() - обеспечение минимальной емкости
        StringBuilder sb4 = new StringBuilder();
        System.out.println("Емкость до ensureCapacity(): " + sb4.capacity());
        sb4.ensureCapacity(100);
        System.out.println("Емкость после ensureCapacity(100): " + sb4.capacity());

        // trimToSize() - сжатие до актуального размера
        sb4.append("Короткая строка");
        System.out.println("Емкость после добавления текста: " + sb4.capacity());
        sb4.trimToSize();
        System.out.println("Емкость после trimToSize(): " + sb4.capacity());
    }

    private static void practicalExamples() {
        // Пример 1: Построение SQL запроса
        System.out.println("Пример 1: Построение SQL запроса");
        StringBuilder sqlQuery = new StringBuilder("SELECT ");
        sqlQuery.append("id, name, email ");
        sqlQuery.append("FROM users ");
        sqlQuery.append("WHERE age > 18 ");
        sqlQuery.append("AND status = 'active' ");
        sqlQuery.append("ORDER BY name");
        System.out.println("SQL: " + sqlQuery);
        System.out.println();

        // Пример 2: Форматирование JSON
        System.out.println("Пример 2: Построение JSON");
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"name\": \"Иван\",\n");
        json.append("  \"age\": 25,\n");
        json.append("  \"city\": \"Москва\",\n");
        json.append("  \"skills\": [\"Java\", \"Python\", \"JavaScript\"]\n");
        json.append("}");
        System.out.println("JSON:\n" + json);
        System.out.println();

        // Пример 3: Построение HTML
        System.out.println("Пример 3: Построение HTML");
        StringBuilder html = new StringBuilder();
        html.append("<table>\n");
        html.append("  <tr>\n");
        html.append("    <th>Имя</th>\n");
        html.append("    <th>Возраст</th>\n");
        html.append("  </tr>\n");

        String[][] data = {{"Алексей", "30"}, {"Мария", "25"}, {"Дмитрий", "35"}};
        for (String[] row : data) {
            html.append("  <tr>\n");
            html.append("    <td>").append(row[0]).append("</td>\n");
            html.append("    <td>").append(row[1]).append("</td>\n");
            html.append("  </tr>\n");
        }
        html.append("</table>");
        System.out.println("HTML:\n" + html);
        System.out.println();

        // Пример 4: Построение пути к файлу
        System.out.println("Пример 4: Построение пути к файлу");
        StringBuilder filePath = new StringBuilder();
        String[] pathParts = {"home", "user", "documents", "projects", "java", "src"};

        for (int i = 0; i < pathParts.length; i++) {
            if (i > 0) filePath.append("/");
            filePath.append(pathParts[i]);
        }
        filePath.append("/Main.java");
        System.out.println("Путь к файлу: " + filePath);
        System.out.println();

        // Пример 5: Создание CSV
        System.out.println("Пример 5: Создание CSV");
        StringBuilder csv = new StringBuilder();
        csv.append("Имя,Возраст,Город\n");

        Object[][] csvData = {
            {"Анна", 28, "Санкт-Петербург"},
            {"Борис", 35, "Москва"},
            {"Виктория", 22, "Казань"}
        };

        for (Object[] row : csvData) {
            for (int i = 0; i < row.length; i++) {
                if (i > 0) csv.append(",");
                csv.append(row[i]);
            }
            csv.append("\n");
        }
        System.out.println("CSV:\n" + csv);
    }

    private static void bestPractices() {
        System.out.println("Лучшие практики использования:");
        System.out.println();

        // 1. Инициализация с правильной емкостью
        System.out.println("1. Правильная инициализация емкости:");
        StringBuilder sb1 = new StringBuilder(); // емкость по умолчанию 16
        StringBuilder sb2 = new StringBuilder(100); // если знаете примерный размер

        System.out.println("По умолчанию: емкость " + sb1.capacity());
        System.out.println("С заданной емкостью: " + sb2.capacity());
        System.out.println("Преимущество: меньше пересоздания внутреннего массива");
        System.out.println();

        // 2. Использование метода чейнинга
        System.out.println("2. Метод чейнинг (цепочка вызовов):");
        String result = new StringBuilder()
                .append("Метод ")
                .append("чейнинг ")
                .append("делает ")
                .append("код ")
                .append("читаемым")
                .toString();
        System.out.println("Результат: " + result);
        System.out.println();

        // 3. Когда использовать StringBuilder vs String
        System.out.println("3. Когда использовать StringBuilder:");
        System.out.println("✓ Много операций конкатенации в цикле");
        System.out.println("✓ Динамическое построение строк");
        System.out.println("✓ Сложное форматирование");
        System.out.println();
        System.out.println("Когда StringBuilder НЕ нужен:");
        System.out.println("✗ Простая конкатенация 2-3 строк");
        System.out.println("✗ Статические строки");
        System.out.println("✗ Однократные операции");
        System.out.println();

        // 4. Преобразование в String
        System.out.println("4. Правильное преобразование в String:");
        StringBuilder sb = new StringBuilder("Пример");

        // Правильно
        String str1 = sb.toString();
        System.out.println("Правильно: sb.toString()");

        // Неправильно (избегать)
        String str2 = "" + sb;
        String str3 = String.valueOf(sb);
        System.out.println("Избегать: \"\" + sb или String.valueOf(sb)");
        System.out.println();

        // 5. Очистка содержимого
        System.out.println("5. Очистка содержимого:");
        StringBuilder sbClear = new StringBuilder("Содержимое для очистки");
        System.out.println("До очистки: '" + sbClear + "'");

        // Способ 1: setLength(0)
        sbClear.setLength(0);
        System.out.println("После setLength(0): '" + sbClear + "'");

        // Способ 2: delete(0, length())
        sbClear.append("Новое содержимое");
        sbClear.delete(0, sbClear.length());
        System.out.println("После delete(0, length()): '" + sbClear + "'");
        System.out.println("Рекомендуется: setLength(0) - быстрее");
    }
}