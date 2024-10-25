package ru.mephi.week8.lesson2.task3Solved;

import java.util.*;

public class Task3 {

    /**
     * <h2>Задание: Уникальные элементы из двух списков</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Напишите программу, которая принимает два списка целых чисел и находит
     * уникальные элементы, которые присутствуют только в одном из списков.
     * Результат должен быть выведен в виде списка уникальных элементов.</p>
     */

    public static void main(String[] args) {

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7, 8);

        List<Integer> uniqueElements = findUniqueElements(list1, list2);

        System.out.println("Уникальные элементы: " + uniqueElements);
    }

    public static List<Integer> findUniqueElements(List<Integer> list1, List<Integer> list2) {
        Set<Integer> uniqueSet = new HashSet<>();

        uniqueSet.addAll(list1);

        for (Integer num : list2) {
            if (!uniqueSet.add(num)) {
                uniqueSet.remove(num);
            }
        }

        return new ArrayList<>(uniqueSet);
    }

}
