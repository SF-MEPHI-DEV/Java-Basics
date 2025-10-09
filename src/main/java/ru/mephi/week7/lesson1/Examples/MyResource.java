package ru.mephi.week7.lesson1.Examples;

import java.io.Closeable;

public class MyResource implements Closeable {

    private final String name;

    public MyResource(String name) {
        this.name = name;
        System.out.println("MyResource [" + name + "] создан");
    }

    public void doSomething() {
        System.out.println("MyResource [" + name + "] выполняет работу");
    }

    @Override
    public void close() {
        System.out.println("MyResource [" + name + "] закрыт");
    }

    public static void main(String[] args) {
        System.out.println("=== Пример использования MyResource ===\n");

        // Использование в try-with-resources
        try (MyResource resource = new MyResource("Ресурс1")) {
            resource.doSomething();
        }

        System.out.println("\n=== Несколько ресурсов ===\n");

        try (MyResource r1 = new MyResource("Первый");
             MyResource r2 = new MyResource("Второй");
             MyResource r3 = new MyResource("Третий")) {

            r1.doSomething();
            r2.doSomething();
            r3.doSomething();
        }

        System.out.println("\n=== Программа завершена ===");
    }
}
