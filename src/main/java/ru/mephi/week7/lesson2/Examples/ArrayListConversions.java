package ru.mephi.week7.lesson2.Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListConversions {

    public static void main(String[] args) {
        arrayToList();
        listToArray();
        varargs();
        capacityVsSize();
    }

    public static void arrayToList() {
        System.out.println("=== Массив -> List ===");

        String[] array = {"A", "B", "C"};

        List<String> list1 = Arrays.asList(array);
        System.out.println("Arrays.asList: " + list1 + " (фиксированный)");

        ArrayList<String> list2 = new ArrayList<>(Arrays.asList(array));
        list2.add("D");
        System.out.println("ArrayList: " + list2 + " (изменяемый)");

        int[] numbers = {1, 2, 3, 4, 5};
        ArrayList<Integer> numList = new ArrayList<>();
        for (int num : numbers) {
            numList.add(num);
        }
        System.out.println("int[] -> ArrayList: " + numList);
    }

    public static void listToArray() {
        System.out.println("\n=== List -> Массив ===");

        ArrayList<String> list = new ArrayList<>();
        list.add("Один");
        list.add("Два");
        list.add("Три");

        String[] array1 = list.toArray(new String[0]);
        System.out.println("toArray(): " + Arrays.toString(array1));

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        int[] primitiveArray = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            primitiveArray[i] = numbers.get(i);
        }
        System.out.println("-> int[]: " + Arrays.toString(primitiveArray));
    }

    public static void varargs() {
        System.out.println("\n=== Varargs ===");

        List<String> list1 = Arrays.asList("A", "B", "C");
        System.out.println("Arrays.asList: " + list1);

        List<Integer> list2 = List.of(1, 2, 3, 4, 5);
        System.out.println("List.of: " + list2 + " (immutable)");

        ArrayList<String> mutableList = new ArrayList<>(List.of("X", "Y", "Z"));
        mutableList.add("W");
        System.out.println("Mutable copy: " + mutableList);
    }

    public static void capacityVsSize() {
        System.out.println("\n=== Capacity vs Size ===");

        ArrayList<Integer> list1 = new ArrayList<>();
        System.out.println("Пустой: size=" + list1.size() + ", capacity=10");

        for (int i = 0; i < 11; i++) {
            list1.add(i);
        }
        System.out.println("11 элементов: size=" + list1.size() + ", capacity~15");

        ArrayList<Integer> list2 = new ArrayList<>(100);
        System.out.println("\ninitialCapacity=100: size=" + list2.size());

        list2.add(1);
        System.out.println("После add: size=" + list2.size() + ", capacity=100");

        list2.ensureCapacity(200);
        System.out.println("ensureCapacity(200): capacity=200");

        list2.trimToSize();
        System.out.println("trimToSize(): capacity=" + list2.size());
    }
}
