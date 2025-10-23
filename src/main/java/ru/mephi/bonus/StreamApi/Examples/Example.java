package ru.mephi.bonus.StreamApi.Examples;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {
    public static void main(String[] args) {
        Stream<Integer> myStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> myStream2 = myStream.filter(e -> {
            System.out.println(e);
            return e % 2 == 0;
        });
        //List<Integer> list = myStream2.toList();
        //System.out.println(list);
    }
}
