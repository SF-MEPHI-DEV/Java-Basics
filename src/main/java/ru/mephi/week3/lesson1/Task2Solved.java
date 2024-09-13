package ru.mephi.week3.lesson1;

import java.util.Arrays;

public class Task2Solved {

    /**
     * Дан целочисленный массив {@code nums} и целочисленное значение {@code val}, удалите
     * все вхождения {@code val} без создания нового массива и без изменения порядка следования
     */


    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 4, 5, 6, 7, 8, 2, 10};
        int val = 2;

        int offset = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == val) {
                offset += 1;
            } else {
                nums[i - offset] = nums[i];
            }

        }

        System.out.println("Count of deleted items: " + offset);
        System.out.println("Output array: " + Arrays.toString(nums));

        for (int i = 0; i < nums.length - offset; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
