package ru.mephi.week2.lesson1;

public class ConditionalExample {
    public static void main(String[] args) {
        
        // Простой if-else
        int age = 18;
        if (age >= 18) {
            System.out.println("Совершеннолетний");
        } else {
            System.out.println("Несовершеннолетний");
        }
        
        // if-else if-else (проверка оценки)
        int score = 85;
        if (score >= 90) {
            System.out.println("Отлично");
        } else if (score >= 70) {
            System.out.println("Хорошо");
        } else {
            System.out.println("Плохо");
        }
        
        // Логические операторы
        boolean isStudent = true;
        boolean hasDiscount = false;
        
        if (isStudent && hasDiscount) {
            System.out.println("Скидка 15%");
        } else if (isStudent || hasDiscount) {
            System.out.println("Скидка 10%");
        } else {
            System.out.println("Полная цена");
        }
    }
}