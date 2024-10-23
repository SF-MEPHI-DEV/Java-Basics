package ru.mephi.week8.lesson1.task3Solved;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    /**
     * <h2>Задание: очередь задач</h2>
     * <br>
     * <p>Создать систему управления очередью задач на сервере, используя
     * LinkedList как реализацию очереди. Задачи поступают на выполнение
     * и ставятся в конец очереди. Каждые 10 секунд сервер обрабатывает
     * одну задачу из начала очереди. Новые задачи могут добавляться в
     * очередь во время работы сервера.</p>
     * <p>Условия:</p>
     * <ol>
     *  <li>Каждая задача имеет уникальный идентификатор.</li>
     * 	<li>Задачи обрабатываются в порядке поступления (FIFO — первым пришел, первым обслужен).</li>
     * 	<li>Каждые 10 секунд сервер обрабатывает одну задачу.</li>
     * 	<li>Новые задачи добавляются в конец очереди.</li>
     * 	<li>По окончании симуляции программа должна вывести оставшиеся в очереди задачи.</li>
     * </ol>
     */

    public static void main(String[] args) {

        Queue<Task> taskQueue = new LinkedList<>();

        taskQueue.add(new Task(1));
        taskQueue.add(new Task(2));
        taskQueue.add(new Task(3));

        int taskCounter = 4;
        int simulationTime = 60;

        for (int time = 10; time <= simulationTime; time += 10) {
            System.out.println("Время: " + time + " секунд");

            Task currentTask = taskQueue.poll();
            if (currentTask != null) {
                System.out.println("Обработка задачи: " + currentTask);
            } else {
                System.out.println("Нет задач для обработки.");
            }

            Task newTask = new Task(taskCounter);
            taskQueue.add(newTask);
            System.out.println("Новая задача добавлена: " + newTask);

            taskCounter++;
            System.out.println("Текущая очередь задач: " + taskQueue + "\n");
        }

        System.out.println("Задачи, оставшиеся в очереди после завершения симуляции: ");
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());
        }
    }

}
