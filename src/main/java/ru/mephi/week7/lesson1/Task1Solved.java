package ru.mephi.week7.lesson1;

import java.util.Scanner;

public class Task1Solved {

    /**
     * <h2>Задача: калькулятор с обработкой ошибок</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Напишите программу, которая запрашивает у пользователя два числа и одну
     * математическую операцию (например: +, -, *, /). Программа должна выполнить
     * соответствующую операцию над числами и вывести результат. При этом программа
     * должна корректно обрабатывать следующие ошибки:</p>
     * <ul>
     * 	<li>Деление на ноль (ArithmeticException).</li>
     * 	<li>Неправильный формат чисел (если пользователь ввёл не число) (NumberFormatException).</li>
     * 	<li>Неподдерживаемую операцию (если пользователь ввёл что-то отличное от +, -, *, /).</li>
     * 	</ul>
     */

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите первое число: ");
            double num1 = Double.parseDouble(scanner.nextLine());

            System.out.print("Введите второе число: ");
            double num2 = Double.parseDouble(scanner.nextLine());

            System.out.print("Введите операцию: ");
            String operation = scanner.nextLine();

            double result;

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        throw new ArithmeticException("Деление на ноль запрещено" + num1 + "/" + num2);
                    }
                    result = num1 / num2;
                    break;
                default:
                    throw new UnsupportedOperationException("Операция не поддерживается.");
            }

            System.out.println("Результат: " + result);



        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный формат числа!");
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (UnsupportedOperationException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

    }

}
