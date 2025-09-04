package ru.mephi.week2.lesson1;

public class BitwiseOperationsExample {
    public static void main(String[] args) {
        int a = 29;        // 0001 1101 в двоичном виде
        int b = 15;        // 0000 1111 в двоичном виде

        // Побитовое И (AND)
        int andResult = a & b;     // 0000 1101 ->  13
        System.out.println("AND: " + andResult);

        // Побитовое ИЛИ (OR)
        int orResult = a | b;      // 0001 1111 -> 31
        System.out.println("OR: " + orResult);

        // Побитовое исключающее ИЛИ (XOR)
        int xorResult = a ^ b;     // 0001 0010 -> 18
        System.out.println("XOR: " + xorResult);

        // Побитовое НЕ (инверсия)
        int notResult = ~a;        // Инвертируем все биты a
        System.out.println("NOT: " + notResult);

        // Сдвиг влево (умножение на 2 в степени сдвига)
        int leftShift = a << 2;    // 0001 1101 << 2 -> 0111 0100 (116)
        System.out.println("Left Shift: " + leftShift);

        // Сдвиг вправо с сохранением знака (деление на 2 в степени сдвига)
        int rightShift = a >> 2;   // 0001 1101 >> 2 -> 0000 0111 (7)
        System.out.println("Right Shift: " + rightShift);

        // Сдвиг вправо с заполнением нулями (без сохранения знака)
        int unsignedRightShift = a >>> 2;
        System.out.println("Unsigned Right Shift: " + unsignedRightShift);

        int c = -16;
        // В двоичном виде (32 бита): 11111111 11111111 11111111 11110000

        // Сдвиг вправо с сохранением знака (знаковый сдвиг)
        int signedShift = c >> 2;
        System.out.println("Signed right shift (a >> 2): " + signedShift);
        // В двоичной форме будет: 11111111 11111111 11111111 11111100

        // Сдвиг вправо без сохранения знака (беззнаковый сдвиг)
        int unsignedShift = c >>> 2;
        System.out.println("Unsigned right shift (a >>> 2): " + unsignedShift);
        // В двоичной форме: 00111111 11111111 11111111 11111100
    }
}
