package ru.mephi.bonus.StreamApi.Examples;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class MethodsExamples {

    // 1. filter — Фильтрация элементов
    static void filterExample() {

        System.out.println("=== 1. filter ===");
        List<String> names = List.of("Anna", "Bob", "Artem", "Bella");
        /*
        List<String> filteredNames = new ArrayList<String>();
        for(String name : names) {
            if(name.startsWith("A")) {
                names.add(name);
            }
        }
        */
        List<String> aNames = names.stream()
            .filter(name -> name.startsWith("A"))
            .collect(Collectors.toList());
        System.out.println(aNames); // [Anna, Artem]
        System.out.println();
    }

    // 2. map — Преобразование элементов
    static void mapExample() {
        System.out.println("=== 2. map ===");
        List<String> words = List.of("cat", "dog", "mouse");
        List<Integer> lengths = words.stream()
            .map(String::length)
            .collect(Collectors.toList());
        System.out.println(lengths); // [3, 3, 5]
        System.out.println();
    }

    // 3. sorted — Сортировка
    static void sortedExample() {
        System.out.println("=== 3. sorted ===");
        List<Integer> numbers = List.of(5, 3, 9, 1);
        List<Integer> sorted = numbers.stream()
            .sorted()
            .collect(Collectors.toList());
        System.out.println(sorted); // [1, 3, 5, 9]
        System.out.println();
    }

    // 4. distinct — Удаление дубликатов
    static void distinctExample() {
        System.out.println("=== 4. distinct ===");
        List<String> tags = List.of("java", "spring", "java", "rest");
        List<String> uniqueTags = tags.stream()
            .distinct()
            .collect(Collectors.toList());
        System.out.println(uniqueTags); // [java, spring, rest]
        System.out.println();
    }

    // 5. limit и skip — Ограничение и пропуск элементов
    static void limitSkipExample() {
        System.out.println("=== 5. limit и skip ===");
        List<Integer> numbers = List.of(10, 20, 30, 40, 50);
        List<Integer> subList = numbers.stream()
            .skip(2)     // Пропускаем первые два элемента
            .limit(2)    // Оставляем только два следующих
            .collect(Collectors.toList());
        System.out.println(subList); // [30, 40]
        System.out.println();
    }

    // 6. forEach — Выполнение действия для каждого элемента
    static void forEachExample() {
        System.out.println("=== 6. forEach ===");
        List<String> fruits = List.of("apple", "banana", "pear");
        fruits.stream().forEach(fruit -> System.out.println(fruit.toLowerCase()));
        // APPLE
        // BANANA
        // PEAR
        System.out.println();
    }

    // 7. collect — Сбор результатов
    static void collectExample() {
        System.out.println("=== 7. collect ===");
        List<String> animals = List.of("cat", "dog", "fox");
        String result = animals.stream()
            .collect(Collectors.joining(", "));
        System.out.println(result); // cat, dog, fox
        System.out.println();
    }

    // 8. reduce — Свертка/агрегация
    static void reduceExample() {
        System.out.println("=== 8. reduce ===");
        List<Integer> values = List.of(1, 2, 3, 4);
        List<String> strings = List.of("Aleksey","Anton","Alexander");
        String result = strings.stream()
                .reduce("",(a,b) -> a + " " + b);
        System.out.println(result);

        double sum = values.stream()
                .filter( a -> a > 2 )
                .map( a -> a.doubleValue())
                .reduce(0.0, (a, b) -> a + b);
        System.out.println(sum); // 10
        System.out.println();
    }

    // 9. flatMap — "Разворачивание" стримов коллекций
    static void flatMapExample() {
        System.out.println("=== 9. flatMap ===");
        List<List<Integer>> nested = List.of(
            List.of(1, 2),
            List.of(3, 4)
        );
        List<Integer> flat = nested.stream()
            .flatMap(list -> list.stream())
            .collect(Collectors.toList());
        System.out.println(flat); // [1, 2, 3, 4]
        System.out.println();
    }

    // 10. anyMatch / allMatch / noneMatch — Проверки условий
    static void matchExample() {
        System.out.println("=== 10. anyMatch / allMatch / noneMatch ===");
        List<Integer> nums = List.of(10, 25, 30);
        boolean anyAbove20 = nums.stream().anyMatch(n -> n > 20); // true
        boolean allAbove5   = nums.stream().allMatch(n -> n > 5);  // true
        boolean noneAbove40 = nums.stream().noneMatch(n -> n > 40); // true
        System.out.println(anyAbove20 + ", " + allAbove5 + ", " + noneAbove40);
        System.out.println();
    }

    // 11. findFirst / findAny — Поиск элемента
    static void findExample() {
        System.out.println("=== 11. findFirst / findAny ===");
        List<String> list = List.of("x", "y", "z");
        String first = list.stream().findFirst().orElse("none");
        System.out.println(first); // x
        System.out.println();
    }

    // 12. count — Подсчет количества
    static void countExample() {
        System.out.println("=== 12. count ===");
        List<String> filtered = List.of("robot", "java", "rest");
        long countJ = filtered.stream().filter(word -> word.startsWith("j")).count();
        System.out.println(countJ); // 1
        System.out.println();
    }

    public static void main(String[] args) {
        filterExample();
        mapExample();
        sortedExample();
        distinctExample();
        limitSkipExample();
        forEachExample();
        collectExample();
        reduceExample();
        flatMapExample();
        matchExample();
        findExample();
        countExample();
    }
}
