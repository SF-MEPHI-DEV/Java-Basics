package ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper;

// Wrapper класс для широты (latitude) - чтобы не путать с долготой
public class Latitude {
    // значение широты в градусах
    public final double value;
    
    public Latitude(double value) {
        this.value = value;
    }
    
    // удобный метод для получения значения
    public double getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "Широта: " + value + "°";
    }
}
