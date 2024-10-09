package ru.mephi.week6.encodings;

import java.nio.charset.Charset;
import java.util.Map;

public class JVMAvailableEncodings {

    public static void main(String[] args) {

        Map<String, Charset> charsets = Charset.availableCharsets();
        for (var entry : charsets.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
