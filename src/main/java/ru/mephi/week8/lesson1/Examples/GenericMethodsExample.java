package ru.mephi.week8.lesson1.Examples;

public class GenericMethodsExample {

    public static void main(String[] args) {
        staticGenericMethods();
        typeInference();
        multipleTypeParameters();
        genericInNonGenericClass();
    }

    public static void staticGenericMethods() {
        System.out.println("=== Статические generic методы ===");

        print(123);
        print("Hello");
        print(3.14);

        Integer[] intArray = {1, 2, 3};
        print(intArray);

        String[] strArray = {"A", "B", "C"};
        print(strArray);
    }

    public static void typeInference() {
        System.out.println("\n=== Type inference ===");

        Integer max1 = findMax(10, 20, 5);
        System.out.println(max1);

        String max2 = findMax("apple", "banana", "cherry");
        System.out.println(max2);

        Double max3 = findMax(3.14, 2.71, 1.41);
        System.out.println(max3);
    }

    public static void multipleTypeParameters() {
        System.out.println("\n=== Несколько параметров типа ===");

        Pair<String, Integer> pair1 = makePair("Age", 25);
        System.out.println(pair1.getKey() + " = " + pair1.getValue());

        Pair<Integer, String> pair2 = makePair(404, "Not Found");
        System.out.println(pair2.getKey() + ": " + pair2.getValue());

        boolean same1 = areEqual(10, 10);
        System.out.println("10 == 10: " + same1);

        boolean same2 = areEqual("test", "test");
        System.out.println("test == test: " + same2);
    }

    public static void genericInNonGenericClass() {
        System.out.println("\n=== Generic методы в обычном классе ===");

        Util util = new Util();

        String[] words = {"hello", "world"};
        util.reverse(words);
        System.out.println(words[0] + ", " + words[1]);

        Integer[] nums = {1, 2, 3, 4, 5};
        util.reverse(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static <T> void print(T element) {
        System.out.println(element);
    }

    public static <T> void print(T[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static <T extends Comparable<T>> T findMax(T a, T b, T c) {
        T max = a;
        if (b.compareTo(max) > 0) {
            max = b;
        }
        if (c.compareTo(max) > 0) {
            max = c;
        }
        return max;
    }

    public static <K, V> Pair<K, V> makePair(K key, V value) {
        return new Pair<>(key, value);
    }

    public static <T> boolean areEqual(T a, T b) {
        return a.equals(b);
    }
}

class Util {
    public <T> void reverse(T[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            T temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }

    public <T> T getFirst(T[] array) {
        if (array.length > 0) {
            return array[0];
        }
        return null;
    }
}
