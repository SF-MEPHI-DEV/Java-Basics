package ru.mephi.week1.lesson2.Classes.ZooExample;

public abstract class Spider extends Animal {
    public Spider(String name,int age) {
        super(name,age);
    }

    @Override
    public String getName() {
        return "Spider with name " + name;
    }
}