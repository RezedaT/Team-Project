package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

public class BankTest {

    @Test
    public void shouldPayBetweenSavingsAccounts() { // перевод между накопителными счетами
        Bank bank = new Bank();
        SavingAccount account1 = new SavingAccount(3_000, 1_000, 10_000, 5);
        SavingAccount account2 = new SavingAccount(5_000, 1_000, 10_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(true, bank.transfer(account1, account2, 1_999));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());

    }

    @Test
    public void shouldPayBetweenSavingsAccountsEqualToMinBalance() {
        // перевод между накопителными счетами; сумма перевода до минбаланса
        Bank bank = new Bank();
        SavingAccount account1 = new SavingAccount(3_000, 1_000, 10_000, 5);
        SavingAccount account2 = new SavingAccount(5_000, 1_000, 10_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(true, bank.transfer(account1, account2, 2_000));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());

    }
    @Test
    public void shouldPayBetweenSavingsAccountsLowThanMinBalance() {
        // перевод между накопителными счетами; сумма перевода выходит за границу минбаланса
        Bank bank = new Bank();
        SavingAccount account1 = new SavingAccount(3_000, 1_000, 10_000, 5);
        SavingAccount account2 = new SavingAccount(5_000, 1_000, 10_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(false, bank.transfer(account1, account2, 2_001));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());

    }
    @Test
    public void shouldPayBetweenSavingsAccountsMoreThanMaxBalance() {
        // перевод между накопителными счетами; сумма перевода выходит за границу максбаланса
        Bank bank = new Bank();
        SavingAccount account1 = new SavingAccount(13_000, 1_000, 15_000, 5);
        SavingAccount account2 = new SavingAccount(5_000, 1_000, 10_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(false, bank.transfer(account1, account2, 5_001));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());

    }

    @Test
    public void shouldPayBetweenCreditAccounts() { // перевод между кредитными счетами
        Bank bank = new Bank();
        CreditAccount account1 = new CreditAccount(3_000, 5_000, 15);
        CreditAccount account2 = new CreditAccount(5_000, 5_000, 15);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(true, bank.transfer(account1, account2, 7_999));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());
    }
    @Test
    public void shouldPayBetweenCreditAccountsEqualToLimit() { // перевод между кредитными счетами
        Bank bank = new Bank();
        CreditAccount account1 = new CreditAccount(3_000, 5_000, 15);
        CreditAccount account2 = new CreditAccount(5_000, 5_000, 15);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(true, bank.transfer(account1, account2, 8_000));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());
    }

    @Test
    public void shouldPayBetweenCreditAccountsMoreThanLimit() {
        // перевод между кредитными счетами сверх лимита
        Bank bank = new Bank();
        CreditAccount account1 = new CreditAccount(3_000, 5_000, 15);
        CreditAccount account2 = new CreditAccount(5_000, 5_000, 15);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(false, bank.transfer(account1, account2, 8_001));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());
    }

    @Test
    public void shouldPayBetweenCreditAndSavingsAccount() {
        // перевод между кредитным и накопительным счетами
        Bank bank = new Bank();
        CreditAccount account1 = new CreditAccount(5_000, 5_000, 15);
        SavingAccount account2 = new SavingAccount(2_000, 1_000, 15_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(true, bank.transfer(account1, account2, 9_999));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());
    }
    @Test
    public void shouldPayBetweenCreditAndSavingsAccountEqualToLimit() {
        // перевод между кредитным и накопительным счетами до лимита
        Bank bank = new Bank();
        CreditAccount account1 = new CreditAccount(5_000, 5_000, 15);
        SavingAccount account2 = new SavingAccount(2_000, 1_000, 15_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(true, bank.transfer(account1, account2, 10_000));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());
    }
    @Test
    public void shouldPayBetweenCreditAndSavingsAccountMoreThanLimit() {
        // перевод между кредитным и накопительным счетами сверх лимита
        Bank bank = new Bank();
        CreditAccount account1 = new CreditAccount(5_000, 5_000, 15);
        SavingAccount account2 = new SavingAccount(2_000, 1_000, 15_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(false, bank.transfer(account1, account2, 10_001));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());
    }
    @Test
    public void shouldPayBetweenCreditAndSavingsAccountMoreThanMaxBalance() {
        // перевод между кредитными и накопительным счетом превышен максимальный баланс
        Bank bank = new Bank();
        CreditAccount account1 = new CreditAccount(5_000, 5_000, 15);
        SavingAccount account2 = new SavingAccount(2_000, 1_000, 10_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(false, bank.transfer(account1, account2, 9_999));
        System.out.println("Баланс счета с которого переводим:" + account1.getBalance());
        System.out.println("Баланс счета на который переводим:" + account2.getBalance());
    }

    @Test
    public void shouldPayBetweenSavingsAndCreditAccount() {// перевод между накопительным и кредитным счетами
        Bank bank = new Bank();
        CreditAccount account1 = new CreditAccount(5_000, 5_000, 15);
        SavingAccount account2 = new SavingAccount(2_000, 1_000, 10_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(true, bank.transfer(account1, account2, 999));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());

    }
    @Test
    public void shouldPayBetweenSavingsAndCreditAccountEqualToMinBalance() {
        // перевод между накопительным и кредитным счетами до мин
        Bank bank = new Bank();
        CreditAccount account1 = new CreditAccount(5_000, 5_000, 15);
        SavingAccount account2 = new SavingAccount(2_000, 1_000, 10_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(true, bank.transfer(account1, account2, 1_000));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());

    }
    @Test
    public void shouldPayBetweenSavingsAndCreditAccountMoreThanMinBalance() {
        // перевод между накопительным и кредитным счетами до мин
        Bank bank = new Bank();
        CreditAccount account1 = new CreditAccount(5_000, 5_000, 15);
        SavingAccount account2 = new SavingAccount(2_000, 1_000, 10_000, 5);

        System.out.print("Счет списания. Баланс на начало: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на начало: " + account2.getBalance());
        Assertions.assertEquals(true, bank.transfer(account1, account2, 1_001));
        System.out.print("Счет списания. Баланс на конец: " + account1.getBalance());
        System.out.println(" Счет зачисления. Баланс на конец: " + account2.getBalance());

    }

}
