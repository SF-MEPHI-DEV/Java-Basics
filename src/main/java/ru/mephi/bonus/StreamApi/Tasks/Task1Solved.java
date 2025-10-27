package ru.mephi.bonus.StreamApi.Tasks;

import java.util.*;
import java.util.stream.*;

public class Task1Solved {

    static class Sale {
        String product;
        String category;
        double price;
        int quantity;

        public Sale(String product, String category, double price, int quantity) {
            this.product = product;
            this.category = category;
            this.price = price;
            this.quantity = quantity;
        }

        public String getCategory() {
            return category;
        }

        public double getRevenue() {
            return price * quantity;
        }
    }

    public static void main(String[] args) {
        List<Sale> sales = Arrays.asList(
                new Sale("Телефон", "Электроника", 25000, 5),
                new Sale("Ноутбук", "Электроника", 50000, 3),
                new Sale("Футболка", "Одежда", 1500, 20),
                new Sale("Джинсы", "Одежда", 3000, 10),
                new Sale("Хлеб", "Продукты", 50, 100),
                new Sale("Молоко", "Продукты", 80, 50)
        );
        //  "Электроника" - сумма выручки
        // "Одежда" - сумма прибыли
        sales.stream()
                .collect(Collectors.groupingBy(
                        Sale::getCategory,
                        Collectors.summingDouble(Sale::getRevenue)
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
