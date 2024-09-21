package ru.mephi.week3.lesson1;

import java.util.Arrays;

public class Task6 {

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

        /*
        as a result:
        [1, 2]
        [2, 3, 4]
        [1, 5, 6, 7]
        [1, 2, 3, 4]
         */

    }

    public static void findIncreasingSequences(int[] arr, int minLength) {
        // todo: write code
    }

}