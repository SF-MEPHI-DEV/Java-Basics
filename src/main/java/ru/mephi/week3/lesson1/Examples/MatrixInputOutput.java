package ru.mephi.week3.lesson1.Examples;
import java.util.Scanner;

public class MatrixInputOutput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод размеров матрицы
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        if(rows<=0){
            System.out.println("Недопустимое значение");
            return;
        }

        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();
        if(cols<=0){
            System.out.println("Недопустимое значение");
            return;
        }

        // Объявляем двумерный массив (массив массивов)
        int[][] matrix = new int[rows][cols];
        // Ввод элементов матрицы построчно
        System.out.println("Введите элементы матрицы построчно (через пробел):");
        for (int i = 0; i < rows; i++) {
            System.out.print("Строка " + (i + 1) + ": ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Вывод матрицы
        System.out.println("Введённая матрица:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

        scanner.close();
    }
}
