package ru.mephi.week7.lesson1.Task1Soved;

import ru.mephi.week7.lesson1.Task1Soved.exceptions.AccountNotFoundException;
import ru.mephi.week7.lesson1.Task1Soved.exceptions.InsufficientBalanceException;
import ru.mephi.week7.lesson1.Task1Soved.exceptions.InvalidSumException;
import ru.mephi.week7.lesson1.Task1Soved.exceptions.LimitExceededException;

public class Main {

    public static void main(String[] args) {
        try {
            BankAccount account1 = new BankAccount("12345", 1000, 500);
            BankAccount account2 = new BankAccount("67890", 500, 300);

            account1.deposit(200);
            account2.deposit(300);

            account1.withdraw(700);
            account2.withdraw(100);

            account1.transfer(account2, 300);

            account1.withdraw(500);

        } catch (
                InsufficientBalanceException |
                LimitExceededException |
                InvalidSumException |
                AccountNotFoundException e
        ) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

}
