package ru.mephi.week8.lesson1.Examples;

import java.util.ArrayList;
import java.util.List;

public class BoundsExample {

    public static void main(String[] args) {
        upperBoundExample();
        multipleBoundsExample();
        numberBoxExample();
        comparableExample();
    }

    public static void upperBoundExample() {
        System.out.println("=== Upper bound (extends) ===");

        NumberBox<Integer> intBox = new NumberBox<>(10);
        System.out.println(intBox.getValue());
        System.out.println(intBox.doubleValue());

        NumberBox<Double> doubleBox = new NumberBox<>(3.14);
        System.out.println(doubleBox.getValue());
        System.out.println(doubleBox.doubleValue());

        // NumberBox<String> strBox = new NumberBox<>("test");
    }

    public static void multipleBoundsExample() {
        System.out.println("\n=== Multiple bounds ===");

        Integer[] nums = {5, 2, 8, 1, 9};
        Integer max = findMax(nums);
        System.out.println(max);

        //String[] words = {"apple", "banana", "cherry"};
        //String maxWord = findMax(words);
        //System.out.println(maxWord);

        Double[] doubles = {3.14, 2.71, 1.41};
        Double maxDouble = findMax(doubles);
        System.out.println(maxDouble);
    }

    public static void numberBoxExample() {
        System.out.println("\n=== Операции с числами ===");

        NumberBox<Integer> box1 = new NumberBox<>(10);
        NumberBox<Integer> box2 = new NumberBox<>(20);

        //NumberBox<Integer>  -> NumberBox<Number>

        double sum = box1.doubleValue() + box2.doubleValue();
        System.out.println(sum);

        NumberBox<Double> box3 = new NumberBox<>(5.5);
        NumberBox<Double> box4 = new NumberBox<>(2.5);
        double diff = box3.doubleValue() - box4.doubleValue();
        System.out.println(diff);
    }

    public static void comparableExample() {
        System.out.println("\n=== Сортировка с Comparable ===");

        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);

        System.out.println(numbers);
        sortList(numbers);
        System.out.println(numbers);

        List<String> words = new ArrayList<>();
        words.add("banana");
        words.add("apple");
        words.add("cherry");

        System.out.println(words);
        sortList(words);
        System.out.println(words);
    }

    public static <T extends Number & Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    public static <T extends Comparable<T>> void sortList(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}

class NumberBox<T extends Number> {
    private T value;

    public NumberBox(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public double doubleValue() {
        return value.doubleValue();
    }

    public int intValue() {
        return value.intValue();
    }
}
