package ru.mephi.week8.lesson1.Examples;

import java.util.ArrayList;
import java.util.List;

public class WildcardsExample {

    public static void main(String[] args) {
        unboundedWildcard();
        upperBoundedWildcard();
        lowerBoundedWildcard();
        pecsExample();
    }

    public static void unboundedWildcard() {
        System.out.println("=== Unbounded wildcard <?> ===");

        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        printList(intList);

        List<String> strList = new ArrayList<>();
        strList.add("A");
        strList.add("B");
        strList.add("C");
        printList(strList);

        System.out.println(getSize(intList));
        System.out.println(getSize(strList));
    }

    public static void upperBoundedWildcard() {
        System.out.println("\n=== Upper bounded <? extends Number> ===");

        List<Integer> intList = new ArrayList<>();
        intList.add(10);
        intList.add(20);
        intList.add(30);

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(1.5);
        doubleList.add(2.5);
        doubleList.add(3.5);

        double sum1 = sumNumbers(intList);
        System.out.println(sum1);

        double sum2 = sumNumbers(doubleList);
        System.out.println(sum2);

        // List<String> strList = new ArrayList<>();
        // sumNumbers(strList); // Ошибка!
    }

    public static void lowerBoundedWildcard() {
        System.out.println("\n=== Lower bounded <? super Integer> ===");

        List<Integer> intList = new ArrayList<>();
        addIntegers(intList);
        System.out.println(intList);

        List<Number> numList = new ArrayList<>();
        addIntegers(numList);
        System.out.println(numList);

        List<Object> objList = new ArrayList<>();
        addIntegers(objList);
        System.out.println(objList);

        // List<Double> doubleList = new ArrayList<>();
        // addIntegers(doubleList); // Ошибка!
    }

    public static void pecsExample() {
        System.out.println("\n=== PECS: Producer Extends, Consumer Super ===");

        List<Integer> source = new ArrayList<>();
        source.add(1);
        source.add(2);
        source.add(3);

        List<Number> destination = new ArrayList<>();

        copy(source, destination);
        System.out.println(source);
        System.out.println(destination);

        List<Integer> intSource = new ArrayList<>();
        intSource.add(10);
        intSource.add(20);

        List<Object> objDest = new ArrayList<>();
        copy(intSource, objDest);
        System.out.println(objDest);
    }

    public static void printList(List<?> list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i < list.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public static int getSize(List<?> list){
        return list.size();
    }
    //List<Integer> List<Double>
    public static double sumNumbers(List<? extends Number> list) {
        double sum = 0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }

    public static void addIntegers(List<? super Integer> list) {
        list.add(1);
        //Integer num = list.get(1); - вызовет ошибку
        list.add(2);
        list.add(3);
    }

    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }
}
