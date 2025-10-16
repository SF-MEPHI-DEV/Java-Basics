package ru.mephi.week8.lesson2.Examples;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

    public static void main(String[] args) {
        basicOperations();
        iterationExamples();
        keyValueOperations();
        collisionExample();
    }

    public static void basicOperations() {
        System.out.println("=== Базовые операции HashMap ===");

        HashMap<String, Integer> ages = new HashMap<>();

        ages.put("Иван", 25);
        ages.put("Мария", 30);
        ages.put("Петр", 22);
        System.out.println("Map: " + ages);

        System.out.println("Возраст Марии: " + ages.get("Мария"));
        System.out.println("Возраст Ольги: " + ages.get("Ольга"));

        System.out.println("Есть Иван? " + ages.containsKey("Иван"));
        System.out.println("Есть возраст 30? " + ages.containsValue(30));

        ages.put("Мария", 31);
        System.out.println("После обновления: " + ages.get("Мария"));

        ages.remove("Петр");
        System.out.println("Размер после удаления: " + ages.size());
    }

    public static void iterationExamples() {
        System.out.println("\n=== Способы обхода ===");

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Один");
        map.put(2, "Два");
        map.put(3, "Три");

        System.out.println("Обход по ключам:");
        for (Integer key : map.keySet()) {
            System.out.println("  " + key + " -> " + map.get(key));
        }

        System.out.println("Обход по значениям:");
        for (String value : map.values()) {
            System.out.println("  " + value);
        }

        System.out.println("Обход по Entry:");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        System.out.println("forEach:");
        map.forEach((k, v) -> System.out.println("  " + k + ": " + v));
    }

    public static void keyValueOperations() {
        System.out.println("\n=== Операции с ключами и значениями ===");

        HashMap<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 90);
        scores.put("Bob", 85);
        scores.put("Charlie", 92);

        scores.putIfAbsent("Alice", 95);
        System.out.println("Alice после putIfAbsent: " + scores.get("Alice"));

        scores.putIfAbsent("David", 88);
        System.out.println("David добавлен: " + scores.get("David"));

        int aliceScore = scores.getOrDefault("Alice", 0);
        int eveScore = scores.getOrDefault("Eve", 0);
        System.out.println("Alice: " + aliceScore + ", Eve: " + eveScore);

        scores.replace("Bob", 85, 87);
        System.out.println("Bob после replace: " + scores.get("Bob"));

        scores.compute("Charlie", (k, v) -> v + 5);
        System.out.println("Charlie +5: " + scores.get("Charlie"));
    }

    public static void collisionExample() {
        System.out.println("\n=== Коллизии hashCode ===");

        HashMap<Key, String> map = new HashMap<>();

        Key key1 = new Key(1);
        Key key2 = new Key(1);
        Key key3 = new Key(2);

        map.put(key1, "Значение 1");
        map.put(key2, "Значение 2");
        map.put(key3, "Значение 3");

        System.out.println("Размер map: " + map.size());
        System.out.println("key1 hashCode: " + key1.hashCode());
        System.out.println("key2 hashCode: " + key2.hashCode());
        System.out.println("key1.equals(key2): " + key1.equals(key2));

        System.out.println("\nОдинаковый hashCode, но разные объекты");
        System.out.println("HashMap использует equals для разрешения коллизий");
    }
}

class Key {
    private int value;

    public Key(int value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return value % 2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Key key = (Key) obj;
        return value == key.value;
    }
}
