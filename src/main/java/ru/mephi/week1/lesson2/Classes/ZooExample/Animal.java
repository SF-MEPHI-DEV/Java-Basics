package ru.mephi.week1.lesson2.Classes.ZooExample;

public abstract class Animal {
    // protected поля доступны наследникам
    protected String name;
    protected int age;
    protected String habitat;
    
    // приватное поле - доступно только внутри Animal
    private static int totalAnimals = 0;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        totalAnimals++;
        System.out.println("Создано животное: " + name);
    }
    
    // абстрактный метод - должен быть реализован в наследниках
    public abstract void makeSound();
    
    // абстрактный метод для получения типа животного
    public abstract String getType();
    
    // обычный метод с реализацией - наследуется всеми
    public void sleep() {
        System.out.println(name + " спит");
    }
    
    public void eat(String food) {
        System.out.println(name + " ест " + food);
    }
    
    // метод который можно переопределить
    public void move() {
        System.out.println(name + " передвигается");
    }
    
    // final метод - нельзя переопределить
    public final void breathe() {
        System.out.println(name + " дышит");
    }
    
    // геттеры для приватных и protected полей
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    
    public String getHabitat() {
        return habitat;
    }
    
    // статический метод
    public static int getTotalAnimals() {
        return totalAnimals;
    }
}