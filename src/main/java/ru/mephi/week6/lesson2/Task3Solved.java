package ru.mephi.week6.lesson2;

import java.io.*;

public class Task3Solved {


    /**
     * <h2>Задание: Программа для анализа текстового файла</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Программа выполняет следующие действия:</p>
     * <ul>
     * <li>Читает текстовый файл, содержащий произвольный текст.</li>
     * <li>Подсчитывает количество строк, слов и символов в файле.</li>
     * <li>Определяет, сколько раз каждое слово встречается в тексте (игнорируя регистр).</li>
     * <li>Записывает результаты анализа в новый файл.</li>
     * </ul>
     */

    public static void main(String[] args) throws IOException {
        String inputFilePath = "src/main/java/ru/mephi/week6/lesson2/sample_text.txt";
        String outputFilePath = "src/main/java/ru/mephi/week6/lesson2/analysis_result.txt";

        final int MAX_WORDS = 1000;
        String[] uniqueWords = new String[MAX_WORDS];
        int[] wordFrequency = new int[MAX_WORDS];
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilePath));

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            lineCount++;
            charCount += line.length();

            line = line.replaceAll("[^a-zA-Zа-яА-Я0-9 ]", "").toLowerCase();

            String[] words = line.split("\\s+");

            for (String word : words) {
                if (!word.isEmpty()) {
                    wordCount++;
                    boolean found = false;
                    for (int i = 0; i < uniqueWords.length; i++) {
                        if (uniqueWords[i] != null && uniqueWords[i].equals(word)) {
                            wordFrequency[i]++;
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        for (int i = 0; i < uniqueWords.length; i++) {
                            if (uniqueWords[i] == null) {
                                uniqueWords[i] = word;
                                wordFrequency[i] = 1;
                                break;
                            }
                        }
                    }
                }
            }
        }

        bufferedWriter.write("Общее количество строк: " + lineCount);
        bufferedWriter.newLine();
        bufferedWriter.write("Общее количество слов: " + wordCount);
        bufferedWriter.newLine();
        bufferedWriter.write("Общее количество символов: " + charCount);
        bufferedWriter.newLine();
        bufferedWriter.write("Частота слов:");
        bufferedWriter.newLine();

        for (int i = 0; i < uniqueWords.length - 1; i++) {
            for (int j = i + 1; j < uniqueWords.length; j++) {
                if (wordFrequency[j] > wordFrequency[i]) {
                    String tempWord = uniqueWords[i];
                    uniqueWords[i] = uniqueWords[j];
                    uniqueWords[j] = tempWord;

                    int tempFreq = wordFrequency[i];
                    wordFrequency[i] = wordFrequency[j];
                    wordFrequency[j] = tempFreq;
                }
            }
        }

        for (int i = 0; i < uniqueWords.length; i++) {
            if (uniqueWords[i] != null) {
                bufferedWriter.write(uniqueWords[i] + ": " + wordFrequency[i]);
                bufferedWriter.newLine();
            }
        }

        System.out.println("Анализ завершён. Результаты записаны в " + outputFilePath);

        bufferedReader.close();
        bufferedWriter.close();

    }

}
