package ru.mephi.json_parser;

import java.util.Map;
import java.util.stream.Collectors;

public class JsonSerializer {
    private int indentLevel;
    public JsonSerializer() {
        this.indentLevel = 0;
    }

    public String serialize(JsonValue value) {
        indentLevel = 0;
        return serializeValue(value);
    }

    private String serializeValue(JsonValue value) {
        if (value.isNull()) {
            return "null";
        } else if (value.isBoolean()) {
            return String.valueOf(value.asBoolean());
        } else if (value.isNumber()) {
            Number num = value.asNumber();
            if (num instanceof Double || num instanceof Float) {
                return String.format("%.10f", num.doubleValue()).replaceAll("0*$", "").replaceAll("\\.$", ".0");
            }
            return num.toString();
        } else if (value.isString()) {
            return serializeString(value.asString());
        } else if (value.isArray()) {
            return serializeArray(value);
        } else if (value.isObject()) {
            return serializeObject(value);
        }
        return "null";
    }

    private String serializeString(String str) {
        StringBuilder sb = new StringBuilder("\"");
        for (char c : str.toCharArray()) {
            switch (c) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\b' -> sb.append("\\b");
                case '\f' -> sb.append("\\f");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                default -> {
                    if (c < 32) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        sb.append("\"");
        return sb.toString();
    }

    private String serializeArray(JsonValue value) {
        StringBuilder sb = new StringBuilder("[");


        var array = value.asArray();
        for (int i = 0; i < array.size(); i++) {
            sb.append(serializeValue(array.get(i)));
            if (i < array.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    private String serializeObject(JsonValue value) {
        StringBuilder sb = new StringBuilder("{");

        var obj = value.asObject();
        int index = 0;

        for (Map.Entry<String, JsonValue> entry : obj.entrySet()) {
            sb.append(serializeString(entry.getKey()));
            sb.append(":");

            sb.append(serializeValue(entry.getValue()));

            if (index < obj.size() - 1) {
                sb.append(",");
            }
            index++;
        }

        sb.append("}");
        return sb.toString();
    }

    private String getIndent() {
        return "  ".repeat(indentLevel);
    }
}
