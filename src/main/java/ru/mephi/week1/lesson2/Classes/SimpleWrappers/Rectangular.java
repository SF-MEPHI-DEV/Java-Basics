package ru.mephi.week1.lesson2.Classes.SimpleWrappers;

import ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper.Width;
import ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper.Height;

// Простой класс прямоугольника для демонстрации wrapper классов
public class Rectangular extends Shape {
    private final double width;   // ширина
    private final double height;  // высота
    
    // конструктор с обычными значениями
    public Rectangular(double width, double height) {
        super("Прямоугольник");
        this.width = width;
        this.height = height;
    }
    
    // конструктор с wrapper объектами - типобезопасный
    public Rectangular(Width width, Height height) {
        super("Прямоугольник");
        this.width = width.value;
        this.height = height.value;
    }
    
    // вычисление площади
    @Override
    public double getArea() {
        return width * height;
    }
    
    // вычисление периметра
    public double getPerimeter() {
        return 2 * (width + height);
    }
    
    // получить ширину
    public double getWidth() {
        return width;
    }
    
    // получить высоту
    public double getHeight() {
        return height;
    }
    
    // показать информацию о прямоугольнике
    public void displayInfo() {
        System.out.println("Размеры: " + width + " x " + height);
        System.out.println("Площадь: " + getArea());
        System.out.println("Периметр: " + getPerimeter());
    }
    
    @Override
    public String toString() {
        return "Прямоугольник " + width + "x" + height + " (площадь: " + getArea() + ")";
    }
}

