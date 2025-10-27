package ru.mephi.json_parser;

import java.util.List;
import java.util.Map;

public class JsonValue {
    private final Object value;
    private final JsonType type;

    public enum JsonType {
        OBJECT, ARRAY, STRING, NUMBER, BOOLEAN, NULL
    }

    public JsonValue(Object value, JsonType type) {
        this.value = value;
        this.type = type;
    }

    public static JsonValue object(Map<String, JsonValue> value) {
        return new JsonValue(value, JsonType.OBJECT);
    }

    public static JsonValue array(List<JsonValue> value) {
        return new JsonValue(value, JsonType.ARRAY);
    }

    public static JsonValue string(String value) {
        return new JsonValue(value, JsonType.STRING);
    }

    public static JsonValue number(Number value) {
        return new JsonValue(value, JsonType.NUMBER);
    }

    public static JsonValue bool(boolean value) {
        return new JsonValue(value, JsonType.BOOLEAN);
    }

    public static JsonValue nullValue() {
        return new JsonValue(null, JsonType.NULL);
    }

    public Object getValue() {
        return value;
    }

    public JsonType getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    public Map<String, JsonValue> asObject() {
        return (Map<String, JsonValue>) value;
    }

    @SuppressWarnings("unchecked")
    public List<JsonValue> asArray() {
        return (List<JsonValue>) value;
    }

    public String asString() {
        return (String) value;
    }

    public Number asNumber() {
        return (Number) value;
    }

    public boolean asBoolean() {
        return (Boolean) value;
    }

    public boolean isObject() {
        return type == JsonType.OBJECT;
    }

    public boolean isArray() {
        return type == JsonType.ARRAY;
    }

    public boolean isString() {
        return type == JsonType.STRING;
    }

    public boolean isNumber() {
        return type == JsonType.NUMBER;
    }

    public boolean isBoolean() {
        return type == JsonType.BOOLEAN;
    }

    public boolean isNull() {
        return type == JsonType.NULL;
    }

    @Override
    public String toString() {
        return value != null ? value.toString() : "null";
    }
}
