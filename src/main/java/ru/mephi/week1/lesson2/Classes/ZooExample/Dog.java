package ru.mephi.week1.lesson2.Classes.ZooExample;

// Dog наследует от Animal и реализует интерфейс Soundable
public class Dog extends Animal implements Soundable {
    private String breed;
    
    public Dog(String name, int age) {
        super(name, age); // вызов конструктора родителя
        this.breed = "дворняга";
        this.habitat = "дом"; // можем обращаться к protected полю родителя
    }
    
    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
        this.habitat = "дом";
    }
    
    // реализация абстрактного метода из Animal
    @Override
    public void makeSound() {
        System.out.println(name + " лает: Гав-гав!");
    }
    
    // реализация абстрактного метода из Animal
    @Override
    public String getType() {
        return "Собака породы " + breed;
    }
    
    // переопределение метода родителя
    @Override
    public void move() {
        System.out.println(name + " бегает на четырех лапах");
    }
    
    // переопределение метода eat с вызовом родительского
    @Override
    public void eat(String food) {
        System.out.println(name + " грызет " + food);
        super.eat(food); // вызов метода родителя
    }
    
    // собственный метод класса Dog
    public void wagTail() {
        System.out.println(name + " виляет хвостом");
    }
    
    public void fetch() {
        System.out.println(name + " приносит палку");
    }
    
    // геттер для breed
    public String getBreed() {
        return breed;
    }
    
    // переопределение toString для удобного вывода
    @Override
    public String toString() {
        return getType() + " по имени " + name + " (" + age + " лет)";
    }
}