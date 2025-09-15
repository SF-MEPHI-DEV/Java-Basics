package ru.mephi.week3.lesson1.Examples;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Исходный массив:");
        for (int num : data) {
            System.out.print(num + " ");
        }
        System.out.println();

        bubbleSort(data);

        System.out.println("Отсортированный массив:");
        for (int num : data) {
            System.out.print(num + " ");
        }
    }
}

