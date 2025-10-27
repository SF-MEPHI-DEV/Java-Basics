package ru.mephi.bonus.StreamApi.Tasks;

import java.util.*;
import java.util.stream.*;

public class Task4Solved {

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

        students.stream()
                .filter(student -> student.getAverageGrade() > 4.0)
                .sorted(Comparator.comparingDouble(Student::getAverageGrade).reversed())
                .forEach(student ->
                        System.out.println(student.getName() + ": " +
                                String.format("%.1f", student.getAverageGrade()))
                );
    }
}
