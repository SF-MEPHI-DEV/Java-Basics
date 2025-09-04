package ru.mephi.week1.lesson2.Classes.DtoExamples;

import java.time.LocalDateTime;

// DTO для отслеживания активности пользователя
public class UserActivityDTO {
    // final поля - неизменяемые данные
    public final long userId;
    public final String activityType;
    public final LocalDateTime timestamp;
    public final String details;
    public final String ipAddress;
    
    // конструктор с основными полями
    public UserActivityDTO(long userId, String activityType) {
        this.userId = userId;
        this.activityType = activityType;
        this.timestamp = LocalDateTime.now();
        this.details = "";
        this.ipAddress = "localhost";
    }
    
    // полный конструктор
    public UserActivityDTO(long userId, String activityType, String details, String ipAddress) {
        this.userId = userId;
        this.activityType = activityType;
        this.details = details;
        this.ipAddress = ipAddress;
        this.timestamp = LocalDateTime.now();
    }
    
    // получить описание активности
    public String getDescription() {
        return "Пользователь " + userId + " выполнил: " + activityType;
    }
    
    // проверить, является ли активность подозрительной
    public boolean isSuspicious() {
        return activityType.contains("admin") || 
               activityType.contains("delete") ||
               activityType.contains("hack");
    }
    
    // получить форматированное время
    public String getFormattedTime() {
        return timestamp.toString().replace("T", " в ");
    }
    
    @Override
    public String toString() {
        return "UserActivity{" +
               "userId=" + userId +
               ", activity='" + activityType + "'" +
               ", time='" + getFormattedTime() + "'" +
               ", from='" + ipAddress + "'" +
               "}";
    }
}
