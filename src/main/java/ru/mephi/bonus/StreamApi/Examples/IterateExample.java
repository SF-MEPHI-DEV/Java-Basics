package ru.mephi.bonus.StreamApi.Examples;

import java.util.stream.Stream;

public class IterateExample {
    public static void main(String[] args) {
        Stream<Integer> squares = Stream.iterate(1, n -> n + 1)
                .map(n -> n * n)
                .limit(10);

        squares.forEach(System.out::println);

        Stream.iterate(0, n -> n <= 20, n -> n + 2)
                .forEach(System.out::println);
    }
}