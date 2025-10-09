package ru.mephi.week7.lesson1.Examples;

import java.sql.Date;

public class NotFoundException extends Exception {
    String name; // Например, имя сущности, что мы не смогли найти
    Integer id; // Её id в базе данных
}

