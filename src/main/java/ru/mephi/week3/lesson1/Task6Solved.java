package ru.mephi.week3.lesson1;

import java.util.Arrays;

public class Task6Solved {

    /**
     *
     * <H2>Задача: возрастающие последовательности</H2>
     * <br>
     * <H2>Условие:</H2>
     * <p>Yайти все такие последовательности, которые содержат строго возрастающие элементы и при этом их длина больше заданного порога.</p>
     * 	<ul>
     * 	    <li>У тебя есть массив целых чисел arr[], в котором нужно искать строго возрастающие последовательности.</li>
     * 	    <li>Нужно найти все такие последовательности, длина которых строго больше заданного числа minLength.</li>
     * 	    <li>Каждая последовательность должна состоять из элементов, расположенных последовательно в исходном массиве.</li>
     * 	    <li>Возвращать нужно количество таких последовательностей и каждую такую последовательность (как отдельный массив).</li>
     * 	</ul>
     */

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 4, 1, 5, 6, 7, 1, 2, 3, 4};
        int minLength = 2;

        findIncreasingSequences(arr, minLength);
    }

    public static void findIncreasingSequences(int[] arr, int minLength) {

        int n = arr.length;
        int[][] result = new int[n][n];
        int sequencesCount = 0;

        int[] tempSequence = new int[n];
        int tempLength = 0;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                tempSequence[tempLength++] = arr[i];
            } else {
                if (tempLength > 0) {
                    tempSequence[tempLength++] = arr[i];

                    if (tempLength >= minLength) {
                        result[sequencesCount] = Arrays.copyOf(tempSequence, tempLength);
                        sequencesCount++;
                    }
                }
                tempLength = 0;
            }
        }

        if (tempLength > 0) {
            tempSequence[tempLength++] = arr[n - 1];
            if (tempLength > minLength) {
                result[sequencesCount] = Arrays.copyOf(tempSequence, tempLength);
                sequencesCount++;
            }
        }

        System.out.println("Number of sequences: " + sequencesCount);
        for (int i = 0; i < sequencesCount; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }

}
