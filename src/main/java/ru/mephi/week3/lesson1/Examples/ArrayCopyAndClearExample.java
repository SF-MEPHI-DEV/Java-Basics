package ru.mephi.week3.lesson1.Examples;

import java.util.Arrays;

public class ArrayCopyAndClearExample {

    public static void main(String[] args) {
        // Исходный массив объектов (например, строки)
        String[] srcArray = {"one", "two", "three", "four", "five"};
        String[] destArray = new String[srcArray.length];  // пустой массив того же размера

        // Копирование с помощью System.arraycopy
        System.arraycopy(srcArray, 1, destArray, 0, 3);

        //Ручное неэффективное копирование
        /*
        for(int i=1; i<4; i++){
            destArray[i-1] = srcArray[i];
        }
        */
        System.out.println("После System.arraycopy: " + Arrays.toString(destArray));
        // Результат: [two, three, four, null, null]

        // Ручное копирование с помощью цикла
        String[] manualCopy = new String[srcArray.length];
        for (int i = 0; i < srcArray.length; i++) {
            manualCopy[i] = srcArray[i];
        }
        System.out.println("Ручное копирование: " + Arrays.toString(manualCopy));
        // Результат: [one, two, three, four, five]

        // Очистка массива (установка элементов в null)
        Arrays.fill(manualCopy, 2, 5, null);
        System.out.println("После очистки с индекса 2: " + Arrays.toString(manualCopy));
        // Результат: [one, two, null, null, null]

        // Пример удаления элемента из массива (через сдвиг элементов влево)
        String[] arrayToDeleteFrom = {"A", "B", "C", "D", "E"};
        int deleteIndex = 2; // удаляем элемент с индексом 2 ("C")

        System.out.println("До удаления: " + Arrays.toString(arrayToDeleteFrom));
        // Сдвигаем элементы после deleteIndex влево
        System.arraycopy(arrayToDeleteFrom, deleteIndex + 1, arrayToDeleteFrom, deleteIndex, arrayToDeleteFrom.length - deleteIndex - 1);
        // Последний элемент очищаем (null для объектов)
        arrayToDeleteFrom[arrayToDeleteFrom.length - 1] = null;

        System.out.println("После удаления элемента с индексом " + deleteIndex + ": " + Arrays.toString(arrayToDeleteFrom));
        // Результат: [A, B, D, E, null]

        // Для примитивных типов (например, int) очистка происходит установкой 0
        int[] intArray = {10, 20, 30, 40, 50};
        Arrays.fill(intArray, 1, 4, 0);
        System.out.println("Очистка элементов int массива: " + Arrays.toString(intArray));
        // Результат: [10, 0, 0, 0, 50]
    }
}
