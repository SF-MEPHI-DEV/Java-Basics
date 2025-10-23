package ru.mephi.bonus.Lambda;

import java.util.function.Function;

public class LambdaExceptionHandling {
    // Обёртка чтобы ловить checked исключения
    @FunctionalInterface
    interface CheckedFunction<T, R> {
        R apply(T t) throws Exception;
    }

    // Преобразование CheckedFunction в RuntimeException
    public static <T, R> Function<T, R> wrap(CheckedFunction<T, R> func) {
        return t -> {
            try {
                return func.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static void main(String[] args) {
        Function<String, Integer> parseInt = wrap(s -> Integer.parseInt(s));

        System.out.println(parseInt.apply("123"));

        try {
            System.out.println(parseInt.apply("abc"));
        } catch (RuntimeException e) {
            System.out.println("Caught exception: " + e.getCause());
        }
    }
}
