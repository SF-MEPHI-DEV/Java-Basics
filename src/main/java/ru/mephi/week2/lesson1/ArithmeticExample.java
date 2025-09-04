package ru.mephi.week2.lesson1;

public class ArithmeticExample {
    public static void main(String[] args) {
        int a = 12;
        int b = 5;
        // Сложение
        int sum = a + b;         // 12 + 5 = 17
        // Вычитание
        int diff = a - b;        // 12 - 5 = 7
        // Умножение
        int prod = a * b;        // 12 * 5 = 60
        // Деление (целочисленное, остаток отбрасывается)
        int div = a / b;         // 12 / 5 = 2
        // Остаток от деления (modulus)
        int mod = a % b;         // 12 % 5 = 2 ( 12 = 5*2 + 2)
        // Унарные плюс и минус
        int positive = +b;       // +5 = 5
        int negative = -b;       // -5 = -5

        // Инкременты и декременты
        int c1 = 7;
        int c2 = 7;
        System.out.println(c1++);
        // c = 7 (постфиксный инкремент)
        System.out.println(++c2);
        // c = 8 (префиксный инкремент)
        System.out.println(c1--);
        // c = 8 (постфиксный декремент)
        System.out.println(--c2);
        // c = 7 (префиксный декремент)

        // Арифметические операторы с присваиванием
        int d = 10;
        d += 2;  // d = d + 2 = 12
        d -= 3;  // d = d - 3 = 9
        d *= 2;  // d = d * 2 = 18
        d /= 4;  // d = d / 4 = 4 (целочисленное деление)
        d %= 3;  // d = d % 3 = 1
    }
}
