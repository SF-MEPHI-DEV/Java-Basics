package ru.mephi.json_parser;

import ru.mephi.json_parser.exceptions.JsonParseException;

import java.io.*;
import java.util.*;

public class Main {
    private static final JsonParser parser = new JsonParser();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getIntInput("Выберите действие: ");

            try {
                switch (choice) {
                    case 1 -> parseJsonFile();
                    case 2 -> createAndSerializeJson();
                    case 0 -> {
                        System.out.println("Выход из программы");
                        running = false;
                    }
                    default -> System.out.println("Неверный выбор");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   JSON ПАРСЕР И СЕРИАЛИЗАТОР             ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1. Распарсить JSON файл                  ║");
        System.out.println("║ 2. Создать и сериализовать JSON          ║");
        System.out.println("║ 0. Выход                                 ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private static void parseJsonFile() throws JsonParseException, IOException {
        System.out.print("Введите путь к JSON файлу: ");
        String filename = scanner.nextLine();

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        JsonValue parsed = parser.parse(content.toString());
        System.out.println("\n=== Содержимое файла ===");
        printJsonValue(parsed, 0);
    }

    private static void createAndSerializeJson() {
        Map<String, JsonValue> person = new LinkedHashMap<>();
        person.put("name", JsonValue.string("Иван Иванов"));
        person.put("age", JsonValue.number(25));
        person.put("email", JsonValue.string("ivan@example.com"));
        person.put("active", JsonValue.bool(true));

        List<JsonValue> hobbies = new ArrayList<>();
        hobbies.add(JsonValue.string("программирование"));
        hobbies.add(JsonValue.string("чтение"));
        hobbies.add(JsonValue.string("спорт"));
        person.put("hobbies", JsonValue.array(hobbies));

        Map<String, JsonValue> address = new LinkedHashMap<>();
        address.put("city", JsonValue.string("Москва"));
        address.put("street", JsonValue.string("Ленина"));
        address.put("building", JsonValue.number(10));
        person.put("address", JsonValue.object(address));

        person.put("middleName", JsonValue.nullValue());

        JsonValue personJson = JsonValue.object(person);

        JsonSerializer serializer = new JsonSerializer();
        String compact = serializer.serialize(personJson);
        System.out.println("\n=== Компактный JSON ===");
        System.out.println(compact);

    }

    private static void printJsonValue(JsonValue value, int indent) {
        String indentStr = "  ".repeat(indent);

        if (value.isObject()) {
            System.out.println(indentStr + "Объект:");
            value.asObject().forEach((key, val) -> {
                System.out.println(indentStr + "  \"" + key + "\":");
                printJsonValue(val, indent + 2);
            });
        } else if (value.isArray()) {
            System.out.println(indentStr + "Массив:");
            List<JsonValue> array = value.asArray();
            for (int i = 0; i < array.size(); i++) {
                System.out.println(indentStr + "  [" + i + "]:");
                printJsonValue(array.get(i), indent + 2);
            }
        } else if (value.isString()) {
            System.out.println(indentStr + "Строка: \"" + value.asString() + "\"");
        } else if (value.isNumber()) {
            System.out.println(indentStr + "Число: " + value.asNumber());
        } else if (value.isBoolean()) {
            System.out.println(indentStr + "Boolean: " + value.asBoolean());
        } else if (value.isNull()) {
            System.out.println(indentStr + "Null");
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }
}
