package ru.mephi.week2.lesson1;

public class BitwiseOperationsExample {
    public static void main(String[] args) {
        int a = 12;  // 1100
        int b = 5;   // 0101
        byte c = 1;
        System.out.println("a = " + a + " (binary: " + Integer.toBinaryString(a) + ")");
        System.out.println("b = " + b + " (binary: " + Integer.toBinaryString(b) + ")");
        // 4 = 0100
        // Основные битовые операции
        System.out.println("a & b = " + (a & b) +" (binary: " + Integer.toBinaryString(a & b) + ")");  // AND: 4 (0100)
        System.out.println("a | b = " + (a | b));  // OR: 13 (1101)
        System.out.println("a ^ b = " + (a ^ b));  // XOR: 9 (1001)
        System.out.println("~a = " + (~a));        // NOT: -13
        
        // Сдвиги
        System.out.println("a << 1 = " + (a << 1)); // 24 (умножение на 2)
        System.out.println("a >> 1 = " + (a >> 1)); // 6 (деление на 2)
        
        // Практическое применение - проверка четности
        int number = 7;
        boolean isEven = (number & 1) == 0;
        System.out.println(number + " четное? " + isEven);
    }
}
