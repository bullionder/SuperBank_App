package com.example.superbank.service;

import com.example.superbank.exceptions.NotEnoughMoneyException;
import com.example.superbank.repositories.AccountRepository;
import com.example.superbank.entities.AccountEntity;
import com.example.superbank.exceptions.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity registration(String firstName, String lastName, BigDecimal balance) {
        AccountEntity newAccount = new AccountEntity();
        newAccount.setFirstName(firstName);
        newAccount.setLastName(lastName);
        newAccount.setBalance(balance);

        return accountRepository.save(newAccount);
    }

    public AccountEntity getAccount(Long id) throws AccountNotFoundException {
        if (accountRepository.findById(id).isPresent()) {
            return accountRepository.findById(id).get();
        } else {
            throw new AccountNotFoundException("Account not found.");
        }
    }

    public Long delete(Long id) {
        accountRepository.deleteById(id);
        return id;
    }

    public BigDecimal addMoney(Long accountId, BigDecimal money) throws AccountNotFoundException, IllegalArgumentException {
        if (money.signum() == -1) throw new IllegalArgumentException("Positive integer expected.");
        AccountEntity account = getAccount(accountId);
        account.setBalance(account.getBalance().add(money));
        accountRepository.updateBalanceById(account.getBalance(), accountId);
        return money;
    }

    public void transferMoney(Long from, Long to, BigDecimal amount) throws AccountNotFoundException, NotEnoughMoneyException, IllegalArgumentException {
        if (amount.signum() == -1) throw new IllegalArgumentException("Positive integer expected.");
        AccountEntity fromAccount = getAccount(from);
        AccountEntity toAccount = getAccount(to);
        BigDecimal fromBalance = fromAccount.getBalance();
        BigDecimal toBalance = toAccount.getBalance();
        if (fromAccount.getBalance().compareTo(amount) >= 0) {
            BigDecimal fromNewBalance = fromBalance.subtract(amount);
            fromAccount.setBalance(fromNewBalance);
            BigDecimal toNewBalance = toBalance.add(amount);
            toAccount.setBalance(toNewBalance);
            accountRepository.updateBalanceById(fromNewBalance, from);
            accountRepository.updateBalanceById(toNewBalance, to);
        } else throw new NotEnoughMoneyException("Not enough money to transfer.");
    }

    public List<AccountEntity> findOneTest(Long id) {
        return accountRepository.findOneTest(id);
    }

    public List<AccountEntity> getAll() {
        return accountRepository.findAll();
    }

    public BigDecimal withdraw(Long id, BigDecimal amount) throws AccountNotFoundException, NotEnoughMoneyException {
        BigDecimal oldBalance = getAccount(id).getBalance();
        if (oldBalance.compareTo(amount) >= 0) {
            accountRepository.updateBalanceById(oldBalance.subtract(amount), id);
            return amount;
        } else throw new NotEnoughMoneyException("Not enough money to withdraw");
    }
}
