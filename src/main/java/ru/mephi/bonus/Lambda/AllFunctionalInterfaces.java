package ru.mephi.bonus.Lambda;

import java.util.function.*;


public class AllFunctionalInterfaces {

    public static void basicInterfaces() {
        System.out.println("\n=== 1. ОСНОВНЫЕ ФУНКЦИОНАЛЬНЫЕ ИНТЕРФЕЙСЫ ===\n");

        //Runnable - void run()
        Runnable runnable = () -> System.out.println("Привет");
        Consumer<String> cons = (name) -> System.out.println("Привет " + name);
        // T -> void
        // Consumer<T> - void accept(T t);
        Consumer<String> consumer = s -> System.out.println("Consumer: " + s);
        consumer.accept("Hello");
        // Supplier<T> - T get();
        Supplier<Double> supplier = () -> Math.random();
        System.out.println("Supplier: " + supplier.get());

        // Function<T, R> - R apply(T t);
        Function<Integer, String> function = x -> "Result: " + x;
        System.out.println(function.apply(10));

        // Predicate<T> - boolean test(T t);
        Predicate<Integer> predicate = x -> x > 10;
        System.out.println("Predicate test 15: " + predicate.test(15));

        // UnaryOperator<T> extends Function<T, T> - T apply(T t);
        UnaryOperator<Integer> unaryOp = x -> x * 2;
        System.out.println("UnaryOperator 5 * 2: " + unaryOp.apply(5));
    }

    public static void binaryInterfaces() {
        System.out.println("\n=== 2. BINARY ФУНКЦИОНАЛЬНЫЕ ИНТЕРФЕЙСЫ ===\n");

        // BiConsumer<T, U> - void accept(T t, U u);
        BiConsumer<String, String> biConsumer = (a, b) -> System.out.println("BiConsumer: " + a + " " + b);
        biConsumer.accept("Hello", "World");

        // BiFunction<T, U, R> - R apply(T t, U u);
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a + b;
        System.out.println("BiFunction 5 + 3: " + biFunction.apply(5, 3));

        // BiPredicate<T, U> - boolean test(T t, U u);
        BiPredicate<Integer, Integer> biPredicate = (a, b) -> a > b;
        System.out.println("BiPredicate 10 > 5: " + biPredicate.test(10, 5));

        // BinaryOperator<T> extends BiFunction<T, T, T> - T apply(T t1, T t2);
        BinaryOperator<Integer> binaryOp = (a, b) -> a * b;
        System.out.println("BinaryOperator 4 * 5: " + binaryOp.apply(4, 5));
    }
    public static void intInterfaces() {
        System.out.println("\n=== 3. INT СПЕЦИАЛИЗИРОВАННЫЕ ИНТЕРФЕЙСЫ ===\n");

        // IntConsumer - void accept(int value);
        IntConsumer intConsumer = x -> System.out.println("IntConsumer: " + x);
        intConsumer.accept(100);

        // IntSupplier - int getAsInt();
        IntSupplier intSupplier = () -> 999;
        System.out.println("IntSupplier: " + intSupplier.getAsInt());

        // IntFunction<R> - R apply(int value);
        IntFunction<String> intFunction = x -> "Value: " + x;
        System.out.println(intFunction.apply(50));

        // IntPredicate - boolean test(int value);
        IntPredicate intPredicate = x -> x % 2 == 0;
        System.out.println("IntPredicate 8 is even: " + intPredicate.test(8));

        // IntUnaryOperator extends IntFunction - int applyAsInt(int operand);
        IntUnaryOperator intUnaryOp = x -> x * x;
        System.out.println("IntUnaryOperator 7 squared: " + intUnaryOp.applyAsInt(7));

        // IntBinaryOperator extends IntFunction - int applyAsInt(int left, int right);
        IntBinaryOperator intBinaryOp = (a, b) -> a - b;
        System.out.println("IntBinaryOperator 20 - 8: " + intBinaryOp.applyAsInt(20, 8));

        // ToIntFunction<T> - int applyAsInt(T value);
        ToIntFunction<String> toIntFunc = s -> s.length();
        System.out.println("ToIntFunction length of 'Hello': " + toIntFunc.applyAsInt("Hello"));

        // ToIntBiFunction<T, U> - int applyAsInt(T t, U u);
        ToIntBiFunction<String, String> toIntBiFunc = (a, b) -> (a + b).length();
        System.out.println("ToIntBiFunction length: " + toIntBiFunc.applyAsInt("Hello", "World"));

        // IntToLongFunction - long applyAsLong(int value);
        IntToLongFunction intToLongFunc = x -> (long) x * 1000;
        System.out.println("IntToLongFunction: " + intToLongFunc.applyAsLong(5));

        // IntToDoubleFunction - double applyAsDouble(int value);
        IntToDoubleFunction intToDoubleFunc = x -> x * 1.5;
        System.out.println("IntToDoubleFunction: " + intToDoubleFunc.applyAsDouble(4));
    }
    public static void longInterfaces() {
        System.out.println("\n=== 4. LONG СПЕЦИАЛИЗИРОВАННЫЕ ИНТЕРФЕЙСЫ ===\n");

        // LongConsumer - void accept(long value);
        LongConsumer longConsumer = x -> System.out.println("LongConsumer: " + x);
        longConsumer.accept(1000000L);

        // LongSupplier - long getAsLong();
        LongSupplier longSupplier = () -> System.currentTimeMillis();
        System.out.println("LongSupplier (timestamp): " + longSupplier.getAsLong());

        // LongFunction<R> - R apply(long value);
        LongFunction<String> longFunction = x -> "Long: " + x;
        System.out.println(longFunction.apply(500L));

        // LongPredicate - boolean test(long value);
        LongPredicate longPredicate = x -> x > 100L;
        System.out.println("LongPredicate 500 > 100: " + longPredicate.test(500L));

        // LongUnaryOperator - long applyAsLong(long operand);
        LongUnaryOperator longUnaryOp = x -> x + 10;
        System.out.println("LongUnaryOperator + 10: " + longUnaryOp.applyAsLong(90L));

        // LongBinaryOperator - long applyAsLong(long left, long right);
        LongBinaryOperator longBinaryOp = (a, b) -> a * b;
        System.out.println("LongBinaryOperator 6 * 7: " + longBinaryOp.applyAsLong(6L, 7L));

        // ToLongFunction<T> - long applyAsLong(T value);
        ToLongFunction<String> toLongFunc = s -> (long) s.length();
        System.out.println("ToLongFunction: " + toLongFunc.applyAsLong("Test"));

        // ToLongBiFunction<T, U> - long applyAsLong(T t, U u);
        ToLongBiFunction<String, String> toLongBiFunc = (a, b) -> (long) (a.length() + b.length());
        System.out.println("ToLongBiFunction: " + toLongBiFunc.applyAsLong("Hello", "World"));

        // LongToIntFunction - int applyAsInt(long value);
        LongToIntFunction longToIntFunc = x -> (int) (x / 1000);
        System.out.println("LongToIntFunction: " + longToIntFunc.applyAsInt(5000L));

        // LongToDoubleFunction - double applyAsDouble(long value);
        LongToDoubleFunction longToDoubleFunc = x -> x * 0.5;
        System.out.println("LongToDoubleFunction: " + longToDoubleFunc.applyAsDouble(100L));
    }
    public static void doubleInterfaces() {
        System.out.println("\n=== 5. DOUBLE СПЕЦИАЛИЗИРОВАННЫЕ ИНТЕРФЕЙСЫ ===\n");

        // DoubleConsumer - void accept(double value);
        DoubleConsumer doubleConsumer = x -> System.out.println("DoubleConsumer: " + x);
        doubleConsumer.accept(3.14);

        // DoubleSupplier - double getAsDouble();
        DoubleSupplier doubleSupplier = () -> Math.PI;
        System.out.println("DoubleSupplier (PI): " + doubleSupplier.getAsDouble());

        // DoubleFunction<R> - R apply(double value);
        DoubleFunction<String> doubleFunction = x -> "Value: " + x;
        System.out.println(doubleFunction.apply(2.71));

        // DoublePredicate - boolean test(double value);
        DoublePredicate doublePredicate = x -> x > 1.0;
        System.out.println("DoublePredicate 2.5 > 1.0: " + doublePredicate.test(2.5));

        // DoubleUnaryOperator - double applyAsDouble(double operand);
        DoubleUnaryOperator doubleUnaryOp = x -> Math.sqrt(x);
        System.out.println("DoubleUnaryOperator sqrt(16): " + doubleUnaryOp.applyAsDouble(16));

        // DoubleBinaryOperator - double applyAsDouble(double left, double right);
        DoubleBinaryOperator doubleBinaryOp = (a, b) -> Math.pow(a, b);
        System.out.println("DoubleBinaryOperator 2^3: " + doubleBinaryOp.applyAsDouble(2, 3));

        // ToDoubleFunction<T> - double applyAsDouble(T value);
        ToDoubleFunction<String> toDoubleFunc = s -> Double.parseDouble(s);
        System.out.println("ToDoubleFunction: " + toDoubleFunc.applyAsDouble("3.14"));

        // ToDoubleBiFunction<T, U> - double applyAsDouble(T t, U u);
        ToDoubleBiFunction<Integer, Integer> toDoubleBiFunc = (a, b) -> (double) (a + b) / 2;
        System.out.println("ToDoubleBiFunction average: " + toDoubleBiFunc.applyAsDouble(10, 20));

        // DoubleToIntFunction - int applyAsInt(double value);
        DoubleToIntFunction doubleToIntFunc = x -> (int) Math.round(x);
        System.out.println("DoubleToIntFunction round(3.7): " + doubleToIntFunc.applyAsInt(3.7));

        // DoubleToLongFunction - long applyAsLong(double value);
        DoubleToLongFunction doubleToLongFunc = x -> (long) x;
        System.out.println("DoubleToLongFunction: " + doubleToLongFunc.applyAsLong(99.9));
    }

    public static void otherInterfaces() {
        System.out.println("\n=== 6. ДРУГИЕ ВСТРОЕННЫЕ ФУНКЦИОНАЛЬНЫЕ ИНТЕРФЕЙСЫ ===\n");

        // Runnable - void run();
        Runnable runnable = () -> System.out.println("Runnable executing");
        runnable.run();

        // Callable<V> - V call() throws Exception;
        java.util.concurrent.Callable<Integer> callable = () -> 123;
        try {
            System.out.println("Callable result: " + callable.call());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Comparable<T> - int compareTo(T o);
        Comparable<Integer> comparable = x -> (x > 10) ? 1 : ((x < 10) ? -1 : 0);
        System.out.println("Comparable 15 vs 10: " + comparable.compareTo(15));

        // Comparator<T> - int compare(T o1, T o2);
        java.util.Comparator<String> comparator = (a, b) -> a.length() - b.length();
        System.out.println("Comparator 'Hello' vs 'Hi': " + comparator.compare("Hello", "Hi"));
    }

    public static void main(String[] args) {
        basicInterfaces();
        binaryInterfaces();
        intInterfaces();
        longInterfaces();
        doubleInterfaces();
        otherInterfaces();
    }
}
