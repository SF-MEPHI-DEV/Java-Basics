package ru.mephi.week5.lesson1.Tasks.task3;

import java.util.Scanner;

public class TaskSolved {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numberToGuess = (int) (Math.random() * 100 + 1);
        int numberOfTries = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("Угадайте число от 1 до 100:");

        while (!hasGuessedCorrectly) {
            System.out.print("Введите вашу догадку: ");
            int userGuess = scanner.nextInt();
            numberOfTries++;

            if (userGuess < 1 || userGuess > 100) {
                System.out.println("Пожалуйста, введите число в диапазоне от 1 до 100.");
            } else if (userGuess < numberToGuess) {
                System.out.println("Слишком низко! Попробуйте еще раз.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Слишком высоко! Попробуйте еще раз.");
            } else {
                hasGuessedCorrectly = true;
                System.out.printf("Поздравляем! Вы угадали число %d за %d попыток.%n", numberToGuess, numberOfTries);
            }
        }

        scanner.close();
    }

}
