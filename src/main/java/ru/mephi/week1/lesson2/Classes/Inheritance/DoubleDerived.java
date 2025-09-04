package ru.mephi.week1.lesson2.Classes.Inheritance;

/**
 * Наследник Derived - демонстрирует многоуровневое наследование
 * DoubleDerived -> Derived -> Base
 */
public class DoubleDerived extends Derived {
    // собственное поле
    private String doubleDerivedField = "DoubleDerived поле";
    
    public DoubleDerived() {
        super(); // вызов конструктора Derived, который вызывает Base
        System.out.println("Создан объект DoubleDerived");
    }
    
    // снова переопределяем метод (уже переопределенный в Derived)
    @Override
    public void basePublicMethod() {
        System.out.println("DoubleDerived: basePublicMethod ПЕРЕОПРЕДЕЛЕН ВТОРОЙ РАЗ!");
        
        // можем вызвать метод непосредственного родителя (Derived)
        System.out.println("Вызываем метод Derived:");
        super.basePublicMethod(); // это вызовет версию из Derived
    }
    
    // переопределение метода Derived
    @Override
    public void derivedMethod() {
        System.out.println("DoubleDerived: переопределил метод из Derived");
        super.derivedMethod(); // вызов версии из Derived
    }
    
    // собственный метод
    public void doubleDerivedMethod() {
        System.out.println("DoubleDerived: собственный метод");
    }
    
    // демонстрация доступа к полям всех уровней наследования
    public void demonstrateMultiLevelAccess() {
        System.out.println("\n=== Многоуровневое наследование: доступ к полям ===");
        
        // Поля от Base (через Derived)
        System.out.println("✓ basePublicField: " + basePublicField);
        System.out.println("✓ baseProtectedField: " + baseProtectedField);
        System.out.println("✓ baseDefaultField: " + baseDefaultField);
        // basePrivateField недоступно напрямую
        
        // Поля от Derived
        System.out.println("✓ derivedPublicField: " + derivedPublicField);
        // derivedPrivateField недоступно (private в Derived)
        
        // Собственное поле
        System.out.println("✓ doubleDerivedField: " + doubleDerivedField);
        
        // Static поле доступно на всех уровнях
        System.out.println("✓ instanceCount: " + instanceCount);
    }
    
    // демонстрация instanceof для многоуровневого наследования
    public void demonstrateInstanceOf() {
        System.out.println("\n=== instanceof проверки ===");
        System.out.println("DoubleDerived instanceof DoubleDerived: " + (this instanceof DoubleDerived));
        System.out.println("DoubleDerived instanceof Derived: " + (this instanceof Derived));
        System.out.println("DoubleDerived instanceof Base: " + (this instanceof Base));
        System.out.println("DoubleDerived instanceof Object: " + (this instanceof Object));
    }
}
