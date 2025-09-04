package ru.mephi.week1.lesson2.Classes.Inheritance;

/**
 * Наследник Base - демонстрирует что наследуется и что доступно
 */
public class Derived extends Base {
    // собственные поля наследника
    public String derivedPublicField = "Derived PUBLIC поле";
    private String derivedPrivateField = "Derived PRIVATE поле";
    
    public Derived() {
        super(); // вызов конструктора родителя (можно опустить - будет вызван автоматически)
        System.out.println("Создан объект Derived");
    }
    
    // переопределение метода родителя
    @Override
    public void basePublicMethod() {
        System.out.println("Derived: basePublicMethod ПЕРЕОПРЕДЕЛЕН!");
        
        // можем вызвать метод родителя через super
        System.out.println("Вызываем родительский метод:");
        super.basePublicMethod();
    }
    
    // переопределение protected метода
    @Override
    protected void protectedMethod() {
        System.out.println("Derived: ПЕРЕОПРЕДЕЛЕН protected метод");
        super.protectedMethod(); // вызов родительского метода
    }
    
    // собственный метод наследника
    public void derivedMethod() {
        System.out.println("Derived: собственный метод наследника");
    }
    
    // демонстрация доступности полей родителя
    public void demonstrateAccess() {
        System.out.println("\n=== Доступность полей родителя в Derived ===");
        
        // PUBLIC поле - доступно
        System.out.println("✓ basePublicField: " + basePublicField);
        
        // PROTECTED поле - доступно (мы наследники!)
        System.out.println("✓ baseProtectedField: " + baseProtectedField);
        
        // DEFAULT поле - доступно (мы в том же пакете!)
        System.out.println("✓ baseDefaultField: " + baseDefaultField);
        
        // PRIVATE поле - НЕ доступно напрямую! Только через геттер
        // System.out.println("✗ basePrivateField: " + basePrivateField); // ОШИБКА!
        System.out.println("✓ basePrivateField (через геттер): " + getPrivateField());
        
        // FINAL поле - доступно для чтения, но нельзя изменить
        System.out.println("✓ FINAL_FIELD: " + FINAL_FIELD);
        // FINAL_FIELD = "новое значение"; // ОШИБКА!
        
        // STATIC поле - доступно
        System.out.println("✓ instanceCount: " + instanceCount);
    }
    
    // демонстрация доступности методов родителя
    public void demonstrateMethodAccess() {
        System.out.println("\n=== Доступность методов родителя в Derived ===");
        
        // PUBLIC методы - доступны
        System.out.println("✓ Вызов publicMethod():");
        publicMethod();
        
        // PROTECTED методы - доступны (мы наследники!)
        System.out.println("✓ Вызов protectedMethod():");
        protectedMethod();
        
        // DEFAULT методы - доступны (мы в том же пакете!)
        System.out.println("✓ Вызов defaultMethod():");
        defaultMethod();
        
        // PRIVATE методы - НЕ доступны!
        // privateMethod(); // ОШИБКА!
        
        // FINAL методы - доступны, но нельзя переопределить
        System.out.println("✓ Вызов finalMethod():");
        finalMethod();
        
        // STATIC методы - доступны
        System.out.println("✓ Вызов publicStaticMethod():");
        Base.publicStaticMethod(); // лучше через имя класса
        publicStaticMethod();       // но можно и так
    }
    
    // попытка переопределить final метод - ОШИБКА!
    // @Override
    // public final void finalMethod() { // ОШИБКА КОМПИЛЯЦИИ!
    //     System.out.println("Нельзя переопределить final метод!");
    // }
    
    // можем добавить свой статический метод
    public static void derivedStaticMethod() {
        System.out.println("Derived: собственный static метод");
    }
}
