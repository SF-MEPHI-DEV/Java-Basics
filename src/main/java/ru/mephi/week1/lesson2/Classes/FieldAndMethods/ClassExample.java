package ru.mephi.week1.lesson2.Classes.FieldAndMethods;

public class ClassExample {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация static и instance полей ===");
        
        // создание первого кота
        Cat cat1 = new Cat("Barsik", 5);
        cat1.sayHello();
        System.out.println("Вид: " + Cat.getSpecies()); // обращение к статической константе
        
        System.out.println();
        Cat.printCount(); // вызов статического метода
        
        // создание второго кота с породой
        Cat cat2 = new Cat("Murzik", 3, "сиамская");
        cat2.sayHello();
        
        System.out.println();
        Cat.printCount(); // счетчик увеличился
        
        // изменение статического поля влияет на всех котов
        System.out.println("\n=== Меняем приют для всех котов ===");
        Cat.changeShelter("Приют Надежда");
        
        cat1.sayHello(); // приют изменился для первого кота
        cat2.sayHello(); // и для второго тоже
        
        // демонстрация final полей
        System.out.println("\n=== Final поля ===");
        System.out.println("ID первого кота: " + cat1.getId());
        System.out.println("ID второго кота: " + cat2.getId());
        
        // можем изменить обычное поле экземпляра
        cat1.setBreed("персидская");
        System.out.println("После изменения породы:");
        cat1.sayHello();
    }
}
