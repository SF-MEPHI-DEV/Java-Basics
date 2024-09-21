package ru.mephi.week3.lesson2;

public class Task5Solved {

    /**
     * <h2>Задача: соствить url запрос</h2>
     * <br>
     * <p>Пользователь вводит поисковый запрос, и вам нужно преобразовать его в корректный URL.
     * Поисковый запрос может содержать пробелы, которые должны быть заменены на символы %20,
     * а также могут быть лишние пробелы в начале и конце строки, которые нужно убрать.</p>
     *
     */

    public static void main(String[] args) {

        String query = "  Make this url correct  ";
        String formattedUrl = formatUrl(query);
        System.out.println("Formatted URL: " + formattedUrl);

    }

    public static String formatUrl(String query) {

        query = query.trim();

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("https://www.search.com?q=");

        for (int i = 0; i < query.length(); i++) {
            char currentChar = query.charAt(i);

            if (currentChar == ' ') {
                urlBuilder.append("%20");
            } else {
                urlBuilder.append(currentChar);
            }
        }

        return urlBuilder.toString();
    }


}
