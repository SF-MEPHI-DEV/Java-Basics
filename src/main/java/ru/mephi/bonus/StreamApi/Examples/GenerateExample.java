package ru.mephi.bonus.StreamApi.Examples;

import java.util.Random;
import java.util.stream.Stream;

public class GenerateExample {
    public static void main(String[] args) {
        Stream<Integer> randomNumbers = Stream.generate(() -> new Random().nextInt(100));

        randomNumbers
                .limit(5)
                .forEach(System.out::println);
    }
}