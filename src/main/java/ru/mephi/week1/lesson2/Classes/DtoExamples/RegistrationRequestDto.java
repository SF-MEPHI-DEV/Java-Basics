package ru.mephi.week1.lesson2.Classes.DtoExamples;

// DTO для запроса регистрации пользователя - неизменяемый объект
public class RegistrationRequestDto {
    // final поля - данные нельзя изменить после создания
    public final String firstName;
    public final String lastName;
    public final String email;
    public final String password;
    public final int age;
    public final boolean agreeToTerms;
    
    // конструктор с основными полями
    public RegistrationRequestDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = 0; // значение по умолчанию
        this.agreeToTerms = false;
    }
    
    // полный конструктор
    public RegistrationRequestDto(String firstName, String lastName, String email, 
                                String password, int age, boolean agreeToTerms) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.agreeToTerms = agreeToTerms;
    }
    
    // метод для получения полного имени
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    // проверка корректности данных
    public boolean isValid() {
        return firstName != null && !firstName.isEmpty() &&
               lastName != null && !lastName.isEmpty() &&
               email != null && email.contains("@") &&
               password != null && password.length() >= 6 &&
               age >= 0 && agreeToTerms;
    }
    
    // переопределение toString для удобного отображения (без пароля!)
    @Override
    public String toString() {
        return "RegistrationRequest{" +
               "fullName='" + getFullName() + "', " +
               "email='" + email + "', " +
               "age=" + age + 
               "}";
    }
}
