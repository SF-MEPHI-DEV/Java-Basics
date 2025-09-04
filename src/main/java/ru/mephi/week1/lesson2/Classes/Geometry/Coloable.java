package ru.mephi.week1.lesson2.Classes.Geometry;

// Интерфейс для объектов, которые могут быть окрашены
// Демонстрирует использование интерфейсов в ООП
public interface Coloable {
    // абстрактный метод - должен быть реализован в классах
    String getColour();
    
    // default метод - можно переопределить, но не обязательно (Java 8+)
    default void changeColor(String newColor) {
        System.out.println("Меняю цвет на " + newColor);
    }
    
    // static метод в интерфейсе (Java 8+)
    static boolean isValidColor(String color) {
        String[] validColors = {"красный", "синий", "зеленый", "желтый", "черный", "белый"};
        for (String validColor : validColors) {
            if (validColor.equals(color)) {
                return true;
            }
        }
        return false;
    }
}
