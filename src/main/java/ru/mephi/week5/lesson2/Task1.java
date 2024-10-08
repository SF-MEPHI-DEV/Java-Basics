package ru.mephi.week5.lesson2;

import java.io.File;
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

public class Task1 {

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

        boolean wasDeleted = false;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter directory: ");
        String directory = scanner.nextLine();

        Path direcotryPath = Paths.get(directory);

        if (!Files.exists(direcotryPath) || !Files.isDirectory(direcotryPath)) {
            System.out.println("Incorrect input");
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date cutofDate = calendar.getTime();

        Path mergeFile = direcotryPath.resolve("mergeFile.txt");

        Object[] pathsArray = Files.list(direcotryPath).toArray();

        for (Object fileObject : pathsArray) {

            if (!(fileObject instanceof Path)) {
                continue;
            }

            Path file = (Path) fileObject;

            if (Files.isRegularFile(file) && file.toString().endsWith(".txt")) {

                BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
                Date fileDate = new Date(attributes.lastModifiedTime().toMillis());

                if (fileDate.before(cutofDate)) {

                    System.out.println("Executing file: " + file);

                    Files.writeString(
                            mergeFile,
                            "String file: " + file.getFileName() + "\n",
                            StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND
                    );

                    List<String> lines = Files.readAllLines(file);
                    Files.write(
                            mergeFile,
                            lines,
                            StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND
                    );

                    Files.delete(file);
                    wasDeleted = true;

                }
            }
        }

        if (!wasDeleted) {
            System.out.println("No file found");
        }

    }

}
