package ru.mephi.week1.lesson2.Classes.Modifiers;

class SamePackageClass extends ModifiersExample {
    public void testAccess() {
        System.out.println("\n=== Доступ из класса в том же пакете ===");
        
        // public - доступен
        System.out.println("Public поле: " + publicField);
        
        // protected - доступен (мы наследник)
        System.out.println("Protected поле: " + protectedField);
        
        // default - доступен (тот же пакет)
        System.out.println("Default поле: " + defaultField);
        
        // private - НЕ доступен напрямую
        // System.out.println(privateField); // ошибка компиляции
        System.out.println("Private поле через геттер: " + getPrivateField());
        
        // методы
        publicMethod();      // доступен
        protectedMethod();   // доступен (наследник)
        defaultMethod();     // доступен (тот же пакет)
        // privateMethod();  // НЕ доступен
    }
}

public class AccessDemo {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация модификаторов доступа ===");
        
        // создание объектов
        ModifiersExample obj1 = new ModifiersExample();
        ModifiersExample obj2 = new ModifiersExample();
        
        System.out.println("\n=== Доступ к полям и методам ===");
        
        // public поле - доступно
        obj1.publicField = "Изменено снаружи";
        System.out.println("Public поле: " + obj1.publicField);
        
        // protected и default доступны в том же пакете
        obj1.protectedField = "Изменен protected";
        obj1.defaultField = "Изменен default";
        
        // private поле - доступ только через публичные методы
        obj1.setPrivateField("Новое значение через сеттер");
        System.out.println("Private поле: " + obj1.getPrivateField());
        
        // final поле - нельзя изменить
        // obj1.FINAL_NAME = "новое"; // ошибка компиляции
        System.out.println("Final поле: " + obj1.FINAL_NAME);
        
        // статические поля и методы
        System.out.println("\n=== Статические элементы ===");
        ModifiersExample.staticMethod();
        System.out.println("PI = " + ModifiersExample.PI);
        
        // демонстрация наследования
        SamePackageClass child = new SamePackageClass();
        child.testAccess();
        
        // final метод нельзя переопределить
        child.finalMethod();
    }
}