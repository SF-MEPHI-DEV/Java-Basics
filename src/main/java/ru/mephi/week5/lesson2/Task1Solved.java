package ru.mephi.week5.lesson2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Task1Solved {

    /**
     * <h2>Задание: текстовый оптимизатор</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Напишите программу на Java, которая:</p>
     * <ul>
     * 	<li>Запрашивает у пользователя путь к каталогу, содержащему текстовые файлы.</li>
     * 	<li>Проверяет, существует ли указанный каталог.</li>
     * 	<li>Перебирает все файлы в каталоге и находит те, которые были созданы более 7 дней назад.</li>
     * 	<li>Объединяет содержимое этих файлов в один файл, названный merged_old_files.txt.</li>
     * 	<li>После успешного объединения удаляет файлы, которые были включены в объединение.</li>
     * 	<li>Если каталог не существует или в нём нет файлов старше 7 дней, программа выводит соответствующие сообщения.</li>
     * 	</ul>
     */


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к каталогу с текстовыми файлами: ");
        String directoryPathStr = scanner.nextLine();
        Path directoryPath = Paths.get(directoryPathStr);

        if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
            System.out.println("Указанный каталог не существует или не является директорией.");
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date cutoffDate = calendar.getTime();

        Path mergedFilePath = directoryPath.resolve("merged_old_files.txt");
        boolean filesMerged = false;

        Object[] files = Files.list(directoryPath).toArray();

        for (Object fileObject : files) {

            if (!(fileObject instanceof Path)) {
                System.out.println(fileObject.toString() + "  - ошибка в обработке ");
                continue;
            }

            Path file = (Path)fileObject;

            if (Files.isRegularFile(file) && file.toString().endsWith(".txt")) {

                BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
                Date fileModifiedDate = new Date(attrs.lastModifiedTime().toMillis());

                if (fileModifiedDate.before(cutoffDate)) {
                    System.out.println("Обрабатываем файл: " + file);
                    Files.writeString(mergedFilePath, "=== Содержимое файла: " + file.getFileName() + " ===\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);

                    List<String> lines = Files.readAllLines(file);
                    Files.write(mergedFilePath, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    Files.delete(file);
                    System.out.println("Удалён файл: " + file);
                    filesMerged = true;
                }

            }
        }

        if (filesMerged) {
            System.out.println("Все файлы, старше 7 дней, объединены в файл: " + mergedFilePath);
        } else {
            System.out.println("Нет файлов старше 7 дней для объединения.");
        }
    }

}

