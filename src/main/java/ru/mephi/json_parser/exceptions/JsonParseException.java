package ru.mephi.json_parser.exceptions;

public class JsonParseException extends Exception {
    public JsonParseException(String message) {
        super(message);
    }

    public JsonParseException(String message, int position) {
        super(message + " на позиции " + position);
    }
}
