package ru.mephi.week8.lesson1.Examples;

import java.util.ArrayList;
import java.util.List;

public class TypeErasureExample {

    public static void main(String[] args) {
        whatIsTypeErasure();
        problem1_CannotCreateInstance();
        problem2_CannotCreateArray();
        problem3_CannotUseInstanceof();
        problem4_MethodOverloading();
        problem5_RuntimeTypeInfo();
        solutions();
    }

    public static void whatIsTypeErasure() {
        System.out.println("=== Что такое Type Erasure ===");

        Box<String> stringBox = new Box<>();
        Box<Integer> intBox = new Box<>();

        System.out.println(stringBox.getClass().getName());
        System.out.println(intBox.getClass().getName());
        System.out.println(stringBox.getClass() == intBox.getClass());

        System.out.println("\nПосле компиляции Box<String> и Box<Integer> становятся просто Box");
    }

    public static void problem1_CannotCreateInstance() {
        System.out.println("\n=== Проблема 1: new T() невозможно ===");

        // class BadBox<T> {
        //     T value = new T(); // Ошибка компиляции!
        // }

        System.out.println("Нельзя создать экземпляр T, после erasure T становится Object");
    }

    public static void problem2_CannotCreateArray() {
        System.out.println("\n=== Проблема 2: new T[] невозможно ===");

        // class BadContainer<T> {
        //     T[] array = new T[10]; // Ошибка компиляции!
        // }

        System.out.println("Нельзя создать массив generic типа");
        System.out.println("Массивы хранят тип в runtime, generics стираются");

        GenericArray<String> arr = new GenericArray<>(5);
        arr.set(0, "Hello");
        System.out.println("Решение: Object[] + cast");
    }

    public static void problem3_CannotUseInstanceof() {
        System.out.println("\n=== Проблема 3: instanceof с generics ===");

        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        // if (stringList instanceof List<String>) {} // Ошибка!
        if (stringList instanceof List) {
            System.out.println("Можно проверить только raw type: instanceof List");
        }
    }

    public static void problem4_MethodOverloading() {
        System.out.println("\n=== Проблема 4: Перегрузка методов ===");

        ListProcessor processor = new ListProcessor();
        processor.process(new ArrayList<String>());
        processor.process(new ArrayList<Integer>());

        System.out.println("\nПосле erasure: process(List) и process(List) - одинаковая сигнатура");
    }

    public static void problem5_RuntimeTypeInfo() {
        System.out.println("\n=== Проблема 5: Потеря информации о типе ===");

        List<String> stringList = new ArrayList<>();
        stringList.add("test");

        Class<?> clazz = stringList.getClass();
        System.out.println(clazz.getName());
        System.out.println("Нет информации что это List<String>, только ArrayList");
    }

    public static void solutions() {
        System.out.println("\n=== Решения проблем ===");

        Factory<String> stringFactory = new Factory<>(String.class);
        String str = stringFactory.create();
        System.out.println(str != null ? "null" : str);

        Factory<StringBuilder> builderFactory = new Factory<>(StringBuilder.class);
        StringBuilder sb = builderFactory.create();
        System.out.println(sb != null);

        GenericArray<Integer> intArray = new GenericArray<>(5);
        intArray.set(0, 100);
        System.out.println(intArray.get(0));
    }
}

class ListProcessor {
    public void process(List list) {
        System.out.println("Processing list");
    }

    // НЕ СКОМПИЛИРУЕТСЯ - одинаковая сигнатура после erasure
    //public void process(List<String> list) {
    //     System.out.println("Processing string list");
    // }

    // public void process(List<Integer> list) {
    //     System.out.println("Processing integer list");
    // }
}

class Factory<T> {
    private Class<T> type;

    public Factory(Class<T> type) {
        this.type = type;
    }

    public T create() {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Ошибка создания: " + e.getMessage());
            return null;
        }
    }

    public Class<T> getType() {
        return type;
    }
}

class GenericArray<T> {
    private Object[] array;

    public GenericArray(int size) {
        array = new Object[size];
    }

    public void set(int index, T value) {
        array[index] = value;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }
}
