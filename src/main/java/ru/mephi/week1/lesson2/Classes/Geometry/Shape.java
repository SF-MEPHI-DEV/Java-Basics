package ru.mephi.week1.lesson2.Classes.Geometry;

// Абстрактный базовый класс для всех геометрических фигур
// Демонстрирует основы ООП: абстракцию, наследование, полиморфизм
public abstract class Shape {
    protected String name;     // имя фигуры
    protected String color;    // цвет фигуры
    
    // статический счетчик всех созданных фигур
    private static int totalShapes = 0;
    
    // конструктор принимает имя фигуры
    public Shape(String name) {
        this.name = name;
        this.color = "белый"; // цвет по умолчанию
        totalShapes++;
        System.out.println("Создана фигура: " + name);
    }
    
    // абстрактный метод - должен быть реализован в каждом наследнике
    public abstract double getArea();
    
    // абстрактный метод для периметра
    public abstract double getPerimeter();
    
    // конкретный метод - наследуется всеми фигурами
    public void displayInfo() {
        System.out.println("Фигура: " + name);
        System.out.println("Цвет: " + color);
        System.out.println("Площадь: " + String.format("%.2f", getArea()));
        System.out.println("Периметр: " + String.format("%.2f", getPerimeter()));
    }
    
    // метод можно переопределить в наследниках
    public void draw() {
        System.out.println("Рисую " + name + " цвета " + color);
    }
    
    // final метод - нельзя переопределить
    public final String getName() {
        return name;
    }
    
    // геттеры и сеттеры
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    // статический метод
    public static int getTotalShapes() {
        return totalShapes;
    }
    
    // переопределение toString для удобного вывода
    @Override
    public String toString() {
        return name + " (площадь: " + String.format("%.2f", getArea()) + ")";
    }
}
