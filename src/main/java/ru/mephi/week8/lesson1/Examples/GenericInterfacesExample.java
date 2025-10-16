package ru.mephi.week8.lesson1.Examples;

public class GenericInterfacesExample {

    public static void main(String[] args) {
        containerExample();
        processorExample();
        comparableExample();
    }

    public static void containerExample() {
        System.out.println("=== Container<T> ===");

        Container<String> stringContainer = new SimpleContainer<>();
        stringContainer.add("Hello");
        stringContainer.add("World");
        System.out.println(stringContainer.size());
        System.out.println(stringContainer.get(0));

        Container<Integer> intContainer = new SimpleContainer<>();
        intContainer.add(10);
        intContainer.add(20);
        intContainer.add(30);
        System.out.println(intContainer.size());
        System.out.println(intContainer.get(1));
    }

    public static void processorExample() {
        System.out.println("\n=== Processor<T> ===");

        Processor<String> upperCase = new UpperCaseProcessor();
        String result1 = upperCase.process("hello");
        System.out.println(result1);

        Processor<Integer> doubler = new DoublerProcessor();
        Integer result2 = doubler.process(5);
        System.out.println(result2);

        Processor<String> reverser = new ReverseProcessor();
        String result3 = reverser.process("world");
        System.out.println(result3);
    }

    public static void comparableExample() {
        System.out.println("\n=== Comparable<T> ===");

        Product p1 = new Product("Яблоко", 50);
        Product p2 = new Product("Банан", 30);
        Product p3 = new Product("Вишня", 50);

        System.out.println(p1.compareTo(p2));
        System.out.println(p2.compareTo(p1));
        System.out.println(p1.compareTo(p3));

        if (p1.compareTo(p2) > 0) {
            System.out.println(p1.getName() + " дороже");
        }
    }
}

interface Container<T> {
    void add(T element);
    T get(int index);
    int size();
}

class SimpleContainer<T> implements Container<T> {
    private Object[] array;
    private int size;

    public SimpleContainer() {
        array = new Object[10];
        size = 0;
    }

    @Override
    public void add(T element) {
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size++] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public int size() {
        return size;
    }
}

interface Processor<T> {
    T process(T input);
}

class UpperCaseProcessor implements Processor<String> {
    @Override
    public String process(String input) {
        return input.toUpperCase();
    }
}

class DoublerProcessor implements Processor<Integer> {
    @Override
    public Integer process(Integer input) {
        return input * 2;
    }
}

class ReverseProcessor implements Processor<String> {
    @Override
    public String process(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}

class Product implements Comparable<Product> {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.price, other.price);
    }
}
