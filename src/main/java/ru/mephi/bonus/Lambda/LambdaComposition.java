package ru.mephi.bonus.Lambda;

import java.util.function.*;

public class LambdaComposition {
    public static void main(String[] args) {
        Function<Integer, Integer> plusTwo = x -> x + 2;
        Function<Integer, Integer> timesThree = x -> x * 3;

        Function<Integer, Integer> combined = plusTwo.andThen(timesThree); // сначала +2, потом *3

        System.out.println("(5 + 2) * 3 = " + combined.apply(5));
    }
}
