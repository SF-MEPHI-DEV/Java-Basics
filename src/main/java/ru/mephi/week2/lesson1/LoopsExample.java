package ru.mephi.week2.lesson1;

public class LoopsExample {
    public static void main(String[] args) {
        
        // Цикл for
        System.out.println("=== For ===");
        for (int i = 1 ; i <= 10 ; i*=2 ) {
            System.out.println("i = " + i);
        }
        System.out.println("=== While ===");
        int j = 0;
        while( j < 5 ){
            System.out.println("j = " + j);
            j++;
        }
        
        // Цикл while
        System.out.println("\n=== While ===");
        int count = 0;
        while (count < 3) {
            System.out.println("count = " + count);
            count++;
        }
        
        // Цикл do-while
        System.out.println("\n=== Do-While ===");
        int x = 0;
        do {
            System.out.println("x = " + x);
            x++;
        } while (x < 3);
        
        // For-each с массивом
        System.out.println("\n=== For-each ===");
        int[] numbers = {10, 20, 30};
        for (int num : numbers) {
            System.out.println("Число: " + num);
        }
        
        // Break и continue
        System.out.println("\n=== Break и Continue ===");
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                continue; // Пропускаем 3
            }
            System.out.println("Число: " + i);
        }
    }
}