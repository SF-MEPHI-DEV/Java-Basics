package ru.mephi.week1.lesson2.Classes.DtoExamples;

/*
 * DTO (Data Transfer Object) - это простые классы для передачи данных.
 * 
 * ВАЖНО: Логика в этих DTO примерах приближена к реальным бизнес-задачам:
 * - RegistrationRequestDto - как в реальных системах регистрации
 * - UserActivityDTO - как в системах мониторинга активности пользователей  
 * - OrderDto - как в интернет-магазинах
 * - ProductDto - как в системах управления товарами
 * 
 * Эти примеры показывают как DTO используются в реальной разработке
 * для передачи данных между слоями приложения (контроллер -> сервис -> база данных).
 */
public class DtoDemo {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация DTO классов ===");
        System.out.println("DTO = Data Transfer Object - объекты для передачи данных");
        System.out.println("Эти примеры основаны на реальной бизнес-логике\n");
        
        // создание DTO для регистрации
        System.out.println("\n--- Регистрация пользователя ---");
        RegistrationRequestDto registration1 = new RegistrationRequestDto(
            "Иван", "Петров", "ivan@email.com", "password123"
        );
        
        RegistrationRequestDto registration2 = new RegistrationRequestDto(
            "Мария", "Сидорова", "maria@email.com", "strongpass", 25, true
        );
        
        System.out.println("Запрос 1: " + registration1);
        System.out.println("Валидность: " + registration1.isValid());
        
        System.out.println("Запрос 2: " + registration2);
        System.out.println("Валидность: " + registration2.isValid());
        System.out.println("Полное имя: " + registration2.getFullName());
        
        // создание DTO активности пользователя
        System.out.println("\n--- Активность пользователей ---");
        UserActivityDTO activity1 = new UserActivityDTO(1001, "login");
        UserActivityDTO activity2 = new UserActivityDTO(1002, "admin_delete", 
                                                        "Удален файл config.txt", "192.168.1.10");
        
        System.out.println(activity1);
        System.out.println("Подозрительная: " + activity1.isSuspicious());
        
        System.out.println(activity2);
        System.out.println("Подозрительная: " + activity2.isSuspicious());
        System.out.println("Описание: " + activity2.getDescription());
        
        // создание DTO товаров
        System.out.println("\n--- Каталог товаров ---");
        ProductDto laptop = new ProductDto("Ноутбук Dell", "electronics", 75000, 5);
        ProductDto book = new ProductDto("Java для начинающих", "books", 1200, 0);
        
        System.out.println(laptop);
        System.out.println("В наличии: " + laptop.isInStock());
        System.out.println("Дорогой: " + laptop.isExpensive());
        System.out.println("Цена со скидкой 10%: " + laptop.getDiscountedPrice(10));
        
        System.out.println(book);
        System.out.println("В наличии: " + book.isInStock());
        System.out.println("Популярная категория: " + book.isPopularCategory());
        
        System.out.println("Всего товаров создано: " + ProductDto.getTotalProductsCount());
        
        // создание DTO заказов
        System.out.println("\n--- Заказы ---");
        OrderDto order1 = new OrderDto(1001, 76200, "ул. Ленина, 15, кв. 23");
        
        System.out.println(order1);
        System.out.println("Дорогой заказ: " + order1.isExpensive());
        System.out.println("Можно отменить: " + order1.canBeCancelled());
        System.out.println("Сумма: " + order1.getFormattedAmount());
        
        System.out.println("\n=== О DTO классах ===");
        System.out.println("DTO классы в реальных проектах:");
        System.out.println("1. Передают данные между API и сервисами");
        System.out.println("2. Содержат простую бизнес-логику (валидация, форматирование)");  
        System.out.println("3. Часто используют final поля для неизменности");
        System.out.println("4. Имеют методы toString() для удобной отладки");
        System.out.println("5. Содержат геттеры но редко сеттеры");
        
        System.out.println("\n=== Демонстрация завершена ===");
    }
}