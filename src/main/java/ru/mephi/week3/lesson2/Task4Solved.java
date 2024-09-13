package ru.mephi.week3.lesson2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4Solved {

    /**
     * <H2>Задача: Извлечение телефонных номеров из текста</H2>
     * <br>
     * <H2>Описание:</H2>
     * <p>Напишите программу, которая принимает строку, содержащую произвольный текст, и извлекает
     * из неё все телефонные номера. Телефонные номера могут быть в разных форматах:</p>
     * <ul>
     * 	<li>(123) 456-7890</li>
     *  <li>123-456-7890</li>
     * 	<li>123.456.7890</li>
     * 	<li>+1-123-456-7890</li>
     * </ul>
     */

    public static void main(String[] args) {

        String text = "Contact us at (123) 456-7890 or 123-456-7890. " +
                "Our international number is +1-123-456-7890. " +
                "You can also use 123.456.7890 for direct line.";

        String regex = "\\+?\\d{0,2}[- ]?\\(?\\d{3}\\)?[- .]?\\d{3}[- .]?\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Phone numbers found:");
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

    }
}
