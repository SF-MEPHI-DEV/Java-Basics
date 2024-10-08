package ru.mephi.week6.lesson1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Task2Solved {

    /**
     * <h2>Задание: разработка программы для резервного копирования файлов с фильтрацией по типу</h2>
     * <br>
     * <h2>Описание: </h2>
     * <p>Вам необходимо написать программу, которая выполняет резервное копирование всех
     * файлов из одной директории в другую. Программа должна:</p>
     * <ul>
     * 	<li>Копировать только файлы определённого расширения (например, .jpg, .png, .txt), которые указаны пользователем.</li>
     *  <li>Если файл с таким именем уже существует в целевой директории, программа должна спрашивать у пользователя, перезаписывать его или пропустить.</li>
     * 	<li>Все действия программы (начало копирования, успешное завершение, ошибки) должны логироваться в файл backup_log.txt.</li>
     * 	<li>Программа должна выводить общее количество скопированных файлов, а также количество пропущенных файлов.</li>
     * 	</ul>
     */

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter source file path: ");
        String sourceDirectory = scanner.nextLine();

        System.out.print("Enter destination file path: ");
        String destinationDirectory = scanner.nextLine();

        System.out.println("Enter types for copy (example, '.jpg'):");
        String fileExtension = scanner.nextLine();

        File logFile = new File("backup_log.txt");

        if (!logFile.exists() && !logFile.createNewFile()) {
            System.out.println("Error wile creation log file");
            return;
        }

        FileOutputStream logStream = new FileOutputStream(logFile, true);

        writeToLog(logStream, "Copping was started.");

        File sourceDir = new File(sourceDirectory);
        File[] filesToCopy = listFilesWithExtension(sourceDir, fileExtension);

        if (filesToCopy == null || filesToCopy.length == 0) {
            String message = "File with extension " + fileExtension + " not found.";
            System.out.println(message);
            writeToLog(logStream, message);
            logStream.close();
            return;
        }

        int copiedFiles = 0;
        int skippedFiles = 0;

        for (File file : filesToCopy) {
            File destFile = new File(destinationDirectory, file.getName());

            if (destFile.exists()) {
                System.out.println("File " + file.getName() + " already exits. Rewrite it? (y/n):");
                String response = scanner.nextLine();

                if (!response.equalsIgnoreCase("y")) {
                    writeToLog(logStream, "File " + file.getName() + " was skipped.");
                    skippedFiles++;
                    continue;
                }
            }

            copyFile(file, destFile);
            writeToLog(logStream, "File " + file.getName() + " successfully copied.");
            copiedFiles++;

        }

        writeToLog(logStream, "Copping was ended. " + copiedFiles + " file were rewrote, were skipped: " + skippedFiles);
        System.out.println("Copping was ended. " + copiedFiles + " file were rewrote, were skipped: " + skippedFiles);

        logStream.close();
    }

    public static void writeToLog(FileOutputStream stream, String message) throws IOException {
        if (stream != null) {

            stream.write(message.getBytes(StandardCharsets.UTF_8));
            stream.write("\n".getBytes(StandardCharsets.UTF_8));
            stream.flush();

        }
    }


    public static File[] listFilesWithExtension(File directory, String extension) {
        File[] files = directory.listFiles();
        if (files == null) {
            return null;
        }
        int count = 0;
        for (File file : files) {
            if (file.getName().endsWith(extension)) {
                count++;
            }
        }
        File[] filteredFiles = new File[count];
        int index = 0;
        for (File file : files) {
            if (file.getName().endsWith(extension)) {
                filteredFiles[index++] = file;
            }
        }
        return filteredFiles;
    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {

        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, bytesRead);
        }

    }
}
