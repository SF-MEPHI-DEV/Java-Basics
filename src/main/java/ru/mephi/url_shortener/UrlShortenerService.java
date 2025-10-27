package ru.mephi.url_shortener;

import ru.mephi.url_shortener.exceptions.InvalidUrlException;
import ru.mephi.url_shortener.exceptions.UrlNotFoundException;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UrlShortenerService {
    private final Map<String, ShortUrl> urlMap;
    private final Random random;
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;

    public UrlShortenerService() {
        this.urlMap = new HashMap<>();
        this.random = new Random();
    }

    public String createShortUrl(String originalUrl) throws InvalidUrlException {

        validateUrl(originalUrl);

        Optional<ShortUrl> existing = urlMap.values().stream()
                .filter(url -> url.getOriginalUrl().equals(originalUrl))
                .findFirst();

        if (existing.isPresent()) {
            return existing.get().getShortCode();
        }

        String shortCode = generateShortCode();
        ShortUrl shortUrl = new ShortUrl(shortCode, originalUrl);
        urlMap.put(shortCode, shortUrl);
        return shortCode;
    }

    public String getOriginalUrl(String shortCode) throws UrlNotFoundException {
        ShortUrl shortUrl = urlMap.get(shortCode);
        if (shortUrl == null) {
            throw new UrlNotFoundException("Короткая ссылка не найдена: " + shortCode);
        }
        shortUrl.incrementAccessCount();
        return shortUrl.getOriginalUrl();
    }

    public void deleteUrl(String shortCode) throws UrlNotFoundException {
        ShortUrl removed = urlMap.remove(shortCode);
        if (removed == null) {
            throw new UrlNotFoundException("Короткая ссылка не найдена: " + shortCode);
        }
        System.out.println("Удалена ссылка: " + removed);
    }

    public void showAllUrls() {
        if (urlMap.isEmpty()) {
            System.out.println("Нет сохраненных ссылок");
            return;
        }

        System.out.println("\n=== Все сокращенные ссылки ===");
        urlMap.values().stream()
                .sorted(Comparator.comparing(ShortUrl::getCreatedAt).reversed())
                .forEach(System.out::println);
    }

    public void showStatistics() {
        if (urlMap.isEmpty()) {
            System.out.println("Нет данных для статистики");
            return;
        }

        System.out.println("\n=== Статистика ===");
        System.out.println("Всего ссылок: " + urlMap.size());

        int totalClicks = urlMap.values().stream()
                .mapToInt(ShortUrl::getAccessCount)
                .sum();
        System.out.println("Всего переходов: " + totalClicks);

        double avgClicks = urlMap.values().stream()
                .mapToInt(ShortUrl::getAccessCount)
                .average()
                .orElse(0.0);
        System.out.println("Средних переходов на ссылку: " + String.format("%.2f", avgClicks));

        ShortUrl mostPopular = urlMap.values().stream()
                .max(Comparator.comparingInt(ShortUrl::getAccessCount))
                .orElse(null);

        if (mostPopular != null && mostPopular.getAccessCount() > 0) {
            System.out.println("\nСамая популярная ссылка:");
            System.out.println(mostPopular);
        }
    }

    public void showTopUrls(int n) {
        System.out.println("\n=== Топ-" + n + " самых популярных ссылок ===");
        urlMap.values().stream()
                .sorted(Comparator.comparingInt(ShortUrl::getAccessCount).reversed())
                .limit(n)
                .forEach(url -> System.out.println(url.getShortCode() + " -> " + url.getOriginalUrl() +
                        " (" + url.getAccessCount() + " переходов)"));
    }

    public List<ShortUrl> searchUrls(String query) {
        return urlMap.values().stream()
                .filter(url -> url.getOriginalUrl().toLowerCase().contains(query.toLowerCase()) ||
                        url.getShortCode().toLowerCase().contains(query.toLowerCase()))
                .sorted(Comparator.comparing(ShortUrl::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public void showRecentUrls(int n) {
        System.out.println("\n=== Последние " + n + " созданных ссылок ===");
        urlMap.values().stream()
                .sorted(Comparator.comparing(ShortUrl::getCreatedAt).reversed())
                .limit(n)
                .forEach(System.out::println);
    }

    public void saveToCSV(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("ShortCode,OriginalUrl,CreatedAt,AccessCount\n");
            for (ShortUrl url : urlMap.values()) {
                writer.write(url.getShortCode() + "," + url.getOriginalUrl() + "," +
                        url.getCreatedAt() + "," + url.getAccessCount() + "\n");
            }
        }
        System.out.println("Данные сохранены в " + filename);
    }

    public void loadFromCSV(String filename) throws IOException {
        urlMap.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                String shortCode = parts[0];
                String originalUrl = parts[1];
                LocalDateTime createdAt = LocalDateTime.parse(parts[2]);
                int accessCount = Integer.parseInt(parts[3]);

                ShortUrl shortUrl = new ShortUrl(shortCode, originalUrl, createdAt, accessCount);
                urlMap.put(shortCode, shortUrl);
            }
        }
        System.out.println("Данные загружены из " + filename);
    }

    private String generateShortCode() {
        String shortCode;
        do {
            StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);
            for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
                sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
            }
            shortCode = sb.toString();
        } while (urlMap.containsKey(shortCode));
        return shortCode;
    }

    private void validateUrl(String url) throws InvalidUrlException {
        if (url == null || url.trim().isEmpty()) {
            throw new InvalidUrlException("URL не может быть пустым");
        }
        if (!url.matches("^https?://.*")) {
            throw new InvalidUrlException("URL должен начинаться с http:// или https://");
        }

        if (url.length() > 2048) {
            throw new InvalidUrlException("URL слишком длинный (максимум 2048 символов)");
        }
    }
}
