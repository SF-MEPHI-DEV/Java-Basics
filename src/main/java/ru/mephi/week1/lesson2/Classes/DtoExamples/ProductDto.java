package ru.mephi.week1.lesson2.Classes.DtoExamples;

// DTO для товара в каталоге
public class ProductDto {
    // final поля - неизменяемые данные товара
    public final long productId;
    public final String name;
    public final String category;
    public final double price; // упрощено - используем double
    public final int stockQuantity;
    public final boolean isActive;
    
    // статическое поле для отслеживания общего количества товаров
    private static long totalProducts = 0;
    
    // основной конструктор
    public ProductDto(String name, String category, double price, int stockQuantity) {
        this.productId = ++totalProducts; // простой способ генерации ID
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.isActive = stockQuantity > 0; // активен если есть в наличии
    }
    
    // бизнес-методы
    public boolean isInStock() {
        return stockQuantity > 0;
    }
    
    public boolean isExpensive() {
        return price > 5000; // упрощенное сравнение
    }
    
    public boolean isPopularCategory() {
        return "electronics".equalsIgnoreCase(category) || 
               "books".equalsIgnoreCase(category);
    }
    
    // получить цену со скидкой - упрощенная версия
    public double getDiscountedPrice(int discountPercent) {
        return price - (price * discountPercent / 100.0);
    }
    
    // статический метод
    public static long getTotalProductsCount() {
        return totalProducts;
    }
    
    @Override
    public String toString() {
        return "Товар{" +
               "id=" + productId +
               ", название='" + name + "'" +
               ", категория='" + category + "'" +
               ", цена=" + price + " руб." +
               ", остаток=" + stockQuantity +
               ", активен=" + isActive +
               "}";
    }
}