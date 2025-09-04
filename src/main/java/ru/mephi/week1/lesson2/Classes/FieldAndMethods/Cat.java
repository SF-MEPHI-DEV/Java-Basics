package ru.mephi.week1.lesson2.Classes.FieldAndMethods;

public class Cat {
    // поля экземпляра - у каждого объекта свои значения
    private String name;
    private int age;
    private String breed = "обычная"; // значение по умолчанию
    
    // статические поля - общие для всех объектов класса
    public static int count = 0; // счетчик котов
    public static final String SPECIES = "Felis catus"; // константа
    private static String shelter = "Приют Доброта"; // общий приют
    
    // final поле экземпляра - уникально для каждого кота, но неизменно
    private final long id;
    
    // статический блок для инициализации статических полей
    static {
        System.out.println("Класс Cat загружен. Вид: " + SPECIES);
    }
    
    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = System.currentTimeMillis() + count; // уникальный ID
        count++; // увеличиваем общий счетчик
    }
    
    public Cat(String name, int age, String breed) {
        this(name, age); // вызов основного конструктора
        this.breed = breed;
    }
    
    // методы для работы с полями экземпляра
    public void sayHello() {
        System.out.println("Меня зовут " + name + ", мне " + age + " лет, порода: " + breed);
        System.out.println("Мой ID: " + id + ", я живу в приюте: " + shelter);
    }
    
    public void setBreed(String breed) {
        this.breed = breed; // можем изменить породу
    }
    
    // статические методы - работают только со статическими полями
    public static void printCount() {
        System.out.println("Всего котов создано: " + count);
        System.out.println("Все коты из приюта: " + shelter);
    }
    
    public static void changeShelter(String newShelter) {
        shelter = newShelter; // изменяем для всех котов
    }
    
    public static String getSpecies() {
        return SPECIES; // константу нельзя изменить
    }
    
    // геттеры для приватных полей
    public String getName() {
        return name;
    }
    
    public long getId() {
        return id; // final поле можно только читать
    }
}
