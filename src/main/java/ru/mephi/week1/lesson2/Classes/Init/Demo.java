package ru.mephi.week1.lesson2.Classes.Init;

class Parent {
    // поля класса
    String name;
    int age;
    String defaultValue = "unknown"; // инициализация при объявлении
    
    // статический блок инициализации - выполняется при загрузке класса
    static {
        System.out.println("1. Статический блок Parent");
    }
    
    // блок инициализации экземпляра - выполняется перед каждым конструктором
    {
        System.out.println("3. Блок инициализации Parent");
        this.defaultValue = "initialized";
    }
    
    // конструктор по умолчанию
    public Parent() {
        System.out.println("4. Конструктор по умолчанию Parent");
    }
    
    // конструктор с одним параметром - вызывает другой конструктор
    public Parent(String name) {
        this(); // вызов конструктора по умолчанию
        this.name = name;
        System.out.println("5. Конструктор с именем Parent");
    }
    
    // конструктор с двумя параметрами
    public Parent(String name, int age) {
        this(name); // вызов конструктора с одним параметром
        this.age = age;
        System.out.println("6. Полный конструктор Parent");
    }
}

class Child extends Parent {
    String hobby;
    
    // статический блок дочернего класса
    static {
        System.out.println("2. Статический блок Child");
    }
    
    // блок инициализации экземпляра дочернего класса
    {
        System.out.println("7. Блок инициализации Child");
        this.hobby = "reading";
    }
    
    // конструктор дочернего класса
    public Child() {
        super(); // неявный вызов конструктора родителя
        System.out.println("8. Конструктор Child");
    }
    
    // конструктор с параметрами
    public Child(String name, int age) {
        super(name, age); // явный вызов конструктора родителя
        System.out.println("9. Конструктор Child с параметрами");
    }
}

public class Demo {
    public static void main(String[] args) {
        System.out.println("=== Создание первого Parent ===");
        Parent parent1 = new Parent("Pavel");
        
        System.out.println("\n=== Создание второго Parent ===");
        Parent parent2 = new Parent("Alexander", 25);
        
        System.out.println("\n=== Создание Child ===");
        Child child = new Child("Maria", 20);
        
        System.out.println("\nЗавершено");
    }
}
