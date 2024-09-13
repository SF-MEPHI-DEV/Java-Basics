package ru.mephi.week3.lesson2;

import java.util.Scanner;

public class Task3Solved {

    /**
     * <H2>Задача: Валидация данных пользователя при регистрации</H2>
     * <br>
     * <H2>Описание:</H2>
     *
     * <p>Создайте консольное приложение для регистрации пользователя. Программа запрашивает у пользователя его имя, электронную почту и пароль, а затем проверяет правильность введённых данных.</p>
     * <br>
     * <H2>Требования:</H2>
     *
     * <ul>
     * 	<li>Имя должно начинаться с заглавной буквы.</li>
     * 	<li>Электронная почта должна содержать символ “@” и домен.</li>
     * 	<li>Пароль должен быть не менее 8 символов, содержать хотя бы одну цифру, одну заглавную букву и один специальный символ (например, @, #, $).</li>
     *</ul>
     * <br>
     * <p>Если данные не соответствуют требованиям, программа сообщает пользователю, что необходимо исправить.</p>
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        if (!validateName(name)) {
            System.out.println("Name must start with a capital letter and contain only letters.");
        } else {
            System.out.println("Name is valid.");
        }

        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        if (!validateEmail(email)) {
            System.out.println("Invalid email format. It must contain '@' and a valid domain.");
        } else {
            System.out.println("Email is valid.");
        }

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (!validatePassword(password)) {
            System.out.println("Password must be at least 8 characters long, contain one digit, one uppercase letter, and one special character.");
        } else {
            System.out.println("Password is valid.");
        }
    }

    public static boolean validateName(String name) {
        return name.matches("[A-Z][a-zA-Z]*");
    }

    public static boolean validateEmail(String email) {
        return email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean validatePassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*\\d.*") &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[!@#$%^&*()_+\\-=].*");
    }

}
