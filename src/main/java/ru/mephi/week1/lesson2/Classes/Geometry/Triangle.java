package ru.mephi.week1.lesson2.Classes.Geometry;

// Треугольник - еще один наследник Shape
public class Triangle extends Shape implements Coloable {
    private double sideA, sideB, sideC; // три стороны треугольника
    
    public Triangle(double sideA, double sideB, double sideC) {
        super("Треугольник");
        // проверка на возможность существования треугольника
        if (isValidTriangle(sideA, sideB, sideC)) {
            this.sideA = sideA;
            this.sideB = sideB;  
            this.sideC = sideC;
        } else {
            throw new IllegalArgumentException("Невозможно создать треугольник с такими сторонами!");
        }
    }
    
    // проверка правила треугольника
    private boolean isValidTriangle(double a, double b, double c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }
    
    // площадь по формуле Герона
    @Override
    public double getArea() {
        double s = getPerimeter() / 2; // полупериметр
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }
    
    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }
    
    @Override
    public void draw() {
        System.out.println("Рисую треугольник со сторонами " + 
                         sideA + ", " + sideB + ", " + sideC + " цвета " + color);
    }
    
    @Override
    public String getColour() {
        return color;
    }
    
    // дополнительные методы треугольника
    public boolean isEquilateral() {
        return Math.abs(sideA - sideB) < 0.001 && Math.abs(sideB - sideC) < 0.001;
    }
    
    public boolean isIsosceles() {
        return Math.abs(sideA - sideB) < 0.001 || 
               Math.abs(sideB - sideC) < 0.001 || 
               Math.abs(sideA - sideC) < 0.001;
    }
    
    public String getTriangleType() {
        if (isEquilateral()) return "равносторонний";
        if (isIsosceles()) return "равнобедренный";
        return "разносторонний";
    }
    
    @Override
    public String toString() {
        return name + " " + getTriangleType() + " (стороны: " + sideA + ", " + sideB + ", " + sideC + 
               ", площадь: " + String.format("%.2f", getArea()) + ")";
    }
}