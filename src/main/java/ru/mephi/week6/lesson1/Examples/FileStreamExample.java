package ru.mephi.week6.lesson1.Examples;

import java.io.*;

/**
 * <h2>Работа с файловыми потоками</h2>
 * <p>
 * Примеры использования потоков для работы с файлами:
 * <ul>
 *     <li>FileInputStream / FileOutputStream - байтовые потоки для файлов</li>
 *     <li>DataInputStream / DataOutputStream - для примитивных типов</li>
 *     <li>ObjectInputStream / ObjectOutputStream - для объектов</li>
 * </ul>
 * </p>
 */
public class FileStreamExample {

    public static void main(String[] args) {
        System.out.println("=== Работа с файловыми потоками ===\n");

        try {
            // 1. Байтовые потоки - FileInputStream/FileOutputStream
            demonstrateByteStreams();

            // 2. DataStream - запись примитивных типов
            demonstrateDataStreams();

            // 3. ObjectStream - сериализация объектов
            demonstrateObjectStreams();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 1. Байтовые потоки - для работы с бинарными данными
     */
    private static void demonstrateByteStreams() throws IOException {
        System.out.println("1. FileInputStream и FileOutputStream (байтовые потоки)\n");

        String filename = "byte_data.bin";

        // Запись байтов в файл
        System.out.println("   Запись байтов в файл:");
        try (FileOutputStream fos = new FileOutputStream(filename, false)) {
            // Записываем отдельные байты
            fos.write(65);  // A
            fos.write(66);  // B
            fos.write(67);  // C

            // Записываем массив байтов
            byte[] data = {68, 69, 70, 71, 72};  // D E F G H
            fos.write(data);

            System.out.println("   Записано 8 байтов");
        }

        // Чтение байтов из файла
        System.out.println("   Чтение байтов из файла:");
        try (FileInputStream fis = new FileInputStream(filename)) {
            int byteValue;
            int count = 0;
            while ((byteValue = fis.read()) != -1) {
                System.out.print((char) byteValue);
                count++;
            }
            System.out.println("\n   Прочитано " + count + " байтов");
        }

        System.out.println("\n   Применение: изображения, аудио, видео, любые бинарные файлы\n");
    }

    /**
     * 2. DataStream - чтение/запись примитивных типов
     */
    private static void demonstrateDataStreams() throws IOException {
        System.out.println("2. DataInputStream и DataOutputStream (примитивные типы)\n");

        String filename = "data.dat";

        // Запись типизированных данных
        System.out.println("   Запись типизированных данных:");
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(filename))) {

            dos.writeInt(12345);
            dos.writeDouble(3.14159);
            dos.writeBoolean(true);
            dos.writeUTF("Текстовая строка");

            System.out.println("   Записано: int, double, boolean, String");
        }

        // Чтение типизированных данных
        System.out.println("   Чтение типизированных данных:");
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(filename))) {

            int intValue = dis.readInt();
            double doubleValue = dis.readDouble();
            boolean boolValue = dis.readBoolean();
            String stringValue = dis.readUTF();

            System.out.println("   int:     " + intValue);
            System.out.println("   double:  " + doubleValue);
            System.out.println("   boolean: " + boolValue);
            System.out.println("   String:  " + stringValue);
        }

        System.out.println("\n   Применение: конфигурации, бинарные форматы данных\n");
    }

    /**
     * 3. ObjectStream - сериализация объектов
     */
    private static void demonstrateObjectStreams() throws IOException, ClassNotFoundException {
        System.out.println("3. ObjectInputStream и ObjectOutputStream (сериализация)\n");

        String filename = "person.ser";

        // Создаем и сохраняем объект
        Person person = new Person("Иван Иванов", 25, "ivan@example.com");
        System.out.println("   Создан объект: " + person);

        System.out.println("   Сериализация объекта...");
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(person);
            System.out.println("   Объект сохранен в файл");
        }

        // Загружаем объект
        System.out.println("   Десериализация объекта...");
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            Person loadedPerson = (Person) ois.readObject();
            System.out.println("   Загружен объект: " + loadedPerson);
        }

        System.out.println("\n   Применение: сохранение состояния объектов, кеширование\n");
    }

    // ==================== Вспомогательные классы ====================

    /**
     * Простой класс для демонстрации сериализации
     */
    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        private int age;
        private String email;

        public Person(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + ", email='" + email + "'}";
        }
    }
}
