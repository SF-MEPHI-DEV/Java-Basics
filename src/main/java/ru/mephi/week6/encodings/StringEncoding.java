package ru.mephi.week6.encodings;

import java.nio.charset.Charset;

public class StringEncoding {

    public static void main(String[] args) {
        String text = "Пример строки";

        byte[] bytes = text.getBytes(Charset.forName("UTF-8"));
        System.out.println("Длина строки в UTF-8: " + bytes.length + " байт");
    }

}