package ru.mephi.week1.lesson2.Classes.SimpleWrappers;

// Абстрактный базовый класс для всех геометрических фигур
public abstract class Shape {
    protected String name; // protected чтобы наследники могли обращаться
    
    public Shape() {
        this.name = "Неизвестная фигура";
    }
    
    public Shape(String name) {
        this.name = name;
    }
    
    // абстрактный метод - должен быть реализован в наследниках
    public abstract double getArea();
    
    // обычный метод - наследуется всеми фигурами
    public String getName() {
        return name;
    }
    
    // метод для отображения информации о фигуре
    public void displayInfo() {
        System.out.println(name + " с площадью: " + getArea() + " кв.см");
    }
}
