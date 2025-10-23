package ru.mephi.bonus;

import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> nonEmpty = Optional.of("Привет, Optional!");

        Optional<String> possibleEmpty = Optional.ofNullable(null);

        // isPresent() для проверки наличия значения
        if (nonEmpty.isPresent()) {
            System.out.println("Значение: " + nonEmpty.get());
        }

        // Получение значения с помощью orElse()
        String value1 = possibleEmpty.orElse("Значение отсутствует");
        System.out.println(value1);

        // ifPresent() для выполнения действия
        if(nonEmpty.isPresent()){
            System.out.println("Длина строки: " + nonEmpty.get().length());
        }

        nonEmpty.ifPresent(v -> System.out.println("Длина строки: " + v.length()));

        Optional<Integer> length = nonEmpty.map(String::length);
        System.out.println("Длина (Optional): " + length.orElse(-1));

        // Получение пользователя и его электронного адреса, если он есть
        User user = new User("Ivan", null);
        String email = Optional.ofNullable(user)
                .map(User::getEmail)
                .orElse("Нет email");
        System.out.println(email);
    }

    static class User {
        private String name;
        // email необязателен
        private String email;
        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
        public String getName() { return name; }
        public String getEmail() { return email; }
    }
}
