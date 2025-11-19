package com.controller;

import com.bean.dto.*;
import com.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // -------------------- CREATE ACCOUNT --------------------
    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(@Valid @RequestBody AccountRequest request) {
        AccountResponseDto response = accountService.createAccount(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // -------------------- GET ALL ACCOUNTS --------------------
    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // -------------------- GET ACCOUNT --------------------
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponseDto> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getAccount(accountNumber));
    }

    // -------------------- UPDATE ACCOUNT --------------------
    @PutMapping("/{accountNumber}")
    public ResponseEntity<AccountResponseDto> updateAccount(
            @PathVariable String accountNumber,
           @Valid @RequestBody AccountRequest request) {
        return ResponseEntity.ok(accountService.updateAccount(accountNumber, request));
    }

    // -------------------- DELETE ACCOUNT --------------------
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Map<String, String>> deleteAccount(@PathVariable String accountNumber) {

        accountService.deleteAccount(accountNumber);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Account has been deleted successfully");

        return ResponseEntity.ok(response);
    }


    // -------------------- DEPOSIT --------------------
    @PutMapping("/{accountNumber}/deposit")
    public ResponseEntity<TransactionResponseDto> deposit(
            @PathVariable String accountNumber,
        @Valid @RequestBody DepositRequest request) {
        return ResponseEntity.ok(accountService.deposit(accountNumber, request));
    }

    // -------------------- WITHDRAW --------------------
    @PutMapping("/{accountNumber}/withdraw")
    public ResponseEntity<TransactionResponseDto> withdraw(
            @PathVariable String accountNumber,
           @Valid @RequestBody WithdrawRequest request) {
        return ResponseEntity.ok(accountService.withdraw(accountNumber, request));
    }

    // -------------------- TRANSFER --------------------
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDto> transfer(
           @Valid @RequestBody TransferRequest request) {
        return ResponseEntity.ok(accountService.transfer(request));
    }

    // -------------------- GET TRANSACTIONS --------------------
    @GetMapping("/{accountNumber}/transactions")
    public ResponseEntity<List<TransactionResponseDto>> getTransactions(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getTransactions(accountNumber));
    }
}
