package ru.mephi.week1.lesson2.Classes.SimpleWrappers;

import ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper.Latitude;
import ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper.Longitude;

// Класс для географической точки - демонстрирует пользу wrapper классов
public class Location extends Shape {
    private final double latitude;  // широта
    private final double longitude; // долгота
    
    // конструктор с обычными значениями - ОПАСНО! Можно перепутать порядок
    public Location(double latitude, double longitude) {
        super("Географическая точка");
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    // конструктор с wrapper объектами - БЕЗОПАСНО! Нельзя перепутать порядок
    public Location(Latitude latitude, Longitude longitude) {
        super("Географическая точка");
        this.latitude = latitude.value;   // извлекаем широту
        this.longitude = longitude.value; // извлекаем долготу
    }
    
    // метод не очень подходит для локации, но нужен для наследования от Shape
    @Override
    public double getArea() {
        return 0; // точка не имеет площади
    }
    
    // получить широту
    public double getLatitude() {
        return latitude;
    }
    
    // получить долготу
    public double getLongitude() {
        return longitude;
    }
    
    // получить координаты в виде строки
    public String getCoordinates() {
        return latitude + "°, " + longitude + "°";
    }
    
    // проверить валидность координат
    public boolean isValid() {
        return latitude >= -90 && latitude <= 90 && 
               longitude >= -180 && longitude <= 180;
    }
    
    @Override
    public String toString() {
        return "Локация{широта=" + latitude + "°, долгота=" + longitude + "°}";
    }
}