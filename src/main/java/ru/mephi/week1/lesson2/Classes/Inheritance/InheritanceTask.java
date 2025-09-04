package ru.mephi.week1.lesson2.Classes.Inheritance;

/**
 * Комплексная демонстрация наследования и модификаторов доступа в Java
 * 
 * Показывает:
 * 1. Модификаторы доступа (public, protected, default, private)
 * 2. Переопределение методов (@Override)
 * 3. Многоуровневое наследование
 * 4. Полиморфизм
 * 5. Ключевые слова super, instanceof
 * 6. Статические поля и методы при наследовании
 */
public class InheritanceTask {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация наследования и модификаторов доступа ===\n");
        
        // Создание объектов разных уровней наследования
        System.out.println("=== Создание объектов ===");
        Base base = new Base();
        Derived derived = new Derived();  
        DoubleDerived doubleDerived = new DoubleDerived();
        
        System.out.println("Создано объектов Base: " + Base.instanceCount);
        
        // Демонстрация полиморфизма
        System.out.println("\n=== Полиморфизм ===");
        System.out.println("Один метод работает с разными типами объектов:\n");
        callBaseMethod(base);
        callBaseMethod(derived);
        callBaseMethod(doubleDerived);
        
        // Демонстрация переопределения методов
        System.out.println("\n=== Переопределение методов ===");
        demonstrateOverriding(base, derived, doubleDerived);
        
        // Демонстрация модификаторов доступа
        System.out.println("\n=== Модификаторы доступа ===");
        demonstrateAccessModifiers(derived);
        
        // Демонстрация многоуровневого наследования
        System.out.println("\n=== Многоуровневое наследование ===");
        demonstrateMultiLevelInheritance(doubleDerived);
        
        // Демонстрация instanceof
        System.out.println("\n=== instanceof проверки ===");
        demonstrateInstanceOf(base, derived, doubleDerived);
        
        // Демонстрация статических методов
        System.out.println("\n=== Статические методы и наследование ===");
        demonstrateStaticMethods();
        
        // Демонстрация приведения типов
        System.out.println("\n=== Приведение типов ===");
        demonstrateCasting();
        
        System.out.println("\n=== Демонстрация завершена ===");
    }
    
    // Полиморфный метод - принимает любой объект типа Base или его наследников
    public static void callBaseMethod(Base object) {
        System.out.println("Полиморфный вызов для " + object.getClass().getSimpleName() + ":");
        object.basePublicMethod(); // вызывается переопределенная версия
        System.out.println();
    }
    
    // Демонстрация переопределения методов
    public static void demonstrateOverriding(Base base, Derived derived, DoubleDerived doubleDerived) {
        System.out.println("Один и тот же метод ведет себя по-разному:");
        
        System.out.println("Base:");
        base.basePublicMethod();
        
        System.out.println("\nDerived (переопределен):");
        derived.basePublicMethod();
        
        System.out.println("\nDoubleDerived (переопределен второй раз):");
        doubleDerived.basePublicMethod();
    }
    
    // Демонстрация модификаторов доступа
    public static void demonstrateAccessModifiers(Derived derived) {
        System.out.println("Что доступно наследнику:");
        
        // PUBLIC - доступно везде
        derived.basePublicField = "Изменено извне";
        System.out.println("✓ PUBLIC поле доступно: " + derived.basePublicField);
        
        // PROTECTED - доступно наследникам (но не извне пакета)
        derived.baseProtectedField = "Изменено в том же пакете";
        System.out.println("✓ PROTECTED поле доступно в пакете: " + derived.baseProtectedField);
        
        // DEFAULT - доступно в пакете
        derived.baseDefaultField = "DEFAULT тоже доступно";
        System.out.println("✓ DEFAULT поле доступно в пакете: " + derived.baseDefaultField);
        
        // PRIVATE - НЕ доступно напрямую
        // derived.basePrivateField = "Ошибка!"; // COMPILATION ERROR!
        System.out.println("❌ PRIVATE поле НЕ доступно напрямую");
        System.out.println("✓ PRIVATE поле через геттер: " + derived.getPrivateField());
        
        // Демонстрация методов наследника
        System.out.println("\nДоступность методов:");
        derived.demonstrateAccess();
        derived.demonstrateMethodAccess();
    }
    
    // Демонстрация многоуровневого наследования
    public static void demonstrateMultiLevelInheritance(DoubleDerived doubleDerived) {
        System.out.println("DoubleDerived наследует от Derived, который наследует от Base");
        
        // Методы всех уровней доступны
        doubleDerived.showAllFields();          // от Base
        doubleDerived.derivedMethod();          // от Derived
        doubleDerived.doubleDerivedMethod();    // собственный
        
        // Доступ к полям всех уровней
        doubleDerived.demonstrateMultiLevelAccess();
        
        // instanceof проверки
        doubleDerived.demonstrateInstanceOf();
    }
    
    // Демонстрация instanceof
    public static void demonstrateInstanceOf(Base base, Derived derived, DoubleDerived doubleDerived) {
        Object[] objects = {base, derived, doubleDerived};
        String[] names = {"Base", "Derived", "DoubleDerived"};
        
        for (int i = 0; i < objects.length; i++) {
            Object obj = objects[i];
            String name = names[i];
            
            System.out.println(name + ":");
            System.out.println("  instanceof Base: " + (obj instanceof Base));
            System.out.println("  instanceof Derived: " + (obj instanceof Derived));
            System.out.println("  instanceof DoubleDerived: " + (obj instanceof DoubleDerived));
            System.out.println("  instanceof Object: " + (obj instanceof Object));
        }
    }
    
    // Демонстрация статических методов
    public static void demonstrateStaticMethods() {
        System.out.println("Статические методы принадлежат классу, не экземпляру:");
        
        // Статические методы вызываются через имя класса
        Base.publicStaticMethod();
        Derived.derivedStaticMethod();
        
        // Статические поля тоже принадлежат классу
        System.out.println("Всего создано объектов Base: " + Base.instanceCount);
    }
    
    // Демонстрация приведения типов (casting)
    public static void demonstrateCasting() {
        System.out.println("Приведение типов при наследовании:");
        
        // Автоматическое приведение к родительскому типу (upcasting)
        Derived derived = new Derived();
        Base base = derived; // автоматическое приведение
        System.out.println("✓ Upcasting (автоматическое): Derived -> Base");
        
        // Явное приведение к дочернему типу (downcasting)
        if (base instanceof Derived) {
            Derived derivedAgain = (Derived) base; // явное приведение
            derivedAgain.derivedMethod(); // теперь можем вызывать методы Derived
            System.out.println("✓ Downcasting (явное): Base -> Derived");
        }
        
        // Безопасное приведение типов
        Base[] objects = {new Base(), new Derived(), new DoubleDerived()};
        
        System.out.println("\nБезопасное приведение типов:");
        for (Base obj : objects) {
            System.out.println("Объект: " + obj.getClass().getSimpleName());
            
            if (obj instanceof DoubleDerived) {
                DoubleDerived dd = (DoubleDerived) obj;
                dd.doubleDerivedMethod();
            } else if (obj instanceof Derived) {
                Derived d = (Derived) obj;
                d.derivedMethod();
            } else {
                System.out.println("  Базовый объект Base");
            }
        }
    }
}
