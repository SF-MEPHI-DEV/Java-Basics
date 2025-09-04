package ru.mephi.week1.lesson2.Classes.ZooExample;

public class Spider extends Animal {
    public Spider(String name) {
        super(name);
    }

    @Override
    public String getName() {
        return "Spider with name " + name;
    }
}