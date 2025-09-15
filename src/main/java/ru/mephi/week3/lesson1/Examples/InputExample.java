package ru.mephi.week3.lesson1.Examples;


import java.util.Scanner;

public class InputExample {
    public static void main(String[] args) {
        // Создаём объект Scanner для чтения из стандартного потока ввода (консоли)
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ваше имя: ");
        String name = scanner.nextLine();// Считываем строку целиком (ввод до перевода строки)

        System.out.print("Введите ваш возраст: ");
        int age = scanner.nextInt();       // Считываем целое число

        System.out.println("Привет, " + name + "! Твой возраст: " + age);

        scanner.close(); // Закрываем Scanner после использования (освобождаем ресурсы)
    }
}
