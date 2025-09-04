package ru.mephi.week1.lesson2.Classes.Modifiers;

public class ModifiersExample {
    // public - доступен везде
    public String publicField = "Доступен всем";
    
    // private - доступен только внутри этого класса
    private String privateField = "Только внутри класса";
    
    // protected - доступен в пакете и наследникам
    protected String protectedField = "В пакете и наследникам";
    
    // default (package-private) - доступен только в пакете
    String defaultField = "Только в пакете";
    
    // static - принадлежит классу, не экземпляру
    public static int staticCounter = 0;
    
    // final - константа, нельзя изменить
    public final String FINAL_NAME = "Константа";
    
    // static final - константа класса
    public static final double PI = 3.14159;
    
    public ModifiersExample() {
        staticCounter++;
        // внутри класса доступны все поля, включая private
        this.privateField = "Инициализирован в конструкторе";
        System.out.println("Создан объект #" + staticCounter);
    }
    
    // public метод - доступен везде
    public void publicMethod() {
        System.out.println("Public метод вызван");
        // можем обращаться ко всем полям внутри класса
        System.out.println("Private поле: " + privateField);
    }
    
    // private метод - только внутри класса
    private void privateMethod() {
        System.out.println("Private метод - только внутри класса");
    }
    
    // protected метод - в пакете и наследникам
    protected void protectedMethod() {
        System.out.println("Protected метод");
        privateMethod(); // можем вызывать private методы
    }
    
    // default метод - только в пакете
    void defaultMethod() {
        System.out.println("Default (package) метод");
    }
    
    // static метод - принадлежит классу
    public static void staticMethod() {
        System.out.println("Static метод, счетчик: " + staticCounter);
        // в static методах нельзя обращаться к instance полям напрямую
        // System.out.println(publicField); // ошибка!
    }
    
    // final метод - нельзя переопределить в наследниках
    public final void finalMethod() {
        System.out.println("Final метод - нельзя переопределить");
    }
    
    // геттер для private поля
    public String getPrivateField() {
        return privateField;
    }
    
    // сеттер для private поля
    public void setPrivateField(String value) {
        this.privateField = value;
    }
}
