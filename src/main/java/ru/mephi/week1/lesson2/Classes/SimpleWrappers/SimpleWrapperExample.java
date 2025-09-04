package ru.mephi.week1.lesson2.Classes.SimpleWrappers;

import ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper.Width;
import ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper.Height;
import ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper.Latitude;
import ru.mephi.week1.lesson2.Classes.SimpleWrappers.wrapper.Longitude;

public class SimpleWrapperExample {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация простых wrapper классов ===");
        
        // обычные значения размеров
        double width = 5.5;
        double height = 10.5;
        
        // создаем wrapper объекты - оборачиваем примитивы в классы
        Width widthObj = new Width(width);
        Height heightObj = new Height(height);
        
        System.out.println("Обычные значения:");
        System.out.println("Ширина: " + width + " см, Высота: " + height + " см");
        
        System.out.println("\nWrapper объекты:");
        System.out.println(widthObj);  // использует toString()
        System.out.println(heightObj); // использует toString()
        
        // создаем прямоугольники разными способами
        System.out.println("\n=== Создание прямоугольников ===");
        
        // способ 1 - с обычными значениями
        Rectangular rect1 = new Rectangular(width, height);
        System.out.println("Прямоугольник 1 (обычные значения):");
        rect1.displayInfo();
        
        // способ 2 - с wrapper объектами (ТИПОБЕЗОПАСНО)
        Rectangular rect2 = new Rectangular(widthObj, heightObj);
        System.out.println("\nПрямоугольник 2 (wrapper объекты):");
        System.out.println(rect2);
        
        // демонстрация с координатами
        System.out.println("\n=== Координаты (второй пример wrapper классов) ===");
        double lat = 55.75; // широта Москвы
        double lon = 37.61; // долгота Москвы
        
        Latitude latObj = new Latitude(lat);
        Longitude lonObj = new Longitude(lon);
        
        Location moscow = new Location(latObj, lonObj);
        System.out.println("Москва: " + moscow);
        System.out.println("Валидные координаты: " + moscow.isValid());
        
        // демонстрация что wrapper помогает избежать ошибок
        System.out.println("\n=== Польза wrapper классов ===");
        System.out.println("Wrapper классы помогают:");
        System.out.println("1. Типобезопасность - нельзя перепутать ширину с высотой или широту с долготой");
        System.out.println("2. Читаемость кода - понятно что это Width, Height, а не просто double");
        System.out.println("3. Дополнительная функциональность - методы toString(), getValue() и др.");
        
        // демонстрация главной проблемы которую решают wrapper классы
        System.out.println("\n=== Проблема путаницы аргументов ===");
        System.out.println("БЕЗ wrapper классов:");
        System.out.println("- createRectangle(5.5, 10.5) - где ширина, где высота?");
        System.out.println("- createLocation(55.75, 37.61) - где широта, где долгота?");
        System.out.println("- легко перепутать порядок параметров!");
        System.out.println("- компилятор не поймает такую ошибку!");
        
        System.out.println("\nС wrapper классами:");
        System.out.println("- new Rectangular(new Width(5.5), new Height(10.5)) - понятно!");
        System.out.println("- new Location(new Latitude(55.75), new Longitude(37.61)) - безопасно!");
        System.out.println("- если перепутать порядок - ошибка компиляции!");
        System.out.println("- компилятор защищает от ошибок типизации");
    }
}
