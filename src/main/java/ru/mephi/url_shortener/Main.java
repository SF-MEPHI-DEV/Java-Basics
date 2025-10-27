package ru.mephi.url_shortener;

import ru.mephi.url_shortener.exceptions.InvalidUrlException;
import ru.mephi.url_shortener.exceptions.UrlNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final UrlShortenerService service = new UrlShortenerService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String BASE_URL = "http://short.url/";
    //http://short.url/sfssfsdfsfs
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = getIntInput("Выберите действие: ");

            try {
                switch (choice) {
                    case 1 -> createShortUrl();
                    case 2 -> getOriginalUrl();
                    case 3 -> deleteUrl();
                    case 4 -> service.showAllUrls();
                    case 5 -> searchUrls();
                    case 6 -> service.showStatistics();
                    case 7 -> showTopUrls();
                    case 8 -> showRecentUrls();
                    case 9 -> saveData();
                    case 10 -> loadData();
                    case 0 -> {
                        System.out.println("Выход из программы");
                        running = false;
                    }
                    default -> System.out.println("Неверный выбор");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║   СЕРВИС СОКРАЩЕНИЯ ССЫЛОК               ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1.  Создать короткую ссылку              ║");
        System.out.println("║ 2.  Получить оригинальный URL            ║");
        System.out.println("║ 3.  Удалить ссылку                       ║");
        System.out.println("║ 4.  Показать все ссылки                  ║");
        System.out.println("║ 5.  Поиск ссылок                         ║");
        System.out.println("║ 6.  Показать статистику                  ║");
        System.out.println("║ 7.  Топ самых популярных                 ║");
        System.out.println("║ 8.  Последние созданные                  ║");
        System.out.println("║ 9.  Сохранить данные (CSV)               ║");
        System.out.println("║ 10. Загрузить данные (CSV)               ║");
        System.out.println("║ 0.  Выход                                ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    private static void createShortUrl() throws InvalidUrlException {
        System.out.print("Введите полный URL: ");
        String originalUrl = scanner.nextLine();
        String shortCode = service.createShortUrl(originalUrl);
        System.out.println("\nКороткая ссылка создана!");
        System.out.println("Оригинальный URL: " + originalUrl);
        System.out.println("Короткая ссылка: " + BASE_URL + shortCode);
        System.out.println("Код: " + shortCode);
    }

    private static void getOriginalUrl() throws UrlNotFoundException {
        System.out.print("Введите короткий код: ");
        String shortCode = scanner.nextLine();
        String originalUrl = service.getOriginalUrl(shortCode);
        System.out.println("\nПереход по ссылке: " + BASE_URL + shortCode);
        System.out.println("Оригинальный URL: " + originalUrl);
    }

    private static void deleteUrl() throws UrlNotFoundException {
        System.out.print("Введите короткий код: ");
        String shortCode = scanner.nextLine();
        service.deleteUrl(shortCode);
    }

    private static void searchUrls() {
        System.out.print("Введите поисковый запрос: ");
        String query = scanner.nextLine();
        List<ShortUrl> results = service.searchUrls(query);

        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
        } else {
            System.out.println("\nНайдено ссылок: " + results.size());
            results.forEach(System.out::println);
        }
    }

    private static void showTopUrls() {
        int n = getIntInput("Сколько ссылок показать: ");
        service.showTopUrls(n);
    }

    private static void showRecentUrls() {
        int n = getIntInput("Сколько ссылок показать: ");
        service.showRecentUrls(n);
    }

    private static void saveData() throws IOException {
        System.out.print("Введите имя файла (например, urls.csv): ");
        String filename = scanner.nextLine();
        service.saveToCSV(filename);
    }

    private static void loadData() throws IOException {
        System.out.print("Введите имя файла: ");
        String filename = scanner.nextLine();
        service.loadFromCSV(filename);
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число");
            }
        }
    }
}
