package ru.mephi.week8.lesson2.task2Solved;

import java.util.*;

public class PhoneBook {

    private Map<String, List<String>> contacts;

    public PhoneBook() {
        contacts = new TreeMap<>();
    }

    public void addContact(String name, String phone) {
        contacts.putIfAbsent(name, new ArrayList<>());
        contacts.get(name).add(phone);
        System.out.println("Контакт " + name + " с номером " + phone + " добавлен.");
    }

    public List<String> getPhones(String name) {
        if (contacts.containsKey(name)) {
            System.out.println("Номера для " + name + ": " + contacts.get(name));
            return contacts.get(name);
        } else {
            System.out.println("Контакт " + name + " не найден.");
            return Collections.emptyList();
        }
    }

    public void deleteContact(String name) {
        if (contacts.remove(name) != null) {
            System.out.println("Контакт " + name + " удален.");
        } else {
            System.out.println("Контакт " + name + " не найден.");
        }
    }

    public void printAllContacts() {
        System.out.println("Все контакты:");
        for (Map.Entry<String, List<String>> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
