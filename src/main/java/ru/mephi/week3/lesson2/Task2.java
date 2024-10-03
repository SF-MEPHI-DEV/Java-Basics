package ru.mephi.week3.lesson2;

import java.util.Arrays;

public class Task2 {

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

        String[] words = {"flower", "flow", "flight"};
        // prefix - "fl"

        // String[] words = {"dog", "racecar", "car"};
        // prefix - ""

        Arrays.sort(words);

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        int minLength = Math.min(firstWord.length(), lastWord.length());
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < minLength; i++) {

            if (firstWord.charAt(i) == lastWord.charAt(i)) {
                stringBuilder.append(lastWord.charAt(i));
            } else {
                break;
            }

        }

        if (stringBuilder.isEmpty()) {
            System.out.println("prefix is empty");
        } else {
            System.out.println("out string: \"%s\", %d".formatted(stringBuilder.toString(), words.length));
        }

    }

}
