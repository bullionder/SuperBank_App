//package com.example.superbank.service;
//
//import com.example.superbank.Repositories.AccountRepository;
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AccountServiceTest {
//    AccountRepository accountRepository = new AccountRepository();
//    AccountService accountService = new AccountService(accountRepository);
//    IllegalArgumentException exception = new IllegalArgumentException();
//
//    @Test
//    void getMoneyOfFirstClient() {
//        BigDecimal balance = accountService.getMoney(1L);
//        assertEquals(BigDecimal.TEN, balance);
//    }
//
//    @Test
//    void getMoneyOfSecondClient() {
//        BigDecimal balance = accountService.getMoney(2L);
//        assertEquals(BigDecimal.TWO, balance);
//    }
//
//    @Test
//    void addMoneyToFirstClient() {
//        BigDecimal oldBalance = accountService.getMoney(1L);
//        accountService.addMoney(1L, BigDecimal.TEN);
//        BigDecimal newBalance = accountService.getMoney(1L);
//        assertEquals(oldBalance.add(BigDecimal.TEN), newBalance);
//    }
//
//    @Test
//    void addMoneyToSecondClient() {
//        BigDecimal oldBalance = accountService.getMoney(2L);
//        accountService.addMoney(2L, BigDecimal.TEN);
//        BigDecimal newBalance = accountService.getMoney(2L);
//        assertEquals(oldBalance.add(BigDecimal.TEN), newBalance);
//    }
//
//    @Test
//    void transferEnoughAmountOfMoneyFromFirstClient() {
//        BigDecimal firstClientAccBeforeTransfer = accountService.getMoney(1L);
//        BigDecimal secondClientAccBeforeTransfer = accountService.getMoney(2L);
//
//        accountService.transferMoney(1L, 2L, BigDecimal.TWO);
//
//        BigDecimal firstClientAccAfterTransfer = accountService.getMoney(1L);
//        BigDecimal secondClientAccAfterTransfer = accountService.getMoney(2L);
//
//        assertEquals(firstClientAccBeforeTransfer.subtract(BigDecimal.TWO), firstClientAccAfterTransfer);
//        assertEquals(secondClientAccBeforeTransfer.add(BigDecimal.TWO), secondClientAccAfterTransfer);
//    }
//
//    @Test
//    void transferEnoughAmountOfMoneyFromSecondClient() {
//        BigDecimal firstClientAccBeforeTransfer = accountService.getMoney(1L);
//        BigDecimal secondClientAccBeforeTransfer = accountService.getMoney(2L);
//
//        BigDecimal moneyTransfered = BigDecimal.ONE;
//
//        accountService.transferMoney(2L, 1L, moneyTransfered);
//
//        BigDecimal firstClientAccAfterTransfer = accountService.getMoney(1L);
//        BigDecimal secondClientAccAfterTransfer = accountService.getMoney(2L);
//
//        assertEquals(firstClientAccBeforeTransfer.add(moneyTransfered), firstClientAccAfterTransfer);
//        assertEquals(secondClientAccBeforeTransfer.subtract(moneyTransfered), secondClientAccAfterTransfer);
//    }
//
//
//    @Test
//    void transferShouldThrowIllegalArgumentException() {
//        BigDecimal firstClientAccBeforeTransfer = accountService.getMoney(1L);
//        BigDecimal secondClientAccBeforeTransfer = accountService.getMoney(2L);
//
//        assertThrows(IllegalArgumentException.class,
//                () -> {
//                    accountService.transferMoney(1L, 2L, BigDecimal.valueOf(1000));
//                });
//    }
//}