package ru.mephi.bonus.Lambda;

public class LambdaSyntaxExamples {
    public static void main(String[] args) {
        // 1. Без параметров
        Runnable r = () -> System.out.println("No parameters");
        r.run();

        // 2. Один параметр, без скобок
        java.util.function.Consumer<String> c = s -> System.out.println(s);
        c.accept("Single parameter");

        // 3. Несколько параметров с типами
        java.util.function.BiFunction<Integer, Integer, Integer> add = (Integer a, Integer b) -> a + b;
        System.out.println("Add: " + add.apply(1, 2));

        // 4. Несколько параметров без типов
        java.util.function.BiPredicate<String, String> equals = (a, b) -> a.equals(b);
        System.out.println("Equals: " + equals.test("hello", "hello"));

        // 5. Многострочный блок с return и скобками
        java.util.function.Function<Integer, Integer> factorial = (n) -> {
            int result = 1;
            for(int i = 2; i <= n; i++) result *= i;
            return result;
        };
        System.out.println("Factorial 5: " + factorial.apply(5));
    }
}
