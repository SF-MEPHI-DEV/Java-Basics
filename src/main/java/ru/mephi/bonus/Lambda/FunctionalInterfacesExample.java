package ru.mephi.bonus.Lambda;

import java.util.function.*;

@FunctionalInterface
interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}

@FunctionalInterface
interface NoReturnFunction<T> {
    void execute(T t);
}

public class FunctionalInterfacesExample{
    public static void main(String[] args) {
        Predicate<String> isEmpty = s -> s.isEmpty();
        System.out.println("Empty string? " + isEmpty.test(""));

        Consumer<String> printer = s -> System.out.println("Printing: " + s);
        printer.accept("Hello world");

        Supplier<Double> random = () -> Math.random();
        System.out.println("Random: " + random.get());

        BiFunction<Integer, Integer, Integer> sum = (a,b) -> a + b;
        System.out.println("Sum 10+5: " + sum.apply(10, 5));

        TriFunction<String,String,String,String> concat3 = (a,b,c) -> a + b + c;
        System.out.println("Concatenation: " + concat3.apply("A", "B", "C"));

        NoReturnFunction<String> echo = (msg) -> System.out.println("Echo: " + msg);
        echo.execute("Hello!");
    }
}
