package ru.mephi.week3.lesson1.Tasks;

import java.util.Arrays;

public class Task3 {

    /**
     * Вам дана двумерная матрица {@code n x m}, представляющая изображение,
     * поверните изображение на 90 градусов (по часовой стрелке).
     */

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3, 5},
                {4, 5, 6, 6},
                {7, 8, 9, 7}
        };
        int originRows = matrix.length;
        int originColumns = matrix[0].length;
        for (int i = 0; i < originRows; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
        int[][] outArray = new int[originColumns][originRows];

        for (int i = 0; i < originRows; i++) {
            for (int j = 0; j < originColumns; j++) {
                outArray[j][originRows - i - 1] = matrix[i][j];
            }
        }

        for (int i = 0; i < originColumns; i++) {
            System.out.println(Arrays.toString(outArray[i]));
        }

    }


}
