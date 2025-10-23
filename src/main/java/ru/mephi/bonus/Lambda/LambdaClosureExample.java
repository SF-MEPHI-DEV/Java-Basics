package ru.mephi.bonus.Lambda;

import java.util.function.*;

public class LambdaClosureExample {
    public static void example1(String[] args) {
        int factor = 3;
        // захват переменной
        Function<Integer, Integer> multiplier = (x) -> x * factor;

        System.out.println("5 * factor = " + multiplier.apply(5));

        // factor нельзя изменить после захвата,
        // следующая строка вызовет ошибку компиляции
        // factor = 10;
    }
    public static void example2() {
        String text = "Hello";

        // Ошибка компиляции:
        // переменная text уже определена в области видимости этого метода
        //Consumer<String> c1 = text -> System.out.println(text);

        // Рабочий вариант – используем другое имя
        Consumer<String> c2 =
                msg -> System.out.println(text + " " + msg);
    }

}

