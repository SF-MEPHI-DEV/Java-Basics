package ru.mephi.week3.lesson2;

import java.util.Arrays;

public class Task1Solved {

    /**
     * <H2>Задача: Обратный порядок слов</H2>
     * <br>
     * <H2>Описание:</H2>
     * <p>Дана строка {@code s} измените порядок слов в обратном порядке.
     * Слово определяется как последовательность символов без пробелов.
     * Составьте строку слов в обратном порядке, соединенных одним пробелом.</p>
     * <br>
     * <p>Обратите внимание, что s может содержать начальные или конечные пробелы
     * или несколько пробелов между двумя словами. Возвращаемая строка должна содержать
     * только один пробел, разделяющий слова. Не включайте никаких дополнительных пробелов.</p>
     * <br>
     * <H2>Пример:</H2>
     * Input: s = "the sky is blue"
     * Output: "blue is sky the"
     */


    public static void main(String[] args) {

        String s = "   the sky is blue   ";

        /*
        // Approach 1

        s = s.trim();

        String[] words = s.split(" ");

        int left = 0;
        int right = words.length - 1;

        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }

        String outString =  String.join(" ", words);
        System.out.println("Out string: " + outString);
         */

        /*
        // Approach 2

        s = s.trim();
        String[] words = s.split(" ");

        StringBuilder answerBuilder = new StringBuilder();

        for (int i = words.length - 1; i > 0; i--) {
            answerBuilder.append(words[i] + " ");
        }

        answerBuilder.append(words[0]);

        System.out.println("Out string: " + answerBuilder);
         */

    }

}
