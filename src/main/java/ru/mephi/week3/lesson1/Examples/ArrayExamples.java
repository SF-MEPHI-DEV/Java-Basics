package ru.mephi.week3.lesson1.Examples;


public class ArrayExamples {
    public static void main(String[] args) {
        // Исходный массив
        int[] original = {1, 2, 3, 4, 5};

        // Обращение к неправильным индексам (пример с обработкой исключений)
        try {
            int outOfBounds = original[10];  // Выход за границы массива
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение: выход за границы массива при чтении элемента с индексом 10");
        }

        try {
            original[-1] = 50;  // Отрицательный индекс
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Исключение: выход за границы массива при записи элемента с индексом -1");
        }

        // Итерация по массиву разными способами

        System.out.println("Итерация по массиву с помощью классического for:");
        for (int i = 0; i < original.length; i++) {
            System.out.print(original[i] + " ");
        }
        System.out.println();

        System.out.println("Итерация по массиву с помощью for-each:");
        for (int value : original) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("Итерация по массиву с помощью while:");
        int index = 0;
        while (index < original.length) {
            System.out.print(original[index] + " ");
            index++;
        }
        System.out.println();
    }
}
