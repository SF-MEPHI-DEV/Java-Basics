package ru.mephi.bonus.Lambda;

import java.util.*;
import java.util.function.*;

public class MethodReferencesExample {

    public static void main(String[] args) {
        staticMethodReference();
        instanceMethodReference();
        instanceMethodOfArbitraryObject();
        constructorReference();
        arrayConstructorReference();
    }
    public static void staticMethodReference() {
        System.out.println("\n=== Ссылка на статический метод ===\n");
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");

        System.out.println("Лямбда:");
        numbers.stream()
                .map(s -> Integer.parseInt(s))
                .forEach(n -> System.out.print(n + " "));

        System.out.println("\n\nМетод-референс:");
        //Integer.parseInt("1");
        numbers.stream()
                .map(Integer::parseInt)
                .forEach(System.out::println);

        Function<String, Integer> parseFunc = Integer::parseInt;
        System.out.println("Через Function: " + parseFunc.apply("42"));
    }

    public static void instanceMethodReference() {
        System.out.println("\n=== Ссылка на метод экземпляра ===\n");

        String prefix = "Hello, ";

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        System.out.println("Лямбда:");
        names.forEach(name -> System.out.println(prefix.concat(name)));

        System.out.println("\nМетод-референс:");
        names.forEach(prefix::concat);

        Printer printer = new Printer();
        names.forEach(printer::print);
    }

    public static void instanceMethodOfArbitraryObject() {
        System.out.println("\n=== Ссылка на метод произвольного объекта ===\n");

        List<String> words = Arrays.asList("apple", "Banana", "cherry", "DATE");

        System.out.println("Лямбда:");
        words.stream()
                .map(s -> s.toLowerCase())
                .forEach(s -> System.out.println(s));

        System.out.println("\nМетод-референс:");
        words.stream()
                .map(String::toLowerCase)
                .forEach(System.out::println);

        words.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        System.out.println("\nОтсортировано (лямбда): " + words);

        words.sort(String::compareToIgnoreCase);
        System.out.println("Отсортировано (референс): " + words);

        BiPredicate<String, String> equals = String::equals;
        System.out.println("\nРавны? " + equals.test("hello", "hello"));
    }

    public static void constructorReference() {
        System.out.println("\n=== Ссылка на конструктор ===\n");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        System.out.println("Лямбда:");
        List<Person> people1 = names.stream()
                .map(name -> new Person(name))
                .toList();
        people1.forEach(p -> System.out.println(p));

        System.out.println("\nМетод-референс:");
        List<Person> people2 = names.stream()
                .map(Person::new)
                .toList();
        people2.forEach(System.out::println);

        Supplier<Person> personSupplier = Person::new;
        Person p = personSupplier.get();
        System.out.println("Через Supplier: " + p);

        Function<String, Person> personFactory = Person::new;
        Person alice = personFactory.apply("Alice");
        System.out.println("Через Function: " + alice);
    }

    public static void arrayConstructorReference() {
        System.out.println("\n=== Ссылка на конструктор массива ===\n");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        System.out.println("Лямбда:");
        String[] array1 = names.stream()
                .toArray(size -> new String[size]);
        System.out.println("Array: " + Arrays.toString(array1));

        System.out.println("\nМетод-референс:");
        String[] array2 = names.stream()
                .toArray(String[]::new);
        System.out.println("Array: " + Arrays.toString(array2));

        IntFunction<Person[]> personArrayFactory = Person[]::new;
        Person[] people = personArrayFactory.apply(5);
        System.out.println("Создан массив Person[" + people.length + "]");
    }
}

class Printer {
    public void print(String message) {
        System.out.println("Printed: " + message);
    }
}

class Person {
    private String name;

    public Person() {
        this.name = "Unknown";
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }
}
