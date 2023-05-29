package com.example.superbank.api.controller;

import com.example.superbank.exceptions.NotEnoughMoneyException;
import com.example.superbank.utils.TransferBalance;
import com.example.superbank.exceptions.AccountNotFoundException;
import com.example.superbank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/add")
    public ResponseEntity registration(@RequestParam String firstName,
                                       @RequestParam String lastName,
                                       @RequestParam BigDecimal balance) {
        try {
            accountService.registration(firstName, lastName, balance);
            return ResponseEntity.ok("Account successfully registered.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong. // registration method");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllAccounts() {
        try {
            return ResponseEntity.ok(accountService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong. // getAllAccounts method");
        }
    }

    @GetMapping("/getOneTest")
    public ResponseEntity getAllAccountsTest(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(accountService.findOneTest(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong. // getAllAccounts method");
        }
    }

    @GetMapping("/getOne")
    public ResponseEntity getAccount(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(accountService.getAccount(id));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong. // getAccount method");
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestBody TransferBalance transferBalance) {
        try {
            return ResponseEntity.ok(accountService.addMoney(transferBalance.getTo(), transferBalance.getAmount()));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.badRequest().body("Something went wrong. // deposit method");
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestParam Long id,
                                   @RequestParam BigDecimal amount) {
        try {
            return ResponseEntity.ok(accountService.withdraw(id, amount));
        } catch (AccountNotFoundException | NotEnoughMoneyException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.ok("Account with an id \"" + accountService.delete(id) + "\" successfully deleted.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong. // delete method");
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity transfer(@RequestBody TransferBalance transferBalance) {
        try {
            accountService.transferMoney(
                    transferBalance.getFrom(),
                    transferBalance.getTo(),
                    transferBalance.getAmount());
            return ResponseEntity.ok("Transfer completed.");
        } catch (AccountNotFoundException | NotEnoughMoneyException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong. // transfer method");
        }
    }

}
