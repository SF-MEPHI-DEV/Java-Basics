package ru.mephi.week6.lesson1.Examples;

import java.io.*;

/**
 * Примеры работы с файловыми потоками
 */
public class FileStreamsDemo {

    public static void main(String[] args) {
        try {
            byteStreamExample();
            dataStreamExample();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Работа с байтовыми потоками
    static void byteStreamExample() throws IOException {
        System.out.println("1. FileInputStream/FileOutputStream\n");

        String file = "test.dat";

        // Пишем байты
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(65); // A
            fos.write(66); // B
            fos.write(67); // C
        }

        // Читаем
        try (FileInputStream fis = new FileInputStream(file)) {
            int b;
            System.out.print("Прочитали: ");
            while ((b = fis.read()) != -1) {
                System.out.print((char) b);
            }
            System.out.println("\n");
        }
    }

    // Типизированные данные
    static void dataStreamExample() throws IOException {
        System.out.println("2. DataInputStream/DataOutputStream\n");

        String file = "data.bin";

        // Пишем разные типы
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(42);
            dos.writeDouble(3.14);
            dos.writeBoolean(true);
            dos.writeUTF("Hello");
        }

        // Читаем обратно
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            System.out.println("int: " + dis.readInt());
            System.out.println("double: " + dis.readDouble());
            System.out.println("boolean: " + dis.readBoolean());
            System.out.println("String: " + dis.readUTF());
            System.out.println();
        }
    }
}
