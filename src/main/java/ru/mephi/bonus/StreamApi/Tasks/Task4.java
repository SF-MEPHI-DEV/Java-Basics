package ru.mephi.bonus.StreamApi.Tasks;

import java.util.*;
import java.util.stream.*;

public class Task4 {

    /**
     * <h2>Задача: Статистика по студентам</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Дан список студентов с их оценками по разным предметам. Необходимо вычислить средний балл
     * каждого студента, отобрать студентов со средним баллом выше 4.0 и вывести их в порядке
     * убывания среднего балла.</p>
     * <br>
     * <h2>Требования:</h2>
     * <ul>
     *     <li>Для каждого студента вычислить средний балл по всем оценкам</li>
     *     <li>Отфильтровать студентов со средним баллом выше 4.0</li>
     *     <li>Отсортировать студентов по убыванию среднего балла</li>
     *     <li>Вывести имя студента и его средний балл (с округлением до 1 знака)</li>
     * </ul>
     * <br>
     * <h2>Пример входных данных:</h2>
     * <pre>
     * Student("Анна", [5, 5, 4, 5, 5])
     * Student("Борис", [3, 4, 3, 4, 3])
     * Student("Дмитрий", [5, 4, 5, 4, 5])
     * Student("Елена", [4, 4, 5, 4, 4])
     * Student("Виктор", [3, 3, 2, 3, 3])
     * </pre>
     * <br>
     * <h2>Ожидаемый результат:</h2>
     * <pre>
     * Анна: 4.8
     * Дмитрий: 4.6
     * Елена: 4.2
     * </pre>
     */

    static class Student {
        String name;
        List<Integer> grades;

        public Student(String name, List<Integer> grades) {
            this.name = name;
            this.grades = grades;
        }

        public String getName() {
            return name;
        }

        public List<Integer> getGrades() {
            return grades;
        }

        public double getAverageGrade() {
            return grades.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);
        }
    }

    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student("Анна", Arrays.asList(5, 5, 4, 5, 5)),
                new Student("Борис", Arrays.asList(3, 4, 3, 4, 3)),
                new Student("Дмитрий", Arrays.asList(5, 4, 5, 4, 5)),
                new Student("Елена", Arrays.asList(4, 4, 5, 4, 4)),
                new Student("Виктор", Arrays.asList(3, 3, 2, 3, 3))
        );

    }
}
