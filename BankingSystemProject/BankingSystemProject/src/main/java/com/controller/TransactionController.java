package com.controller;

import com.bean.Transaction;
import com.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Get all transactions for a given account
    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByAccount(accountNumber);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
