package ru.mephi.url_shortener.exceptions;

public class InvalidUrlException extends Exception {
    public InvalidUrlException(String message) {
        super(message);
    }
}
