package ru.mephi.week3.lesson1;

import java.util.Arrays;

public class Task1 {


    /**
     * <H2>Задача: объединение массивов</H2>
     * <br>
     * <H2>Описание:</H2>
     * <p>Вам даны два целочисленных массива {@code nums1} и {@code nums2}, отсортированных
     * в порядке неубывания</p>
     *
     * <p>Объедините {@code nums1} и {@code nums2} в один массив, отсортированный в порядке не убывания.</p>
     **/

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {2, 5, 6, 8, 15};

        // [1, 2, 2, 3, 4, 5, 6, 8, 15]

        int[] outArray = new int[nums1.length + nums2.length];

        int idx1 = 0;
        int idx2 = 0;

        for (int i = 0; i < outArray.length; i++) {

            if (idx1 >= nums1.length) {
                outArray[i] = nums2[idx2++];
            } else if (idx2 >= nums2.length) {
                outArray[i] = nums1[idx1++];
            } else if (nums1[idx1] < nums2[idx2]) {
                outArray[i] = nums1[idx1++];
            } else {
                outArray[i] = nums2[idx2++];
            }

        }

        System.out.println("out array: " + Arrays.toString(outArray));

    }

}
