package ru.mephi.week7.lesson2.Examples.QueueStack;

public class Demo {

    public static void main(String[] args) {
        queueExample();
        stackExample();
    }

    public static void queueExample() {
        System.out.println("=== MyQueue (FIFO) ===");

        MyQueue queue = new MyQueue();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Очередь: " + queue);

        System.out.println("peek: " + queue.peek());
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("Осталось: " + queue);
        System.out.println("Размер: " + queue.size());
    }

    public static void stackExample() {
        System.out.println("\n=== MyStack (LIFO) ===");

        MyStack stack = new MyStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Стек: " + stack);

        System.out.println("peek: " + stack.peek());
        System.out.println("pop: " + stack.pop());
        System.out.println("pop: " + stack.pop());
        System.out.println("Осталось: " + stack);
        System.out.println("Размер: " + stack.size());
    }
}
