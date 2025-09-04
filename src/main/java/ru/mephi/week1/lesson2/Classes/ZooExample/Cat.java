package ru.mephi.week1.lesson2.Classes.ZooExample;

// Cat наследует от Animal и реализует два интерфейса
public class Cat extends Animal implements Soundable, Playable {
    private boolean isIndoor;
    
    public Cat(String name, int age) {
        super(name, age);
        this.isIndoor = true;
        this.habitat = "квартира";
    }
    
    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
        this.habitat = isIndoor ? "квартира" : "улица";
    }
    
    // реализация абстрактного метода из Animal
    @Override
    public void makeSound() {
        System.out.println(name + " мяукает: Мяу-мяу!");
    }
    
    // реализация абстрактного метода из Animal
    @Override
    public String getType() {
        return "Кот" + (isIndoor ? " домашний" : " уличный");
    }
    
    // реализация метода из интерфейса Playable
    @Override
    public void play() {
        System.out.println(name + " играет с клубком ниток");
    }
    
    // переопределение метода move
    @Override
    public void move() {
        System.out.println(name + " грациозно прыгает");
    }
    
    // собственные методы кота
    public void purr() {
        System.out.println(name + " мурчит");
    }
    
    public void climb() {
        System.out.println(name + " лазает по деревьям");
    }
    
    // переопределение default метода из интерфейса
    @Override
    public void makeQuietSound() {
        System.out.println(name + " тихо мурлычет");
    }
    
    public boolean isIndoor() {
        return isIndoor;
    }
    
    @Override
    public String toString() {
        return getType() + " по имени " + name + " (" + age + " лет)";
    }
}