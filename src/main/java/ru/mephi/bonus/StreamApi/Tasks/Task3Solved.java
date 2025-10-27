package ru.mephi.bonus.StreamApi.Tasks;

import java.util.*;
import java.util.stream.*;

public class Task3Solved {

    static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    public static void main(String[] args) {

        List<User> users = Arrays.asList(
                new User("Иван Петров", "test@mail.ru"),
                new User("Мария Сидорова", "maria@site.com"),
                new User("Петр Иванов", "test@mail.ru"),
                new User("Ольга Смирнова", "admin@site.com"),
                new User("Анна Козлова", "admin@site.com"),
                new User("Дмитрий Волков", "dmitry@site.com"),
                new User("Елена Новикова", "admin@site.com")
        );

        users.stream()
                .collect(Collectors.groupingBy(
                        User::getEmail,
                        Collectors.mapping(User::getName, Collectors.toList())
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
