package ru.mephi.bonus.StreamApi.Examples;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class PrimitiveStreamExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        IntStream intStream = Arrays.stream(numbers);

        IntStream evenNumbers = intStream.filter(n -> n % 2 == 0);

        IntStream squaredNumbers = evenNumbers.map(n -> n * n);

        int sum = squaredNumbers.sum();

        System.out.println("Сумма квадратов четных чисел: " + sum);

        OptionalInt maxEven = Arrays.stream(numbers)
                                    .filter(n -> n % 2 == 0)
                                    .max();

        maxEven.ifPresent(max -> System.out.println("Максимальное четное число: " + max));

        IntStream primes = IntStream.iterate(2, i -> i + 1)
                                    .filter(PrimitiveStreamExample::isPrime)
                                    .limit(10);

        System.out.println("Первые 10 простых чисел:");
        primes.forEach(System.out::println);
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
