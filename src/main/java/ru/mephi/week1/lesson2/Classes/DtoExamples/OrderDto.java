package ru.mephi.week1.lesson2.Classes.DtoExamples;

// DTO для заказа в интернет-магазине
public class OrderDto {
    // данные заказа - неизменяемые
    public final long orderId;
    public final long customerId;
    public final double totalAmount; // упрощено - используем double вместо BigDecimal
    public final String status;
    public final String shippingAddress;
    
    // основной конструктор
    public OrderDto(long customerId, double totalAmount, String shippingAddress) {
        this.orderId = System.currentTimeMillis(); // простой способ генерации ID
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.status = "PENDING"; // новый заказ всегда в ожидании
        this.shippingAddress = shippingAddress;
    }
    
    // методы для бизнес-логики
    public boolean isExpensive() {
        return totalAmount > 10000; // упрощенное сравнение
    }
    
    public boolean isCompleted() {
        return "COMPLETED".equals(status);
    }
    
    public boolean canBeCancelled() {
        return "PENDING".equals(status);
    }
    
    // получить отформатированную сумму
    public String getFormattedAmount() {
        return totalAmount + " руб.";
    }
    
    @Override
    public String toString() {
        return "Заказ{" +
               "номер=" + orderId +
               ", клиент=" + customerId +
               ", сумма=" + getFormattedAmount() +
               ", статус='" + status + "'" +
               "}";
    }
}