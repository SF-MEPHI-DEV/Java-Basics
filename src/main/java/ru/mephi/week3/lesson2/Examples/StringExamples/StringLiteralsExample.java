package ru.mephi.week3.lesson2.Examples.StringExamples;

public class StringLiteralsExample {

    public static void main(String[] args) {
        System.out.println("=== ЛИТЕРАЛЫ СТРОК ===\n");

        String str1 = "Привет";
        String str2 = "Мир";

        System.out.println("Строка 1: " + str1);
        System.out.println("Строка 2: " + str2);


        // многострочный текст
        String multiline = "Первая строка\n" +
                          "Вторая строка\n" +
                          "Третья строка";
        String multiline17 = """
                one
                two 
                three
                """;
        System.out.println(multiline17);
        System.out.println("\nМногострочная строка:");
        System.out.println(multiline);

        String withTab = "Имя:\tИван\nВозраст:\t25";
        System.out.println("\nС табуляцией:");
        System.out.println(withTab);
    }
}