package ru.mephi.week3.lesson2.Examples.StringExamples;

import java.util.regex.Pattern;

public class ValidationRegexExample {

    public static void main(String[] args) {
        System.out.println("=== ПРОВЕРКА ДАННЫХ РЕГУЛЯРНЫМИ ВЫРАЖЕНИЯМИ ===\n");

        validatePhones();
        validateEmails();
        validateDates();
        validateOtherFormats();
    }

    private static void validatePhones() {
        System.out.println("1. Проверка телефонов:");

        // паттерны для разных форматов
        String phonePattern1 = "\\+7\\d{10}";                    // +71234567890
        String phonePattern2 = "8\\d{10}";                      // 81234567890
        String phonePattern3 = "\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}";  // +7 (123) 456-78-90

        String[] phones = {
            "+71234567890",
            "81234567890",
            "+7 (123) 456-78-90",
            "123-45-67",
            "+79991234567",
            "8-999-123-45-67"
        };

        for (String phone : phones) {
            boolean valid1 = phone.matches(phonePattern1);
            boolean valid2 = phone.matches(phonePattern2);
            boolean valid3 = phone.matches(phonePattern3);

            System.out.printf("%-20s -> +7xxxxxxxxxx: %s, 8xxxxxxxxxx: %s, +7 (xxx) xxx-xx-xx: %s%n",
                    phone, valid1, valid2, valid3);
        }
        System.out.println();
    }

    private static void validateEmails() {
        System.out.println("2. Проверка email:");

        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";

        String[] emails = {
            "user@example.com",
            "test.email@domain.org",
            "user123@test-site.ru",
            "invalid.email",
            "user@",
            "@domain.com",
            "user@domain",
            "a@b.co"
        };

        for (String email : emails) {
            boolean valid = email.matches(emailPattern);
            System.out.printf("%-25s -> %s%n", email, valid ? "✓" : "✗");
        }
        System.out.println();
    }

    private static void validateDates() {
        System.out.println("3. Проверка дат:");

        String datePattern1 = "\\d{2}\\.\\d{2}\\.\\d{4}";      // dd.mm.yyyy
        String datePattern2 = "\\d{4}-\\d{2}-\\d{2}";         // yyyy-mm-dd
        String datePattern3 = "\\d{2}/\\d{2}/\\d{4}";         // mm/dd/yyyy

        String[] dates = {
            "25.12.2023",
            "2023-12-25",
            "12/25/2023",
            "25-12-2023",
            "2023.12.25",
            "31.02.2023",
            "abc.def.ghij"
        };

        for (String date : dates) {
            boolean valid1 = date.matches(datePattern1);
            boolean valid2 = date.matches(datePattern2);
            boolean valid3 = date.matches(datePattern3);

            System.out.printf("%-15s -> dd.mm.yyyy: %s, yyyy-mm-dd: %s, mm/dd/yyyy: %s%n",
                    date, valid1, valid2, valid3);
        }
        System.out.println();
    }

    private static void validateOtherFormats() {
        System.out.println("4. Другие форматы:");

        // ИНН (10 или 12 цифр)
        String innPattern = "\\d{10}|\\d{12}";

        // Почтовый индекс России (6 цифр)
        String postalCodePattern = "\\d{6}";

        // IP адрес (упрощенный)
        String ipPattern = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

        String[] testData = {
            "1234567890",      // ИНН 10
            "123456789012",    // ИНН 12
            "123456",          // индекс
            "192.168.1.1",     // IP
            "999.999.999.999", // неверный IP
            "12345"            // короткий номер
        };

        for (String data : testData) {
            boolean innValid = data.matches(innPattern);
            boolean postalValid = data.matches(postalCodePattern);
            boolean ipValid = data.matches(ipPattern);

            System.out.printf("%-15s -> ИНН: %s, Индекс: %s, IP: %s%n",
                    data, innValid, postalValid, ipValid);
        }
        System.out.println();

        // пример с компилированными паттернами
        System.out.println("5. Компилированные паттерны:");
        Pattern phonePattern = Pattern.compile("\\+7\\d{10}");

        String[] phones = {"+71234567890", "81234567890", "+79991234567"};

        for (String phone : phones) {
            boolean valid = phonePattern.matcher(phone).matches();
            System.out.printf("%-15s -> %s%n", phone, valid ? "валидный" : "невалидный");
        }
    }
}