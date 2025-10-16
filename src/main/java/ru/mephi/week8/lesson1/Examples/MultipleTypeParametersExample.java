package ru.mephi.week8.lesson1.Examples;

public class MultipleTypeParametersExample {

    public static void main(String[] args) {
        pairExample();
        tripleExample();
        resultExample();
    }

    public static void pairExample() {
        System.out.println("=== Pair<K, V> ===");

        Pair<String, Integer> age = new Pair<>("Иван", 25);
        System.out.println(age.getKey() + ": " + age.getValue());

        Pair<Integer, String> idName = new Pair<>(101, "Мария");
        System.out.println(idName.getKey() + " -> " + idName.getValue());

        Pair<String, Double> price = new Pair<>("Яблоко", 50.5);
        System.out.println(price.getKey() + " = " + price.getValue());
    }

    public static void tripleExample() {
        System.out.println("\n=== Triple<A, B, C> ===");

        Triple<String, Integer, Double> student = new Triple<>("Петр", 20, 4.5);
        System.out.println(student.getFirst() + ", " + student.getSecond() + ", " + student.getThird());

        Triple<Integer, Integer, Integer> coords = new Triple<>(10, 20, 30);
        System.out.println("(" + coords.getFirst() + ", " + coords.getSecond() + ", " + coords.getThird() + ")");
    }

    public static void resultExample() {
        System.out.println("\n=== Result<T, E> ===");

        Result<Integer, String> success = Result.success(42);
        if (success.isSuccess()) {
            System.out.println(success.getValue());
        }

        Result<Integer, String> failure = Result.failure("Ошибка деления на ноль");
        if (!failure.isSuccess()) {
            System.out.println(failure.getError());
        }

        Result<String, Exception> fileResult = readFile("data.txt");
        if (fileResult.isSuccess()) {
            System.out.println(fileResult.getValue());
        } else {
            System.out.println("Файл не найден");
        }
    }

    public static Result<String, Exception> readFile(String filename) {
        if (filename.equals("data.txt")) {
            return Result.success("Hello, World!");
        } else {
            return Result.failure(new Exception("Файл не найден"));
        }
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

class Triple<A, B, C> {
    private A first;
    private B second;
    private C third;

    public Triple(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }
}

class Result<T, E> {
    private T value;
    private E error;
    private boolean success;

    private Result(T value, E error, boolean success) {
        this.value = value;
        this.error = error;
        this.success = success;
    }

    public static <T, E> Result<T, E> success(T value) {
        return new Result<>(value, null, true);
    }

    public static <T, E> Result<T, E> failure(E error) {
        return new Result<>(null, error, false);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getValue() {
        return value;
    }

    public E getError() {
        return error;
    }
}
