package ru.mephi.week3.lesson1;

import java.util.Arrays;

public class Task3Solved {

    /**
     * Вам дана двумерная матрица {@code n x m}, представляющая изображение,
     * поверните изображение на 90 градусов (по часовой стрелке).
     */

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2,  3,  4},
                {5, 6,  7,  8},
                {9, 10, 11, 12}
        };

        int[][] outMatrix = new int[matrix[0].length][matrix.length];
        int rowsOrigin = matrix.length;
        int columnsOrigin = matrix[0].length;

        // Row in origin matrix
        for (int i = 0; i < rowsOrigin; i++) {

            // Columns in origin matrix
            for (int j = 0; j < columnsOrigin; j++) {
                outMatrix[j][rowsOrigin - i - 1] = matrix[i][j];
            }

        }

        for (int i = 0; i < outMatrix.length; i++) {
            System.out.println(Arrays.toString(outMatrix[i]));
        }

    }

}
