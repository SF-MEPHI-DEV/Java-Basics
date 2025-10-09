package ru.mephi.week7.lesson1.Examples.AppException;

public class AppException extends Exception{
    // Обьяснение ошибки на разных языках, например для использования в интерфейсе
    ErrorDescription description;
    public AppException(String message, ErrorDescription description) {
        super(message);
        this.description = description;
    }
}
