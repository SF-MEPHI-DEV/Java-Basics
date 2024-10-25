package ru.mephi.week8.lesson2.task2Solved;

public class Main {

    /**
     * <h2>Задание: Телефонный справочник</h2>
     * <br>
     * <h2>Описание: </h2>
     * <p>Реализуйте телефонный справочник, который позволяет:</p>
     * <ol>
     * 	<li>Добавлять новый контакт с именем и номером телефона.</li>
     * 	<li>Обновлять номер телефона для существующего контакта.</li>
     * 	<li>Получать номер телефона по имени.</li>
     * 	<li>Удалять контакт по имени.</li>
     * 	<li>Выводить все контакты в алфавитном порядке.</li>
     * </ol>
     * <p>Справочник должен поддерживать возможность хранения нескольких номеров для одного имени
     * (те, один человек может иметь несколько телефонов).</p>
     */

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addContact("Alice", "123-456");
        phoneBook.addContact("Bob", "234-567");
        phoneBook.addContact("Alice", "345-678");

        phoneBook.getPhones("Alice");

        phoneBook.deleteContact("Bob");

        phoneBook.printAllContacts();
    }

}
