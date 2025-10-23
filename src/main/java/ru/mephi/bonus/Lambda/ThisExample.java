package ru.mephi.bonus.Lambda;

public class ThisExample {
    String name = "OuterClass";
    void run() {
        Runnable lambda = () -> System.out.println(this.name);
        Runnable anon = new Runnable() {
            String name = "AnonClass";
            @Override
            public void run() {
                System.out.println(this.name); // тут this — анонимный класс
            }
        };
        lambda.run(); // OuterClass
        anon.run();   // AnonClass
    }
    public static void main(String[] args) {
        new ThisExample().run();
    }
}
