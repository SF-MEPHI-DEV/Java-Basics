package ru.mephi.week8.lesson2.task1Solved;

import java.util.*;

public class Task1Solved {

    /**
     * <h2>Задание: частотный анализ</h2>
     * <br>
     * <h2>Описание</h2>
     * <p>Напишите программу на Java, которая принимает текстовый ввод и подсчитывает,
     * сколько раз каждое слово встречается в тексте. Программа должна игнорировать
     * регистр букв (например, слова “Java” и “java” считаются одинаковыми). В конце
     * программа должна вывести каждое слово и количество его появлений</p>
     */

    public static void main(String[] args) {

        String text = "По сути, хранимые сущности — это пары ключ-значение. Ключ должен быть уникальным в Map, чтобы мы могли однозначно найти по нему значение.\n" +
                "Ключом могут быть любые объекты Java, при этом для того, чтобы объект мог быть ключом в Map, на него налагаются определенные требования, которые мы обязательно разберем подробно при детальном знакомстве с этим интерфейсом в дальнейших материалах этого модуля.";

        // Преобразуем текст в массив слов, игнорируя знаки препинания и переводя в нижний регистр
        String[] words = text.toLowerCase().replaceAll("[^а-яА-Яa-zA-z ]", "").split("\\s+");

        // Используем HashMap для хранения частот слов
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Подсчитываем частоту каждого слова
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        // Выводим результат
        System.out.println("Word frequencies:");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

}
