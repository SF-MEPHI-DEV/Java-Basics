package ru.mephi.bonus.StreamApi.Tasks;

import java.util.*;
import java.util.stream.*;

public class Task3 {

    /**
     * <h2>Задача: Поиск дубликатов email в списке пользователей</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Дан список пользователей с именем и email адресом. Необходимо найти все email адреса,
     * которые используются несколькими пользователями (дубликаты), и вывести для каждого такого
     * email список имен пользователей, которые его используют.</p>
     * <br>
     * <h2>Требования:</h2>
     * <ul>
     *     <li>Сгруппировать пользователей по email адресам</li>
     *     <li>Отфильтровать только те группы, где более одного пользователя</li>
     *     <li>Для каждого дублирующегося email собрать список имен пользователей</li>
     *     <li>Вывести результат в формате: "email: [имя1, имя2, ...]"</li>
     * </ul>
     * <br>
     * <h2>Пример входных данных:</h2>
     * <pre>
     * User("Иван Петров", "test@mail.ru")
     * User("Мария Сидорова", "maria@site.com")
     * User("Петр Иванов", "test@mail.ru")
     * User("Ольга Смирнова", "admin@site.com")
     * User("Анна Козлова", "admin@site.com")
     * User("Дмитрий Волков", "dmitry@site.com")
     * </pre>
     * <br>
     * <h2>Ожидаемый результат:</h2>
     * <pre>
     * test@mail.ru: [Иван Петров, Петр Иванов]
     * admin@site.com: [Ольга Смирнова, Анна Козлова]
     * </pre>
     */

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

    }
}
