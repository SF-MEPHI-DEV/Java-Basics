package ru.mephi.week1.lesson2.Classes.ZooExample;

public interface Soundable {
    // все методы в интерфейсе public abstract по умолчанию
    void makeSound();
    
    // можно объявить константы - public static final по умолчанию
    int MAX_VOLUME = 100;
    
    // default метод - имеет реализацию (Java 8+)
    default void makeQuietSound() {
        System.out.println("Тихий звук...");
    }
    
    // статический метод в интерфейсе (Java 8+)
    static void printSoundInfo() {
        System.out.println("Максимальная громкость: " + MAX_VOLUME);
    }
}
