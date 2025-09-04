package ru.mephi.week1.lesson2.Classes.Geometry;

// Прямоугольник - наследует от Shape и реализует Coloable
public class Rectangle extends Shape implements Coloable {
    protected double length; // длина
    protected double width;  // ширина
    
    // конструктор вызывает конструктор родителя
    public Rectangle(double length, double width) {
        super("Прямоугольник"); // вызов конструктора Shape
        this.length = length;
        this.width = width;
    }
    
    // реализация абстрактного метода родителя
    @Override
    public double getArea() {
        return length * width;
    }
    
    // реализация второго абстрактного метода
    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }
    
    // переопределение метода draw для более специфичного поведения
    @Override
    public void draw() {
        System.out.println("Рисую прямоугольник " + length + "x" + width + " цвета " + color);
    }
    
    // реализация метода из интерфейса Coloable
    @Override
    public String getColour() {
        return color; // используем поле из родительского класса
    }
    
    // собственные методы прямоугольника
    public double getLength() {
        return length;
    }
    
    public double getWidth() {
        return width;
    }
    
    public boolean isSquare() {
        return Math.abs(length - width) < 0.001; // проверка с учетом погрешности
    }
    
    // переопределение toString
    @Override
    public String toString() {
        return name + " " + length + "x" + width + " (площадь: " + String.format("%.2f", getArea()) + ")";
    }
}
