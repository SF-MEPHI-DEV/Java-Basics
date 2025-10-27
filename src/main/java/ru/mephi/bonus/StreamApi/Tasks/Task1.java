package ru.mephi.bonus.StreamApi.Tasks;

import java.util.*;
import java.util.stream.*;

public class Task1 {

    /**
     * <h2>Задача: Анализ продаж магазина</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Дан список продаж магазина. Каждая продажа содержит информацию о товаре, категории,
     * цене и количестве проданных единиц. Необходимо найти топ-3 самых прибыльных категории
     * и вывести их с общей суммой выручки.</p>
     * <br>
     * <h2>Требования:</h2>
     * <ul>
     *     <li>Использовать Stream API для обработки данных</li>
     *     <li>Сгруппировать продажи по категориям</li>
     *     <li>Вычислить общую выручку для каждой категории (цена * количество)</li>
     *     <li>Отсортировать категории по убыванию выручки</li>
     *     <li>Вывести топ-3 категории с суммами</li>
     * </ul>
     * <br>
     * <h2>Пример входных данных:</h2>
     * <pre>
     * Sale("Телефон", "Электроника", 25000, 5)
     * Sale("Ноутбук", "Электроника", 50000, 3)
     * Sale("Футболка", "Одежда", 1500, 20)
     * Sale("Джинсы", "Одежда", 3000, 10)
     * Sale("Хлеб", "Продукты", 50, 100)
     * Sale("Молоко", "Продукты", 80, 50)
     * </pre>
     * <br>
     * <h2>Ожидаемый результат:</h2>
     * <pre>
     * Электроника: 275000.0
     * Одежда: 60000.0
     * Продукты: 9000.0
     * </pre>
     */

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

    }
}
