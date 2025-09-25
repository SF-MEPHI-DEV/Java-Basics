package ru.mephi.week5.lesson1.Example;

import java.util.Random;

public class MathExample {
    public static void main(String[] args) {
        // Абсолютное значение - модуль числа
        int a = -10;
        System.out.println("abs(" + a + ") = " + Math.abs(a)); // 10
        // Максимум и минимум из двух чисел
        int x = 5;
        int y = 12;
        System.out.println("max(" + x + "," + y + ") = " + Math.max(x, y)); // 12
        System.out.println("min(" + x + "," + y + ") = " + Math.min(x, y)); // 5

        // Возведение в степень
        double base = 2;
        double exponent = 3;
        System.out.println(base + " ^ " + exponent + " = " + Math.pow(base, exponent)); // 8.0

        // Квадратный корень и кубический корень
        double number = 27;
        System.out.println("sqrt(" + number + ") = " + Math.sqrt(number)); // 5.196...
        System.out.println("cbrt(" + number + ") = " + Math.cbrt(number)); // 3.0

        // Округление чисел
        double d1 = 3.14;
        double d2 = 3.75;
        System.out.println("round(" + d1 + ") = " + Math.round(d1)); // 3
        System.out.println("round(" + d2 + ") = " + Math.round(d2)); //
        double d3 = 3.5;
        System.out.println("round(" + d3 + ") = " + Math.round(d3));
        System.out.println("ceil(" + d1 + ") = " + Math.ceil(d1));   // 4.0
        System.out.println("floor(" + d2 + ") = " + Math.floor(d2)); // 3.0

        int value = Math.clamp(12,-10,10);
        System.out.println("clamp(" + 12 + ") = " + value);

        // Тригонометрические функции (значения принимаются в радианах)
        double angleDeg = 45;
        double angleRad = Math.toRadians(angleDeg);
        double angleDeg2 = Math.toDegrees(angleRad);
        System.out.println("sin(" + angleDeg + "°) = " + Math.sin(angleRad));
        System.out.println("cos(" + angleDeg + "°) = " + Math.cos(angleRad));
        System.out.println("tan(" + angleDeg + "°) = " + Math.tan(angleRad));

        // Генерация случайных чисел в диапазоне [0,1)
        double randomValue = Math.random();
        // 10 - 20
        double randomValueClamp = 10 + Math.random() * (20-10);
        double result = Math.clamp(randomValueClamp, 12 , 18);
        System.out.println("Random value: " + randomValue);
        System.out.println("Random value between 20 - 10: " + randomValueClamp);

        // Использование констант Math.PI и Math.E
        System.out.println("PI = " + Math.PI);
        System.out.println("E = " + Math.E);

        Random random = new Random();
        random.nextDouble(10,20);
    }

    public static double randomInRange(int a , int b){
        double value = Math.random();
        return a + (b-a) * value;
    }
}
