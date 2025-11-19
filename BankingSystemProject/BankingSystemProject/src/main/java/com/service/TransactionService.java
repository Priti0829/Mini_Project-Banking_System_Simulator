package com.service;

import com.bean.Transaction;
import com.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsByAccount(String accountNumber) {
        return transactionRepository.findByFromAccountOrToAccount(accountNumber, accountNumber);
    }
}
