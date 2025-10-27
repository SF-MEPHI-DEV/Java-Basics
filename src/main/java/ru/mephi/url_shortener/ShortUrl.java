package ru.mephi.url_shortener;

import java.time.LocalDateTime;

public class ShortUrl {
    private final String shortCode;
    private final String originalUrl;
    private final LocalDateTime createdAt;
    private int accessCount;

    public ShortUrl(String shortCode, String originalUrl) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.createdAt = LocalDateTime.now();
        this.accessCount = 0;
    }

    public ShortUrl(String shortCode, String originalUrl, LocalDateTime createdAt, int accessCount) {
        this.shortCode = shortCode;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.accessCount = accessCount;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getAccessCount() {
        return accessCount;
    }

    public void incrementAccessCount() {
        accessCount++;
    }

    @Override
    public String toString() {
        return shortCode + " -> " + originalUrl + " (переходов: " + accessCount + ", создан: " + createdAt + ")";
    }
}
