package ru.mephi.week1.lesson2.Classes.ZooExample;

// второй интерфейс для демонстрации множественной реализации
public interface Playable {
    void play();
    
    // default метод
    default void rest() {
        System.out.println("Отдыхает после игры");
    }
}