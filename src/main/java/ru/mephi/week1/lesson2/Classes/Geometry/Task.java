package ru.mephi.week1.lesson2.Classes.Geometry;

/**
 * Демонстрация классического примера ООП с геометрическими фигурами
 * 
 * Показывает основные принципы ООП:
 * 1. Абстракция - абстрактный класс Shape
 * 2. Наследование - все фигуры наследуют от Shape
 * 3. Полиморфизм - один метод работает с разными типами фигур
 * 4. Инкапсуляция - скрытие деталей реализации
 * 5. Интерфейсы - Coloable определяет поведение окрашиваемых объектов
 */
public class Task {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация ООП на примере геометрических фигур ===\n");
        
        // создание различных фигур
        Rectangle rect = new Rectangle(5.0, 3.0);
        rect.setColor("красный");
        
        Square square = new Square(4.0);
        square.setColor("синий");
        
        Circle circle = new Circle(2.5);
        circle.setColor("зеленый");
        
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        triangle.setColor("желтый");
        
        System.out.println("=== Созданные фигуры ===");
        System.out.println("Всего фигур создано: " + Shape.getTotalShapes());
        
        // демонстрация полиморфизма - массив разных фигур
        Shape[] shapes = {rect, square, circle, triangle};
        
        System.out.println("\n=== Полиморфизм: обработка всех фигур одинаково ===");
        for (Shape shape : shapes) {
            System.out.println("\n--- " + shape.getName() + " ---");
            shape.displayInfo(); // полиморфный вызов
            shape.draw();        // полиморфный вызов
            
            // проверим, что фигура окрашиваемая
            if (shape instanceof Coloable) {
                displayColor((Coloable) shape);
            }
        }
        
        // демонстрация специфичных методов
        System.out.println("\n=== Специфичные методы фигур ===");
        System.out.println("Прямоугольник квадрат? " + rect.isSquare());
        System.out.println("Квадрат квадрат? " + square.isSquare());
        System.out.println("Диаметр круга: " + circle.getDiameter());
        System.out.println("Тип треугольника: " + triangle.getTriangleType());
        
        // демонстрация наследования - Square наследует методы Rectangle
        System.out.println("\n=== Демонстрация наследования ===");
        System.out.println("Квадрат - это Rectangle: " + (square instanceof Rectangle));
        System.out.println("Квадрат - это Shape: " + (square instanceof Shape));
        System.out.println("Длина квадрата: " + square.getLength());
        System.out.println("Ширина квадрата: " + square.getWidth());
        
        // демонстрация статических методов интерфейса
        System.out.println("\n=== Статические методы интерфейса ===");
        System.out.println("'красный' валидный цвет? " + Coloable.isValidColor("красный"));
        System.out.println("'фиолетовый' валидный цвет? " + Coloable.isValidColor("фиолетовый"));
        
        // сравнение площадей
        System.out.println("\n=== Сравнение фигур ===");
        findLargestShape(shapes);
        calculateTotalArea(shapes);
        
        System.out.println("\n=== Демонстрация завершена ===");
    }
    
    // метод принимает любую фигуру (полиморфизм)
    public static void displayArea(Shape shape) {
        System.out.println("Площадь " + shape.getName() + ": " + 
                         String.format("%.2f", shape.getArea()));
    }
    
    // метод работает с любым окрашиваемым объектом
    public static void displayColor(Coloable object) {
        System.out.println("Цвет: " + object.getColour());
    }
    
    // поиск фигуры с наибольшей площадью
    public static void findLargestShape(Shape[] shapes) {
        if (shapes.length == 0) return;
        
        Shape largest = shapes[0];
        for (Shape shape : shapes) {
            if (shape.getArea() > largest.getArea()) {
                largest = shape;
            }
        }
        System.out.println("Самая большая фигура: " + largest);
    }
    
    // подсчет общей площади всех фигур
    public static void calculateTotalArea(Shape[] shapes) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.getArea();
        }
        System.out.println("Общая площадь всех фигур: " + String.format("%.2f", totalArea));
    }
}
