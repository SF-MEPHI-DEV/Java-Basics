package ru.mephi.week1.lesson2.Classes.ZooExample;

public class Example {
    // полиморфизм - метод принимает любой объект, реализующий Soundable
    public static void makeAnimalSound(Soundable animal) {
        animal.makeSound();
        animal.makeQuietSound(); // вызов default метода
    }
    
    // метод для демонстрации полиморфизма с Animal
    public static void feedAnimal(Animal animal) {
        System.out.println("Кормим " + animal.getType());
        animal.eat("корм");
    }
    
    // метод для игры с животными
    public static void playWithAnimal(Playable playable) {
        playable.play();
        playable.rest();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Демонстрация наследования и интерфейсов ===");
        
        // создание объектов
        Dog dog = new Dog("Бобик", 3, "лабрадор");
        Cat cat = new Cat("Мурзик", 2, true);
        
        System.out.println("\n=== Информация о животных ===");
        System.out.println(dog);
        System.out.println(cat);
        System.out.println("Всего животных: " + Animal.getTotalAnimals());
        
        System.out.println("\n=== Полиморфизм с Animal ===");
        Animal[] animals = {dog, cat};
        
        for (Animal animal : animals) {
            System.out.println("\n" + animal.getType() + ":");
            animal.makeSound(); // полиморфный вызов
            animal.move();      // полиморфный вызов
            animal.sleep();     // наследуемый метод
            animal.breathe();   // final метод
        }
        
        System.out.println("\n=== Полиморфизм с интерфейсами ===");
        
        // все животные реализуют Soundable
        Soundable[] soundables = {dog, cat};
        for (Soundable soundable : soundables) {
            makeAnimalSound(soundable);
        }
        
        // демонстрация интерфейса Playable
        System.out.println("\n=== Игры ===");
        playWithAnimal(cat); // кот реализует Playable
        // playWithAnimal(dog); // собака не реализует Playable
        
        System.out.println("\n=== Специфичные методы ===");
        dog.wagTail();
        dog.fetch();
        
        cat.purr();
        cat.climb();
        
        System.out.println("\n=== Кормление ===");
        feedAnimal(dog);
        feedAnimal(cat);
        
        System.out.println("\n=== Статические методы интерфейса ===");
        Soundable.printSoundInfo();
    }
}
