package ru.mephi.week1.lesson2.Classes.Inheritance;

import java.util.ArrayList;
import java.util.List;

/**
 * Демонстрация ковариации и контрвариации в Java
 * 
 * ВАЖНЫЕ ПОНЯТИЯ:
 * 
 * КОВАРИАЦИЯ (Covariance) - "сохраняет направление" наследования
 * - Если B наследует A, то Container<B> можно рассматривать как Container<A>
 * - В Java: List<? extends A> - ковариантный тип
 * - Позволяет ЧИТАТЬ, но НЕ позволяет ЗАПИСЫВАТЬ
 * 
 * КОНТРВАРИАЦИЯ (Contravariance) - "обращает направление" наследования  
 * - Если B наследует A, то Container<A> можно рассматривать как Container<B>
 * - В Java: List<? super B> - контрвариантный тип
 * - Позволяет ЗАПИСЫВАТЬ, но НЕ позволяет ЧИТАТЬ (кроме Object)
 * 
 * ИНВАРИАНТНОСТЬ (Invariance) - "не сохраняет отношения"
 * - List<A> и List<B> не связаны, даже если A и B связаны наследованием
 * - Обычные generic типы в Java инвариантны
 */
public class VarianceExample {
    
    // Простая иерархия для демонстрации
    static class Animal {
        String name;
        Animal(String name) { this.name = name; }
        public void makeSound() { System.out.println(name + " издает звук"); }
        @Override
        public String toString() { return "Animal: " + name; }
    }
    
    static class Dog extends Animal {
        Dog(String name) { super(name); }
        @Override
        public void makeSound() { System.out.println(name + " лает: Гав!"); }
        public void wagTail() { System.out.println(name + " виляет хвостом"); }
        @Override
        public String toString() { return "Dog: " + name; }
    }
    
    static class Cat extends Animal {
        Cat(String name) { super(name); }
        @Override
        public void makeSound() { System.out.println(name + " мяукает: Мяу!"); }
        public void purr() { System.out.println(name + " мурчит"); }
        @Override
        public String toString() { return "Cat: " + name; }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Демонстрация Ковариации и Контрвариации ===\n");
        
        demonstrateInvariance();
        demonstrateCovariance();
        demonstrateContravariance();
        demonstratePECS();
        demonstrateArrayCovariance();
    }
    
    /**
     * ИНВАРИАНТНОСТЬ - обычные generic типы
     */
    public static void demonstrateInvariance() {
        System.out.println("=== 1. ИНВАРИАНТНОСТЬ (Invariance) ===");
        System.out.println("Обычные generic типы инвариантны\n");
        
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Бобик"));
        dogs.add(new Dog("Шарик"));
        
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog("Рекс"));
        animals.add(new Cat("Мурзик"));
        
        // animals = dogs; // ОШИБКА! Нельзя присвоить List<Dog> к List<Animal>
        System.out.println("❌ List<Dog> НЕЛЬЗЯ присвоить к List<Animal>");
        System.out.println("   Это было бы небезопасно:");
        System.out.println("   animals.add(new Cat(\"Васька\")) - в список собак добавили кота!");
        
        // Но отдельные элементы присваивать можно (ковариация для типов)
        Animal animal = new Dog("Дружок"); // OK!
        System.out.println("✓ Dog можно присвоить к Animal: " + animal);
        
        System.out.println();
    }
    
    /**
     * КОВАРИАЦИЯ - ? extends Type
     * Позволяет читать, но не писать
     */
    public static void demonstrateCovariance() {
        System.out.println("=== 2. КОВАРИАЦИЯ (Covariance) ===");
        System.out.println("List<? extends Animal> - можно ЧИТАТЬ, нельзя ЗАПИСЫВАТЬ\n");
        
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Бобик"));
        dogs.add(new Dog("Шарик"));
        
        // Ковариантный тип - можем присвоить List<Dog> к List<? extends Animal>
        List<? extends Animal> covariantAnimals = dogs; // OK!
        System.out.println("✓ List<Dog> можно присвоить к List<? extends Animal>");
        
        // Можем ЧИТАТЬ - получаем Animal (или его подтипы)
        System.out.println("✓ Можем читать (получаем Animal):");
        for (Animal animal : covariantAnimals) {
            System.out.println("  Читаем: " + animal);
            animal.makeSound();
        }
        
        // НЕ можем ЗАПИСЫВАТЬ - компилятор не знает точный тип
        // covariantAnimals.add(new Dog("Новый")); // ОШИБКА!
        // covariantAnimals.add(new Cat("Новый")); // ОШИБКА!
        // covariantAnimals.add(new Animal("Новый")); // ОШИБКА!
        System.out.println("❌ НЕЛЬЗЯ добавлять элементы - компилятор не знает точный тип");
        System.out.println("   List<? extends Animal> может быть List<Dog>, List<Cat> или List<Animal>");
        
        System.out.println();
    }
    
    /**
     * КОНТРВАРИАЦИЯ - ? super Type  
     * Позволяет писать, но не читать (кроме Object)
     */
    public static void demonstrateContravariance() {
        System.out.println("=== 3. КОНТРВАРИАЦИЯ (Contravariance) ===");
        System.out.println("List<? super Dog> - можно ЗАПИСЫВАТЬ, нельзя ЧИТАТЬ\n");
        
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog("Рекс"));
        animals.add(new Cat("Мурзик"));
        
        // Контрвариантный тип - можем присвоить List<Animal> к List<? super Dog>
        List<? super Dog> contravariateAnimals = animals; // OK!
        System.out.println("✓ List<Animal> можно присвоить к List<? super Dog>");
        
        // Можем ЗАПИСЫВАТЬ - добавлять Dog и его подтипы
        contravariateAnimals.add(new Dog("Новый пес"));
        System.out.println("✓ Можем добавлять Dog:");
        System.out.println("  Добавили: Dog('Новый пес')");
        
        // НЕ можем добавлять родительские типы
        // contravariateAnimals.add(new Animal("Животное")); // ОШИБКА!
        // contravariateAnimals.add(new Cat("Кот")); // ОШИБКА!
        
        // Можем читать только как Object (не как Dog или Animal!)
        System.out.println("✓ Можем читать только как Object:");
        for (Object obj : contravariateAnimals) {
            System.out.println("  Читаем как Object: " + obj);
            // obj.makeSound(); // ОШИБКА! Object не имеет метода makeSound
        }
        
        System.out.println();
    }
    
    /**
     * Демонстрация принципа PECS (Producer Extends, Consumer Super)
     */
    public static void demonstratePECS() {
        System.out.println("=== 4. Принцип PECS ===");
        System.out.println("Producer Extends, Consumer Super\n");
        
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Собака1"));
        dogs.add(new Dog("Собака2"));
        
        List<Animal> animals = new ArrayList<>();
        
        // PRODUCER (источник данных) - используем extends
        System.out.println("✓ PRODUCER - используем ? extends (для чтения):");
        copyAnimals(dogs, animals); // dogs - producer (источник)
        
        // CONSUMER (потребитель данных) - используем super  
        System.out.println("✓ CONSUMER - используем ? super (для записи):");
        addMoreDogs(animals); // animals - consumer (получатель)
        
        System.out.println("Итоговый список животных:");
        for (Animal animal : animals) {
            System.out.println("  " + animal);
        }
        
        System.out.println();
    }
    
    // Метод копирования: source - producer (extends), destination - consumer (super)
    public static void copyAnimals(List<? extends Animal> source, List<? super Animal> destination) {
        for (Animal animal : source) {
            destination.add(animal);
        }
        System.out.println("  Скопировано " + source.size() + " животных");
    }
    
    // Метод добавления собак: list - consumer (super)
    public static void addMoreDogs(List<? super Dog> list) {
        list.add(new Dog("Дополнительная собака"));
        System.out.println("  Добавлена еще одна собака");
    }
    
    /**
     * Массивы в Java ковариантны (в отличие от List!)
     */
    public static void demonstrateArrayCovariance() {
        System.out.println("=== 5. Ковариация массивов ===");
        System.out.println("Массивы в Java ковариантны (но это опасно!)\n");
        
        Dog[] dogs = {new Dog("Собака1"), new Dog("Собака2")};
        
        // Массивы ковариантны - можем присвоить Dog[] к Animal[]
        Animal[] animals = dogs; // OK! Но опасно
        System.out.println("✓ Dog[] можно присвоить к Animal[]");
        
        // Можем читать
        System.out.println("✓ Можем читать:");
        for (Animal animal : animals) {
            System.out.println("  " + animal);
        }
        
        // Можем писать, но получим runtime ошибку!
        try {
            animals[0] = new Cat("Кот"); // Компилируется, но падает в runtime!
        } catch (ArrayStoreException e) {
            System.out.println("❌ ArrayStoreException: нельзя добавить Cat в массив Dog[]");
            System.out.println("   Ошибка обнаруживается только во время выполнения!");
        }
        
        System.out.println("\n💡 Поэтому generic типы сделали инвариантными - безопаснее!");
        System.out.println("   List<Dog> нельзя присвоить к List<Animal>, избегая runtime ошибок");
    }
}