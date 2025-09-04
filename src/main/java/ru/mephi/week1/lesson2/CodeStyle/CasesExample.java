package ru.mephi.week1.lesson2.CodeStyle;

public class CasesExample {
    // lowerCamelCase - для переменных, методов
    int myVariable = 10;
    String userName = "John";

    public void doSomethingImportant() {
        // метод в стиле lowerCamelCase
    }
    // PascalCase - для классов, интерфейсов
    public class MyClassExample {
        // Класс с именем PascalCase
    }

    int MAX_INT = 128;


    // UPPER_SNAKE_CASE - для констант (static final)
    public static final int MAX_COUNT = 100;
    public static final String API_KEY = "XYZ123";

    // snake_case - редко используется в Java, но могут быть в некоторых случаях (ресурсы, конфиги)
    String file_name = "data.txt";
    int cat_age = 5;

    // Кebab-case - НЕ поддерживается в Java как идентификатор,
    // но встречается в конфигурационных файлах и других языках
    // Например в yaml, json или именах файлов: my-variable-name

    // Пример enum с именами в UPPER_SNAKE_CASE
    enum Status {
        PENDING_APPROVAL,
        IN_PROGRESS,
        COMPLETED_SUCCESSFULLY,
        FAILED_CRITICALLY
    }
}
