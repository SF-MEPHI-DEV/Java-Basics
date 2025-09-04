package ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper;

// Wrapper класс для высоты - чтобы не путать с шириной
public class Height {
    // значение высоты в сантиметрах
    public final double value;
    
    public Height(double value) {
        this.value = value;
    }
    
    // удобный метод для получения значения
    public double getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "Высота: " + value + " см";
    }
}