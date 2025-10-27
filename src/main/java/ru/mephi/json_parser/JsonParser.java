package ru.mephi.json_parser;

import ru.mephi.json_parser.exceptions.JsonParseException;

import java.util.*;

public class JsonParser {
    private String json;
    private int position;

    public JsonValue parse(String json) throws JsonParseException {
        this.json = json;
        this.position = 0;
        skipWhitespace();
        JsonValue result = parseValue();
        skipWhitespace();

        if (position < json.length()) {
            throw new JsonParseException("Неожиданные символы после конца JSON", position);
        }

        return result;
    }

    private JsonValue parseValue() throws JsonParseException {
        skipWhitespace();

        if (position >= json.length()) {
            throw new JsonParseException("Неожиданный конец строки", position);
        }

        char current = json.charAt(position);

        if (current == '{') {
            return parseObject();
        } else if (current == '[') {
            return parseArray();
        } else if (current == '"') {
            return JsonValue.string(parseString());
        } else if (current == 't' || current == 'f') {
            return JsonValue.bool(parseBoolean());
        } else if (current == 'n') {
            parseNull();
            return JsonValue.nullValue();
        } else if (current == '-' || Character.isDigit(current)) {
            return JsonValue.number(parseNumber());
        } else {
            throw new JsonParseException("Неожиданный символ: " + current, position);
        }
    }

    private JsonValue parseObject() throws JsonParseException {
        Map<String, JsonValue> map = new LinkedHashMap<>();
        position++;
        skipWhitespace();

        if (position < json.length() && json.charAt(position) == '}') {
            position++;
            return JsonValue.object(map);
        }

        while (position < json.length()) {
            skipWhitespace();

            if (json.charAt(position) != '"') {
                throw new JsonParseException("Ожидается ключ в кавычках", position);
            }

            String key = parseString();
            skipWhitespace();

            if (position >= json.length() || json.charAt(position) != ':') {
                throw new JsonParseException("Ожидается ':' после ключа", position);
            }

            position++;
            JsonValue value = parseValue();
            map.put(key, value);
            skipWhitespace();

            if (position >= json.length()) {
                throw new JsonParseException("Неожиданный конец объекта", position);
            }

            char next = json.charAt(position);
            if (next == '}') {
                position++;
                return JsonValue.object(map);
            } else if (next == ',') {
                position++;
            } else {
                throw new JsonParseException("Ожидается ',' или '}'", position);
            }
        }

        throw new JsonParseException("Незакрытый объект", position);
    }

    private JsonValue parseArray() throws JsonParseException {
        List<JsonValue> list = new ArrayList<>();
        position++;
        skipWhitespace();

        if (position < json.length() && json.charAt(position) == ']') {
            position++;
            return JsonValue.array(list);
        }

        while (position < json.length()) {
            JsonValue value = parseValue();
            list.add(value);
            skipWhitespace();

            if (position >= json.length()) {
                throw new JsonParseException("Неожиданный конец массива", position);
            }

            char next = json.charAt(position);
            if (next == ']') {
                position++;
                return JsonValue.array(list);
            } else if (next == ',') {
                position++;
            } else {
                throw new JsonParseException("Ожидается ',' или ']'", position);
            }
        }

        throw new JsonParseException("Незакрытый массив", position);
    }

    private String parseString() throws JsonParseException {
        position++;
        StringBuilder sb = new StringBuilder();

        while (position < json.length()) {
            char c = json.charAt(position);

            if (c == '"') {
                position++;
                return sb.toString();
            } else if (c == '\\') {
                position++;
                if (position >= json.length()) {
                    throw new JsonParseException("Неожиданный конец строки после '\\'", position);
                }

                char escaped = json.charAt(position);
                switch (escaped) {
                    case '"' -> sb.append('"');
                    case '\\' -> sb.append('\\');
                    case '/' -> sb.append('/');
                    case 'b' -> sb.append('\b');
                    case 'f' -> sb.append('\f');
                    case 'n' -> sb.append('\n');
                    case 'r' -> sb.append('\r');
                    case 't' -> sb.append('\t');
                    case 'u' -> {
                        position++;
                        String unicode = json.substring(position, Math.min(position + 4, json.length()));
                        if (unicode.length() < 4) {
                            throw new JsonParseException("Неполный Unicode escape", position);
                        }
                        sb.append((char) Integer.parseInt(unicode, 16));
                        position += 3;
                    }
                    default -> throw new JsonParseException("Неизвестный escape: \\" + escaped, position);
                }
                position++;
            } else {
                sb.append(c);
                position++;
            }
        }

        throw new JsonParseException("Незакрытая строка", position);
    }

    private Number parseNumber() throws JsonParseException {
        int start = position;

        if (json.charAt(position) == '-') {
            position++;
        }

        if (position >= json.length() || !Character.isDigit(json.charAt(position))) {
            throw new JsonParseException("Ожидается цифра", position);
        }

        while (position < json.length() && Character.isDigit(json.charAt(position))) {
            position++;
        }

        boolean isDouble = false;

        if (position < json.length() && json.charAt(position) == '.') {
            isDouble = true;
            position++;

            if (position >= json.length() || !Character.isDigit(json.charAt(position))) {
                throw new JsonParseException("Ожидается цифра после '.'", position);
            }

            while (position < json.length() && Character.isDigit(json.charAt(position))) {
                position++;
            }
        }

        if (position < json.length() && (json.charAt(position) == 'e' || json.charAt(position) == 'E')) {
            isDouble = true;
            position++;

            if (position < json.length() && (json.charAt(position) == '+' || json.charAt(position) == '-')) {
                position++;
            }

            if (position >= json.length() || !Character.isDigit(json.charAt(position))) {
                throw new JsonParseException("Ожидается цифра в экспоненте", position);
            }

            while (position < json.length() && Character.isDigit(json.charAt(position))) {
                position++;
            }
        }

        String numberStr = json.substring(start, position);

        if (isDouble) {
            return Double.parseDouble(numberStr);
        } else {
            long value = Long.parseLong(numberStr);
            if (value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE) {
                return (int) value;
            }
            return value;
        }
    }

    private boolean parseBoolean() throws JsonParseException {
        if (json.startsWith("true", position)) {
            position += 4;
            return true;
        } else if (json.startsWith("false", position)) {
            position += 5;
            return false;
        } else {
            throw new JsonParseException("Ожидается 'true' или 'false'", position);
        }
    }

    private void parseNull() throws JsonParseException {
        if (json.startsWith("null", position)) {
            position += 4;
        } else {
            throw new JsonParseException("Ожидается 'null'", position);
        }
    }

    private void skipWhitespace() {
        while (position < json.length() && Character.isWhitespace(json.charAt(position))) {
            position++;
        }
    }
}
