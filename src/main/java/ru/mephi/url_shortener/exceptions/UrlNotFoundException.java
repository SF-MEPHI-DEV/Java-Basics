package ru.mephi.url_shortener.exceptions;

public class UrlNotFoundException extends Exception {
    public UrlNotFoundException(String message) {
        super(message);
    }
}
