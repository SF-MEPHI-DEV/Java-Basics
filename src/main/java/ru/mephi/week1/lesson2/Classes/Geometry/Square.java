package ru.mephi.week1.lesson2.Classes.Geometry;

// Квадрат - наследует от Rectangle (специальный случай прямоугольника)
// Демонстрирует многоуровневое наследование
public class Square extends Rectangle {
    
    public Square(double side) {
        super(side, side); // квадрат - это прямоугольник со сторонами равной длины
        this.name = "Квадрат"; // переопределяем имя
    }
    
    // переопределяем draw для квадрата
    @Override
    public void draw() {
        System.out.println("Рисую квадрат со стороной " + length + " цвета " + color);
    }
    
    // специальные методы для квадрата
    public double getSide() {
        return length; // для квадрата длина = ширине
    }
    
    public void setSide(double side) {
        if (side > 0) {
            this.length = side;
            this.width = side;
        }
    }
    
    // квадрат всегда квадратный :)
    @Override
    public boolean isSquare() {
        return true;
    }
    
    @Override
    public String toString() {
        return name + " (сторона: " + length + ", площадь: " + String.format("%.2f", getArea()) + ")";
    }
}