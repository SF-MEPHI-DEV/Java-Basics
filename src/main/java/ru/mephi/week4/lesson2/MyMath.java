package ru.mephi.week4.lesson2;

public class MyMath {

    int factorial(int value) {
        System.out.println("begin: " + value);
        if (value <= 1) return 1;
        return value * factorial(value - 1);
    }

}
