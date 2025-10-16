package ru.mephi.week8.lesson1.Examples;

public class BasicGenericsExample {

    public static void main(String[] args) {
        withoutGenerics();
        withGenerics();
        typeSafety();
    }

    public static void withoutGenerics() {
        System.out.println("=== Без дженериков ===");

        ObjectBox box1 = new ObjectBox();
        box1.set("Hello");
        String str = (String) box1.get();

        ObjectBox box2 = new ObjectBox();
        box2.set(123);
        try {
            String wrong = (String) box2.get();
        } catch (ClassCastException e) {
            System.out.println("ClassCastException в runtime");
        }
    }

    public static void withGenerics() {
        System.out.println("\n=== С дженериками ===");

        Box<String> stringBox = new Box<>();
        stringBox.set("Hello");
        System.out.println(stringBox.get());

        Box<Integer> intBox = new Box<>();
        intBox.set(123);
        System.out.println(intBox.get());
        Box<Double> doubleBox = new Box<>();
        doubleBox.set(3.14);
        System.out.println(doubleBox.get());
    }

    public static void typeSafety() {
        System.out.println("\n=== Type safety в Generics===");

        Box<String> stringBox = new Box<>();
        stringBox.set("Hello");
        //stringBox.set(123);

        String value = stringBox.get();
        System.out.println(value);
    }
}

class ObjectBox {
    private Object value;

    public void set(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }
}

class Box<Type> {
    private Type value;
    public void set(Type value) {
        this.value = value;
    }

    public Type get() {
        return value;
    }

    public boolean isEmpty() {
        return value == null;
    }
}
