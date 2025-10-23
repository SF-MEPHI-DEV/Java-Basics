package ru.mephi.bonus.StreamApi.Examples;

import java.util.List;
import java.util.stream.Collectors;

public class ExceptionExample {
    public static void main(String[] args) {
        List<String> data = List.of("one", "two", "three");

        List<String> results = data.stream()
                .map(item -> {
                    try {
                        return riskyOperation(item);
                    } catch (Exception e) {
                        // Обработка исключения внутри лямбды
                        handleException(e);
                        // Возвращаем значение по умолчанию
                        // Чтобы корректно вернуть stream
                        return "default";
                    }
                })
                .collect(Collectors.toList());

        System.out.println(results);
    }
    static void handleException(Exception e) {
        System.out.println("Exception: " + e.getMessage() + " обработано ");
    }
    static String riskyOperation(String s) throws Exception {
        if ("two".equals(s)) {
            throw new Exception("Проблема с элементом: " + s);
        }
        return s.toUpperCase();
    }
}
