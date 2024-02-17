package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalanceZero() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    void shouldPayWhenBalanceBecomesNegative() {
        // Проверяем, что операция оплаты проходит, когда баланс становится отрицательным
        CreditAccount account = new CreditAccount(1000, 1000, 10);
        Assertions.assertTrue(account.pay(2000));
        Assertions.assertEquals(-1000, account.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenInitialBalanceIsNegative() {
        //Проверяем исключение что, начальный баланс не может быть отрицательным
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(-100, 1000, 10));
    }

    @Test
    void shouldThrowExceptionWhenRateIsNegative() {
        //Проверяем исключение что, кредитная ставка не может быть отрицательным
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(100, 1000, -10));
    }

    @Test
    void shouldThrowExceptionWhenCreditLimitIsNegative() {
        //Проверяем исключение что, кредитный лимит не может быть отрицательным
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CreditAccount(100, -1000, 10));
    }

    @Test
    void shouldNotPayWhenAmountIsNegative() {
        // Проверяем, что попытка оплаты отрицательной суммой приводит к неудаче
        CreditAccount account = new CreditAccount(1000, 1000, 10);
        Assertions.assertFalse(account.pay(-100));
    }


    @Test
    public void testPayWithPositiveBalance() {
        //проверяем покупку с положительным балансом
        CreditAccount account = new CreditAccount(1000, 5000, 10);
        boolean result = account.pay(500);
        Assertions.assertTrue(result);
        Assertions.assertEquals(500, account.getBalance());
    }


    @Test
    public void testPayWithNegativeBalanceExceedingCreditLimit() {
        //Проверяем попытку оплаты суммы, превышающей доступный лимит кредита, когда баланс счета уже отрицательный.
        CreditAccount account = new CreditAccount(0, 5000, 10);
        account.pay(5000);
        boolean result = account.pay(1000);
        Assertions.assertFalse(result);
        Assertions.assertEquals(-5000, account.getBalance());
    }

    @Test
    public void testAddWithNegativeAmount() {
        //Проверяем правильно ли обрабатывается добавление положительной суммы к балансу счета.
        CreditAccount account = new CreditAccount(1000, 5000, 10);
        boolean result = account.add(-500);
        Assertions.assertFalse(result);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    void shouldNotCalculateYearChangeWhenBalanceIsPositive() {
        // Проверяем, что метод `yearChange` не высчитывает годовой процент, когда баланс положительный
        CreditAccount account = new CreditAccount(1000, 1000, 10);
        Assertions.assertEquals(0, account.yearChange());
    }

    @Test
    void shouldCalculateYearChangeWhenBalanceIsNegative() {
        // Проверяем, что метод `yearChange` правильно высчитывает годовой процент, когда баланс отрицательный
        CreditAccount account = new CreditAccount(1000, 1000, 10);
        account.pay(1500);
        Assertions.assertEquals(-50, account.yearChange());
    }

    @Test
    void shouldGetCreditLimit() {
        // Проверяем, что метод `getCreditLimit` возвращает установленный кредитный лимит
        CreditAccount account = new CreditAccount(1000, 1000, 10);
        Assertions.assertEquals(1000, account.getCreditLimit());
    }

    @Test
    public void shouldAddToPositiveBalance() {
        //Проверяем, что к начальному балансу, прибавляется сумма
        CreditAccount account = new CreditAccount(1000, 5_000, 15);

        account.add(3_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }
}
