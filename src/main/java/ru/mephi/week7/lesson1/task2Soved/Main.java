package ru.mephi.week7.lesson1.task2Soved;

import ru.mephi.week7.lesson1.task2Soved.exceptions.AccountNotFoundException;
import ru.mephi.week7.lesson1.task2Soved.exceptions.InsufficientBalanceException;
import ru.mephi.week7.lesson1.task2Soved.exceptions.InvalidSumException;
import ru.mephi.week7.lesson1.task2Soved.exceptions.LimitExceededException;

public class Main {

    /**
     * <h2>Задание: система управления банковским счётом</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Необходимо реализовать симуляцию системы управления банковским счётом. В системе должны быть следующие возможности:</p>
     * <ul>
     * 	<li>Пополнение счёта (депозит).</li>
     * 	<li>Снятие денег со счёта (с учётом лимита и остатка).</li>
     * 	<li>Перевод денег на другой счёт.</li>
     * 	<li>Проверка баланса.</li>
     * </ul>
     * <p>Система должна поддерживать обработку исключительных ситуаций, таких как:</p>
     * <ul>
     * 	<li>Недостаточно средств для выполнения операции.</li>
     * 	<li>Лимит снятия за одну операцию.</li>
     * 	<li>Попытка перевода на несуществующий счёт.</li>
     * 	<li>Недопустимая сумма операции (например, отрицательные значения).</li>
     * </ul>
     * <p>Требования:</p>
     * <ul>
     * 	<li>Разработайте класс BankAccount, который будет хранить информацию о счёте и реализовывать методы для управления транзакциями.</li>
     * 	<li>Обработайте все возможные ошибки с помощью собственных пользовательских исключений.</li>
     * 	<li>Каждый метод должен корректно выбрасывать соответствующие исключения, а вызывающая сторона должна их обрабатывать.</li>
     * 	<li>Реализуйте тестовый сценарий с несколькими счетами и выполнением различных операций, включая успешные и ошибочные транзакции.</li>
     * </ul>
     */


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
