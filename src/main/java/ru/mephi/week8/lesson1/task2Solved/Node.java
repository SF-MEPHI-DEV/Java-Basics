package ru.mephi.week8.lesson1.task2Solved;


public class Node <T> {
    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}