package ru.mephi.week2.lesson1;

public class SwitchExample {
    public static void main(String[] args) {
        
        // Классический switch
        int day = -1;
        switch (day) {
            case 1:
                System.out.println("Понедельник");
                break;
            case 2:
                System.out.println("Вторник");
                break;
            case 3:
                System.out.println("Среда");
                break;
            case 4:
                System.out.println("Четверг");
                break;
            case 5:
                System.out.println("Пятница");
                break;
            case 6:
                System.out.println("Суббота");
                break;
            case 7:
                System.out.println("Воскресенье");
                break;
            default:
                System.out.println("Ошибка");
        }
        
        // Switch с символами
        char grade = 'B';
        switch (grade) {
            case 'A':
                System.out.println("Отлично!");
                break;
            case 'B':
                System.out.println("Хорошо!");
                break;
            case 'C':
                System.out.println("Удовлетворительно");
                break;
            default:
                System.out.println("Плохо");
        }
        
        // Switch со строками
        String color = "red";
        String colorTest = "blue";
        switch (color) {
            case "red":
                System.out.println("Красный - стоп!");
                break;
            case "yellow":
                System.out.println("Желтый - внимание!");
                break;
            case "green":
                System.out.println("Зеленый - можно идти!");
                break;
            default:
                System.out.println("Неизвестный цвет");
        }
        
        // Switch без break (fall-through)
        int month = 12;
        switch (month) {
            case 12:
            case 1:
            case 2:
                System.out.println("Зима");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("Весна");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("Лето");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("Осень");
                break;
        }
        
        // Современный switch expression (Java 14+)
        String result = switch (day) {
            case 1, 2, 3, 4, 5 -> "Рабочий день";
            case 6, 7 -> "Выходной";
            default -> "Неизвестный день";
        };
        System.out.println(result);
    }
}