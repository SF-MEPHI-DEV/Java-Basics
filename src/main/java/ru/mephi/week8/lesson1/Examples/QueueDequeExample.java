package ru.mephi.week8.lesson1.Examples;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueDequeExample {

    public static void main(String[] args) {
        queueExample();
        dequeExample();
        stackExample();
        linkedListVsArrayDeque();
        performanceComparison();
    }

    public static void queueExample() {
        System.out.println("=== Queue (FIFO) ===");

        Queue<String> queue = new LinkedList<>();

        queue.offer("Первый");
        queue.offer("Второй");
        queue.offer("Третий");
        System.out.println(queue);

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue);

        Queue<Integer> intQueue = new ArrayDeque<>();
        intQueue.offer(10);
        intQueue.offer(20);
        intQueue.offer(30);
        System.out.println(intQueue);
    }

    public static void dequeExample() {
        System.out.println("\n=== Deque (двусторонняя очередь) ===");

        Deque<String> deque = new ArrayDeque<>();

        deque.addFirst("A");
        deque.addLast("B");
        deque.addFirst("C");
        deque.addLast("D");

        System.out.println(deque);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());

        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque);
    }

    public static void stackExample() {
        System.out.println("\n=== Deque как Stack (LIFO) ===");

        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);

        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);

        System.out.println("\nArrayDeque - лучший выбор для стека (быстрее Stack и LinkedList)");
    }

    public static void linkedListVsArrayDeque() {
        System.out.println("\n=== LinkedList vs ArrayDeque ===");

        System.out.println("LinkedList:");
        System.out.println("+ Реализует List, Queue, Deque");
        System.out.println("+ Можно добавлять null");
        System.out.println("+ get(i), set(i) - доступ по индексу");
        System.out.println("- Медленнее ArrayDeque для Queue/Stack");
        System.out.println("- Больше памяти (каждый узел - объект)");

        System.out.println("\nArrayDeque:");
        System.out.println("+ Быстрее для Queue и Stack");
        System.out.println("+ Меньше памяти (массив внутри)");
        System.out.println("+ Нет overhead на узлы");
        System.out.println("- НЕ реализует List");
        System.out.println("- НЕ поддерживает null");
        System.out.println("- НЕТ get(i) по индексу");

        Deque<String> linkedList = new LinkedList<>();
        linkedList.add("test");
        linkedList.add(null);
        System.out.println("\n" + linkedList);

        Deque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.add("test");
        try {
            arrayDeque.add(null);
        } catch (NullPointerException e) {
            System.out.println("ArrayDeque не принимает null");
        }
    }

    public static void performanceComparison() {
        System.out.println("\n=== Производительность ===");

        int iterations = 100000;

        long start = System.nanoTime();
        Deque<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            linkedList.addLast(i);
        }
        for (int i = 0; i < iterations; i++) {
            linkedList.removeFirst();
        }
        long end = System.nanoTime();
        System.out.println("LinkedList (Queue): " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < iterations; i++) {
            arrayDeque.addLast(i);
        }
        for (int i = 0; i < iterations; i++) {
            arrayDeque.removeFirst();
        }
        end = System.nanoTime();
        System.out.println("ArrayDeque (Queue): " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        Deque<Integer> linkedStack = new LinkedList<>();
        for (int i = 0; i < iterations; i++) {
            linkedStack.push(i);
        }
        for (int i = 0; i < iterations; i++) {
            linkedStack.pop();
        }
        end = System.nanoTime();
        System.out.println("LinkedList (Stack): " + (end - start) / 1_000_000.0 + " мс");

        start = System.nanoTime();
        Deque<Integer> arrayStack = new ArrayDeque<>();
        for (int i = 0; i < iterations; i++) {
            arrayStack.push(i);
        }
        for (int i = 0; i < iterations; i++) {
            arrayStack.pop();
        }
        end = System.nanoTime();
        System.out.println("ArrayDeque (Stack): " + (end - start) / 1_000_000.0 + " мс");
    }
}
