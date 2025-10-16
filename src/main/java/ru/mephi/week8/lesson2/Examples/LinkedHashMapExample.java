package ru.mephi.week8.lesson2.Examples;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {

    public static void main(String[] args) {
        insertionOrderExample();
        hashMapVsLinkedHashMap();
        accessOrderMode();
        lruCacheExample();
    }

    public static void insertionOrderExample() {
        System.out.println("=== Порядок вставки ===");

        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        map.put("Третий", 3);
        map.put("Первый", 1);
        map.put("Второй", 2);
        map.put("Четвертый", 4);

        System.out.println("LinkedHashMap сохраняет порядок вставки:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
    }

    public static void hashMapVsLinkedHashMap() {
        System.out.println("\n=== HashMap vs LinkedHashMap ===");

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(3, "Три");
        hashMap.put(1, "Один");
        hashMap.put(2, "Два");

        System.out.println("HashMap (порядок не гарантирован):");
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        LinkedHashMap<Integer, String> linkedMap = new LinkedHashMap<>();
        linkedMap.put(3, "Три");
        linkedMap.put(1, "Один");
        linkedMap.put(2, "Два");

        System.out.println("LinkedHashMap (порядок вставки):");
        for (Map.Entry<Integer, String> entry : linkedMap.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void accessOrderMode() {
        System.out.println("\n=== Access Order Mode ===");

        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(16, 0.75f, true);

        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);

        System.out.println("После вставки:");
        printMap(map);

        map.get("A");
        System.out.println("После get(A):");
        printMap(map);

        map.get("C");
        System.out.println("После get(C):");
        printMap(map);
    }

    public static void lruCacheExample() {
        System.out.println("\n=== LRU Cache ===");

        LRUCache<String, Integer> cache = new LRUCache<>(3);

        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        System.out.println("Cache: " + cache);

        cache.get("A");
        System.out.println("После get(A): " + cache);

        cache.put("D", 4);
        System.out.println("После put(D) - B удален: " + cache);

        cache.put("E", 5);
        System.out.println("После put(E) - C удален: " + cache);
    }

    private static void printMap(Map<String, Integer> map) {
        System.out.print("  [");
        int i = 0;
        for (String key : map.keySet()) {
            System.out.print(key);
            if (i < map.size() - 1) {
                System.out.print(", ");
            }
            i++;
        }
        System.out.println("]");
    }
}

class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
