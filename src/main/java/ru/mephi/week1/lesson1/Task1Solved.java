package ru.mephi.week1.lesson1;

import java.util.Arrays;

public class Task1Solved {

    /**
     * <H3>Задача 1</H1>
     * <p>Вам даны два целочисленных массива {@code nums1} и {@code nums2}, отсортированных
     * в порядке неубывания</p>
     *
     * <p>Объедините {@code nums1} и {@code nums2} в один массив, отсортированный в порядке убывания.</p>
     **/

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {2, 5, 6, 8, 15};

        /*
        // Approach 1

        int[] outArray = new int[nums1.length + nums2.length];

        for (int i = 0; i < nums1.length; i++) {
            outArray[i] = nums1[i];
        }
        for (int i = 0; i < nums2.length; i++) {
            outArray[i + nums1.length] = nums2[i];
        }

        Arrays.sort(outArray);
        System.out.println("Out array: " + Arrays.toString(outArray));
        */

        /*
        // Approach 2

        int[] outArray = new int[nums1.length + nums2.length];

        System.arraycopy(nums1, 0, outArray, 0, nums1.length);
        System.arraycopy(nums2, 0, outArray, nums1.length, nums2.length);

        Arrays.sort(outArray);
        System.out.println("Out array: " + Arrays.toString(outArray));
        */

        /*
        // Approach 3

        int[] outArray = new int[nums1.length + nums2.length];

        int idx1 = 0;
        int idx2 = 0;

        for (int i = 0; i < nums1.length + nums2.length; i++) {

            if (idx1 >= nums1.length) {
                outArray[i] = nums2[idx2++];
            } else if (idx2 >= nums2.length) {
                outArray[i] = nums1[idx1++];
            } else if (nums1[idx1] > nums2[idx2]) {
                outArray[i] = nums2[idx2++];
            } else {
                outArray[i] = nums1[idx1++];
            }

        }

        System.out.println("Out array: " + Arrays.toString(outArray));
        */


    }

}
