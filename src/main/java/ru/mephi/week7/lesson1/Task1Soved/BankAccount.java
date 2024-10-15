package ru.mephi.week7.lesson1.Task1Soved;

import ru.mephi.week7.lesson1.Task1Soved.exceptions.AccountNotFoundException;
import ru.mephi.week7.lesson1.Task1Soved.exceptions.InsufficientBalanceException;
import ru.mephi.week7.lesson1.Task1Soved.exceptions.InvalidSumException;
import ru.mephi.week7.lesson1.Task1Soved.exceptions.LimitExceededException;

public class BankAccount {

    private final double withdrawalLimit;
    private final String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance, double withdrawalLimit) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.withdrawalLimit = withdrawalLimit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidSumException {
        if (amount <= 0) {
            throw new InvalidSumException("Сумма для пополнения должна быть положительной.");
        }
        balance += amount;
        System.out.println("Пополнение на сумму: " + amount + ". Новый баланс: " + balance);
    }

    public void withdraw(double amount) throws InvalidSumException, InsufficientBalanceException, LimitExceededException {
        if (amount <= 0) {
            throw new InvalidSumException("Сумма для снятия должна быть положительной.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Недостаточно средств для снятия.");
        }
        if (amount > withdrawalLimit) {
            throw new LimitExceededException("Сумма превышает лимит снятия.");
        }
        balance -= amount;
        System.out.println("Снятие на сумму: " + amount + ". Остаток: " + balance);
    }

    public void transfer(BankAccount toAccount, double amount) throws InvalidSumException, InsufficientBalanceException, LimitExceededException, AccountNotFoundException {
        if (toAccount == null) {
            throw new AccountNotFoundException("Счет-получатель не найден.");
        }
        if (amount <= 0) {
            throw new InvalidSumException("Сумма для перевода должна быть положительной.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Недостаточно средств для перевода.");
        }
        this.balance -= amount;
        toAccount.balance += amount;
        System.out.println("Перевод " + amount + " на счет: " + toAccount.getAccountNumber() + ". Ваш новый баланс: " + balance);
    }

}
