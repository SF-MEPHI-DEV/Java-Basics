package ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper;

// Wrapper класс для ширины - чтобы не путать с высотой
public class Width {
    // значение ширины в сантиметрах
    public final double value;
    
    public Width(double value) {
        this.value = value;
    }
    
    // удобный метод для получения значения
    public double getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "Ширина: " + value + " см";
    }
}