package ru.mephi.week3.lesson2;

import java.util.Arrays;
import java.util.Scanner;

public class Task1 {

    /**
     * <H2>Задача: Обратный порядок слов</H2>
     * <br>
     * <H2>Описание:</H2>
     * <p>Дана строка {@code s} измените порядок слов на обратный.
     * Слово определяется как последовательность символов без пробелов.
     * Составьте строку слов в обратном порядке, соединенных одним пробелом.</p>
     * <br>
     * <p>Обратите внимание, что {@code s} может содержать начальные или конечные пробелы
     * или несколько пробелов между двумя словами. Возвращаемая строка должна содержать
     * только один пробел, разделяющий слова. Не включайте никаких дополнительных пробелов.</p>
     * <br>
     * <H2>Пример:</H2>
     * Input: s = "the sky is blue"
     * Output: "blue is sky the"
     */

    public static void main(String[] args) {

        String s = "         the sky is blue   ";

        s = s.trim();
        String[] words = s.split(" ");

        StringBuilder answerBuilder = new StringBuilder();

        for (int i = words.length - 1;i >= 0; i -= 1) {
            answerBuilder.append(words[i] + " ");
        }

        System.out.println(answerBuilder.toString());

    }

}