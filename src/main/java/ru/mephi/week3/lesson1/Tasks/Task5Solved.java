package ru.mephi.week3.lesson1.Tasks;

import java.util.Arrays;

public class Task5Solved {

    /**
     * <h2>Задача: поиск аномалий</h2>
     * <br>
     * <h2>Условие:</h2>
     * <p>Компания, которая занимается онлайн-продажами, собирает данные о покупках клиентов.
     * Эти данные хранятся в виде массива целых чисел, где каждый элемент массива представляет
     * сумму покупки клиента за определённый день. Компания заинтересована в выявлении аномалий
     * в поведении клиентов — таких дней, когда сумма покупки резко отличается от среднего
     * значения за предыдущие дни.</p>
     * <ul>
     * 	<li>У тебя есть массив целых чисел purchases[], в котором каждый элемент представляет сумму покупок в определённый день.</li>
     * 	<li>Твоя задача — для каждого дня (начиная с определённого момента, например с 4-го дня) вычислить среднюю сумму покупок за последние k дней (включая этот день) и проверить, насколько сильно текущая сумма отличается от среднего значения.</li>
     * 	<li>Если абсолютное отклонение текущей суммы от среднего больше заданного порога threshold, этот день считается аномальным.</li>
     * 	<li>Верни список индексов таких аномальных дней.</li>
     * 	</ul>
     */


    public static void main(String[] args) {
        int[] purchases = {100, 120, 80, 300, 150, 110, 500, 90, 200};
        int k = 3;
        int threshold = 100;

        int[] anomalies = findAnomalies(purchases, k, threshold);

        System.out.print("Days with anomalies: ");
        for (int anomaly : anomalies) {
            if (anomaly != -1) {
                System.out.print(anomaly + " ");
            }
        }
    }

    public static int[] findAnomalies(int[] purchases, int k, int threshold) {
        int n = purchases.length;
        int[] anomalies = new int[n];
        int anomalyCount = 0;

        Arrays.fill(anomalies, -1);

        for (int i = k - 1; i < n; i++) {
            int sum = 0;
            for (int j = i - k + 1; j <= i; j++) {
                sum += purchases[j];
            }
            double average = (double) sum / k;
            System.out.println("avg: " + average);

            if (Math.abs(purchases[i] - average) > threshold) {
                anomalies[anomalyCount++] = i;
            }
        }

        System.out.println(Arrays.toString(anomalies));

        return anomalies;
    }

}
