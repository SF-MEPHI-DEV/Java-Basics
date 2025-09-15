package ru.mephi.week2.lesson1;

public class TernaryOperatorExample {
    public static void main(String[] args) {
        
        System.out.println("=== Сравнение if-else и тернарного оператора ===\n");
        
        // ПРИМЕР 1: Найти максимум из двух чисел
        int a = 15, b = 23;
        
        // С помощью if-else (многословно)
        int max1;
        if (a > b) {
            max1 = a;
        } else {
            max1 = b;
        }
        System.out.println("If-else: Максимум = " + max1);
        
        // С помощью тернарного оператора (компактно)
        int max2 = (a > b) ? a : b;
        System.out.println("Тернарный: Максимум = " + max2);
        
        System.out.println();
        
        // ПРИМЕР 2: Проверка четности
        int number = 42;
        
        // С помощью if-else
        String evenness1;
        if (number % 2 == 0) {
            evenness1 = "четное";
        } else {
            evenness1 = "нечетное";
        }
        System.out.println("If-else: " + number + " " + evenness1);
        
        // С помощью тернарного оператора
        String evenness2 = (number % 2 == 0) ? "четное" : "нечетное";
        System.out.println("Тернарный: " + number + " " + evenness2);
        
        System.out.println();
        
        // ПРИМЕР 3: Определение знака числа
        int x = -5;
        
        // С помощью if-else
        String sign1;
        if (x > 0) {
            sign1 = "положительное";
        } else if (x < 0) {
            sign1 = "отрицательное";
        } else {
            sign1 = "ноль";
        }
        System.out.println("If-else: " + x + " " + sign1);
        
        // С помощью тернарного оператора (вложенный)
        String sign2 = (x > 0) ? "положительное" : (x < 0) ? "отрицательное" : "ноль";
        System.out.println("Тернарный: " + x + " " + sign2);
        
        System.out.println();
        
        // ПРИМЕР 4: Возраст и категория
        int age = 16;
        
        // С помощью if-else
        String category1;
        if (age >= 18) {
            category1 = "взрослый";
        } else {
            category1 = "несовершеннолетний";
        }
        System.out.println("If-else: " + age + " лет - " + category1);
        
        // С помощью тернарного оператора
        String category2 = (age >= 18) ? "взрослый" : "несовершеннолетний";
        System.out.println("Тернарный: " + age + " лет - " + category2);
        
        System.out.println();
        
        // ПРИМЕР 5: Вычисление абсолютного значения
        int value = -10;
        
        // С помощью if-else
        int abs1;
        if (value < 0) {
            abs1 = -value;
        } else {
            abs1 = value;
        }
        System.out.println("If-else: |" + value + "| = " + abs1);
        
        // С помощью тернарного оператора
        int abs2 = (value < 0) ? -value : value;
        System.out.println("Тернарный: |" + value + "| = " + abs2);
        
        System.out.println();
        
        // ПРИМЕР 6: Тернарный оператор в выражениях
        int score = 85;
        
        // Можно использовать прямо в println
        System.out.println("Оценка: " + ((score >= 90) ? "отлично" : 
                                       (score >= 70) ? "хорошо" : "плохо"));
        
        // Или для установки значений переменных
        boolean isPassed = (score >= 60) ? true : false;
        System.out.println("Экзамен сдан: " + isPassed);
        
        // Еще короче (но менее читабельно)
        boolean isPassed2 = score >= 60; // Без тернарного оператора вообще!
        System.out.println("Экзамен сдан (упрощено): " + isPassed2);
        
        System.out.println("\n=== Выводы о тернарном операторе ===");
        System.out.println("✓ Компактнее, чем if-else");
        System.out.println("✓ Удобен для простых условий");
        System.out.println("✓ Можно использовать в выражениях");
        System.out.println("✗ Менее читаем при сложных условиях");
        System.out.println("✗ Не подходит для выполнения нескольких операций");
    }
}