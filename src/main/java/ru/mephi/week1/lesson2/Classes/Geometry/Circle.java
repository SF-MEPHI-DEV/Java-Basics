package ru.mephi.week1.lesson2.Classes.Geometry;

// Круг - еще один наследник Shape
public class Circle extends Shape implements Coloable {
    private double radius; // радиус
    
    public Circle(double radius) {
        super("Круг"); // вызов конструктора родителя
        this.radius = radius;
    }
    
    // реализация абстрактных методов
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    
    // переопределение draw
    @Override
    public void draw() {
        System.out.println("Рисую круг радиусом " + radius + " цвета " + color);
    }
    
    // реализация интерфейса
    @Override
    public String getColour() {
        return color;
    }
    
    // собственные методы
    public double getRadius() {
        return radius;
    }
    
    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        }
    }
    
    public double getDiameter() {
        return 2 * radius;
    }
    
    @Override
    public String toString() {
        return name + " (r=" + radius + ", площадь: " + String.format("%.2f", getArea()) + ")";
    }
}