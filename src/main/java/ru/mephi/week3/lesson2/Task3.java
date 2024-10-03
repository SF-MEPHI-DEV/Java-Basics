package ru.mephi.week3.lesson2;

import java.util.Scanner;

public class Task3 {

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
        String userName = scanner.nextLine();
        System.out.println("Is name correct: " + validName(userName));
        String userEmail = scanner.nextLine();
        System.out.println("Is name correct: " + validEmail(userEmail));

    }

    public static boolean validName(String name) {
        return name.matches("[A-Z][a-z]*");
    }

    public static boolean validEmail(String email) {
        // glebpavlyuk@gamil.com
        return email.matches("[\\w._]+@[\\w._]+\\.[a-zA-Z]{2,6}");
    }

    public static boolean validPassword(String password) {
        return password.length() > 8
                && password.matches(".*\\d.*")
                && password.matches(".*[A-Z].*")
                && password.matches(".*[!@#$%^&*()_+].*");
    }

}
