package ru.mephi.bonus.StreamApi.Examples;

import java.util.*;
import java.util.stream.*;

public class CollectorsExample {
    static class Transaction {
        String userId;
        String category;
        double amount;
        String status;

        Transaction(String userId, String category, double amount, String status) {
            this.userId = userId;
            this.category = category;
            this.amount = amount;
            this.status = status;
        }

        @Override
        public String toString() {
            return userId + ": " + amount + " руб (" + category + ")";
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
            new Transaction("user1", "Продукты", 1500, "Завершено"),
            new Transaction("user2", "Транспорт", 300, "Завершено"),
            new Transaction("user1", "Развлечения", 2000, "Завершено"),
            new Transaction("user3", "Продукты", 800, "Отменено"),
            new Transaction("user2", "Продукты", 1200, "Завершено"),
            new Transaction("user1", "Транспорт", 450, "Завершено"),
            new Transaction("user3", "Развлечения", 3000, "Завершено"),
            new Transaction("user2", "Развлечения", 1800, "Отменено")
        );

        System.out.println("=== 1. toList - сбор в список ===");
        List<String> categories = transactions.stream()
            .map(t -> t.category)
            .distinct()
            .collect(Collectors.toList());
        System.out.println("Категории: " + categories);

        System.out.println("\n=== 2. toSet - сбор в множество (уникальные элементы) ===");
        Set<String> users = transactions.stream()
            .map(t -> t.userId)
            .collect(Collectors.toSet());
        System.out.println("Уникальные пользователи: " + users);

        System.out.println("\n=== 3. joining - объединение строк ===");
        String userList = transactions.stream()
            .map(t -> t.userId)
            .distinct()
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Список пользователей: " + userList);

        System.out.println("\n=== 4. groupingBy - группировка по ключу ===");
        Map<String, List<Transaction>> byCategory = transactions.stream()
            .collect(Collectors.groupingBy(t -> t.category));
        byCategory.forEach((category, trans) -> {
            System.out.println(category + ": " + trans.size() + " транзакций");
        });

        System.out.println("\n=== 5. groupingBy + summingDouble - сумма по группам ===");
        Map<String, Double> totalByUser = transactions.stream()
            .filter(t -> t.status.equals("Завершено"))
            .collect(Collectors.groupingBy(
                t -> t.userId,
                Collectors.summingDouble(t -> t.amount)
            ));
        totalByUser.forEach((user, total) -> 
            System.out.println(user + ": " + total + " руб"));

        System.out.println("\n=== 6. partitioningBy - разделение на две группы ===");
        Map<Boolean, List<Transaction>> partitioned = transactions.stream()
            .collect(Collectors.partitioningBy(t -> t.amount > 1000));
        System.out.println("Крупные транзакции (>1000): " + partitioned.get(true).size());
        System.out.println("Мелкие транзакции (<=1000): " + partitioned.get(false).size());

        System.out.println("\n=== 7. counting - подсчёт количества ===");
        Map<String, Long> countByCategory = transactions.stream()
            .collect(Collectors.groupingBy(
                t -> t.category,
                Collectors.counting()
            ));
        countByCategory.forEach((category, count) -> 
            System.out.println(category + ": " + count + " транзакций"));

        System.out.println("\n=== 8. averagingDouble - среднее значение ===");
        Map<String, Double> avgByCategory = transactions.stream()
            .filter(t -> t.status.equals("Завершено"))
            .collect(Collectors.groupingBy(
                t -> t.category,
                Collectors.averagingDouble(t -> t.amount)
            ));
        avgByCategory.forEach((category, avg) -> 
            System.out.println(category + ": средний чек " + String.format("%.2f", avg) + " руб"));

        System.out.println("\n=== 9. maxBy - максимальный элемент в группе ===");
        Map<String, Optional<Transaction>> maxByUser = transactions.stream()
            .collect(Collectors.groupingBy(
                t -> t.userId,
                Collectors.maxBy(Comparator.comparingDouble(t -> t.amount))
            ));
        maxByUser.forEach((user, trans) -> 
            trans.ifPresent(t -> System.out.println(user + ": макс. транзакция " + t.amount + " руб")));

        System.out.println("\n=== 10. toMap - создание карты ===");
        Map<String, Double> userToTotal = transactions.stream()
            .filter(t -> t.status.equals("Завершено"))
            .collect(Collectors.toMap(
                t -> t.userId,
                t -> t.amount,
                Double::sum
            ));
        userToTotal.forEach((user, total) -> 
            System.out.println(user + ": " + total + " руб"));
    }
}
