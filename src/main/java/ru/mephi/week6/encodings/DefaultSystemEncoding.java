package ru.mephi.week6.encodings;

import java.nio.charset.Charset;

public class DefaultSystemEncoding {

    public static void main(String[] args) {
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("Системная кодировка по умолчанию: " + defaultCharset);
    }

}
