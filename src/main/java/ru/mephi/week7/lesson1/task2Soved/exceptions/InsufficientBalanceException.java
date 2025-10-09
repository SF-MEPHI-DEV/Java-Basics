package ru.mephi.week7.lesson1.task2Soved.exceptions;

public class InsufficientBalanceException extends Exception {
    public double delta;
    public InsufficientBalanceException(String message, double delta) {
        super(message);
        this.delta = delta;
    }
}
