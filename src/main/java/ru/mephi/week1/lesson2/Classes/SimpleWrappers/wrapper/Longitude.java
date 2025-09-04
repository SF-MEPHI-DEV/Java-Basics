package ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper;

// Wrapper класс для долготы (longitude) - чтобы не путать с широтой
public class Longitude {
    // значение долготы в градусах
    public final double value;
    
    public Longitude(double value) {
        this.value = value;
    }
    
    // удобный метод для получения значения
    public double getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "Долгота: " + value + "°";
    }
}
