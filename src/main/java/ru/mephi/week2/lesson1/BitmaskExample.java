package ru.mephi.week2.lesson1;

public class BitmaskExample {
    public static void main(String[] args) {
        int flags = 0;          // Изначально все флаги выключены (0000)

        int FLAG_1 = 1 << 0;   // 0001 - первый бит
        int FLAG_2 = 1 << 1;   // 0010 - второй бит
        int FLAG_3 = 1 << 2;   // 0100 - третий бит

        // Включаем первый и третий флаг (делаем 0101)
        flags = flags | FLAG_1 | FLAG_3;
        System.out.println("Flags after setting FLAG_1 and FLAG_3: " + Integer.toBinaryString(flags)); // 101

        // Проверяем, включен ли второй флаг
        boolean isFlag2Set = (flags & FLAG_2) != 0;
        System.out.println("Is FLAG_2 set? " + isFlag2Set); // false

        // Включаем второй флаг
        flags |= FLAG_2;
        System.out.println("Flags after setting FLAG_2: " + Integer.toBinaryString(flags)); // 111

        // Выключаем первый флаг
        flags &= ~FLAG_1;
        System.out.println("Flags after clearing FLAG_1: " + Integer.toBinaryString(flags)); // 110

        // Переключаем третий флаг (включен - выключится)
        flags ^= FLAG_3;
        System.out.println("Flags after toggling FLAG_3: " + Integer.toBinaryString(flags)); // 010
    }
}