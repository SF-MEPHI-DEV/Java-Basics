package ru.mephi.week1.lesson2.Classes.Inheritance;

/**
 * Базовый класс для демонстрации модификаторов доступа при наследовании
 * 
 * Модификаторы доступа в Java:
 * - public: доступен везде
 * - protected: доступен в пакете и наследникам  
 * - default (package-private): доступен только в пакете
 * - private: доступен только в данном классе
 */
public class Base {
    // === ПОЛЯ С РАЗНЫМИ МОДИФИКАТОРАМИ ===
    public String basePublicField = "PUBLIC поле";           // доступно везде
    protected String baseProtectedField = "PROTECTED поле";  // доступно в пакете и наследникам
    String baseDefaultField = "DEFAULT поле";                // доступно только в пакете
    private String basePrivateField = "PRIVATE поле";        // доступно только в Base
    
    // final поле - нельзя изменить
    public final String FINAL_FIELD = "Неизменяемое поле";
    
    // static поле - принадлежит классу
    public static int instanceCount = 0;
    
    // конструктор
    public Base() {
        instanceCount++;
        System.out.println("Создан объект Base #" + instanceCount);
        // в конструкторе доступны все поля, включая private
        this.basePrivateField = "Инициализировано в Base";
    }
    
    // === СТАТИЧЕСКИЕ МЕТОДЫ ===
    public static void publicStaticMethod() {
        System.out.println("Base: PUBLIC static метод - доступен везде");
    }
    
    protected static void protectedStaticMethod() {
        System.out.println("Base: PROTECTED static метод - доступен в пакете и наследникам");
    }
    
    static void defaultStaticMethod() {
        System.out.println("Base: DEFAULT static метод - доступен только в пакете");
    }
    
    private static void privateStaticMethod() {
        System.out.println("Base: PRIVATE static метод - доступен только в Base");
    }
    
    // === INSTANCE МЕТОДЫ ===
    public void publicMethod() {
        System.out.println("Base: PUBLIC метод - может вызвать любой");
        // внутри класса можем вызывать любые методы, даже private
        this.privateMethod();
    }
    
    protected void protectedMethod() {
        System.out.println("Base: PROTECTED метод - доступен в пакете и наследникам");
    }
    
    void defaultMethod() {
        System.out.println("Base: DEFAULT метод - доступен только в пакете");
    }
    
    private void privateMethod() {
        System.out.println("Base: PRIVATE метод - доступен только в Base");
    }
    
    // метод для переопределения в наследниках
    public void basePublicMethod() {
        System.out.println("Base: basePublicMethod - может быть переопределен");
        System.out.println("Доступ к private полю: " + basePrivateField);
    }
    
    // final метод - нельзя переопределить в наследниках
    public final void finalMethod() {
        System.out.println("Base: FINAL метод - нельзя переопределить");
    }
    
    // метод для демонстрации доступности полей
    public void showAllFields() {
        System.out.println("=== Все поля из Base ===");
        System.out.println("Public: " + basePublicField);
        System.out.println("Protected: " + baseProtectedField); 
        System.out.println("Default: " + baseDefaultField);
        System.out.println("Private: " + basePrivateField); // доступно только здесь
    }
    
    // геттер для private поля - единственный способ получить его извне
    public String getPrivateField() {
        return basePrivateField;
    }
}
