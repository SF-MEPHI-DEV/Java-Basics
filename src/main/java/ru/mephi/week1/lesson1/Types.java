package ru.mephi.week1.lesson1;

public class Types {
    public static void main(String[] args) {
        String string = "He said \"Hello Types\"";
        System.out.println(string);

        // byte - 8 бит, диапазон: -128 до 127
        byte b = 127;

        // short - 16 бит, диапазон: -32,768 до 32,767
        short s = 32767;

        // int - 32 бит, диапазон: -2,147,483,648 до 2,147,483,647
        int i = 2147483647;

        // long - 64 бит, диапазон: -9,223,372,036,854,775,808 до 9,223,372,036,854,775,807
        long l = 9223372036854775807L;
        long long2 = 3114141414414L;
        // float - 32 бит, приблизительный диапазон: ±1.4E-45 до ±3.4028235E38
        float f = 3.4028235E38f;
        float f2 = 3.402842424f;

        // double - 64 бит, приблизительный диапазон: ±4.9E-324 до ±1.7976931348623157E308
        double d = 1.7976931348623157E308;

        // char - 16 бит, диапазон: 0 до 65,535 (символы Unicode)
        char c = 'A';
        char c2 = '\\';
        System.out.println(c2);
        // boolean - 1 бит (логическое значение: true или false)
        boolean bool = true;
        long maxLong = Long.MAX_VALUE;
        System.out.println(maxLong);
        long varLong = 7313131313131331111L;

        int maxInt = Integer.MAX_VALUE;  //  2147483647
        int overflowInt = maxInt + 1;   // Прибавляем 1
        System.out.println("Overflow int: " + overflowInt);
        // Результат будет: -2147483648 (минимальное значение int)

        byte maxByte = 127;   // Максимальное значение для byte
        maxByte += 1;         // Переполнение
        System.out.println(maxByte);
        // Вывод: -128 (минимальное значение byte)

    }
}
