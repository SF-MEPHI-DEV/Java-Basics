package ru.mephi.week3.lesson2;

import java.util.Arrays;

public class Task2Solved {

    /**
     * <H2>Задача: Самый длинный общий префикс</H2>
     * <br>
     * <H2>Описание:</H2>
     *
     * <p>Напишите прогармму, которая найдет самую длинную строку с общим префиксом среди массива строк.</p>
     * <br>
     * <p>Если данные не соответствуют требованиям, программа сообщает пользователю, что необходимо исправить.</p>
     */


    public static void main(String[] args) {

        // String[] words = {"flower", "flow", "flight"};
        String[] words = {"dog", "racecar", "car"};

        Arrays.sort(words);

        String first = words[0];
        String last = words[words.length - 1];
        int minLength = first.length() <= last.length() ? first.length() : last.length();

        // Optional
        // int minLength = Math.min(first.length(), last.length());

        StringBuilder answerBuilder = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            if (first.charAt(i) != last.charAt(i)) {
                break;
            }
            answerBuilder.append(first.charAt(i));
        }

        if (answerBuilder.length() > 0) {
            System.out.println("Out word: " + answerBuilder);
        } else {
            System.out.println("Out word is empty!");
        }

    }

}
