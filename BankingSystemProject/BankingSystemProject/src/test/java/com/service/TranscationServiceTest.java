package com.service;

import com.bean.Transaction;
import com.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    private Transaction txn1;
    private Transaction txn2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        txn1 = new Transaction(
                "TXN-001",
                "DEPOSIT",
                500,
                new Date(),
                "SUCCESS",
                "ACC1234",
                null
        );

        txn2 = new Transaction(
                "TXN-002",
                "TRANSFER",
                300,
                new Date(),
                "SUCCESS",
                "ACC1234",
                "ACC5678"
        );
    }

    @Test
    void testGetTransactionsByAccount() {
        when(transactionRepository.findByFromAccountOrToAccount("ACC1234", "ACC1234"))
                .thenReturn(Arrays.asList(txn1, txn2));

        List<Transaction> transactions = transactionService.getTransactionsByAccount("ACC1234");

        assertEquals(2, transactions.size());
        assertTrue(transactions.contains(txn1));
        assertTrue(transactions.contains(txn2));

        verify(transactionRepository, times(1))
                .findByFromAccountOrToAccount("ACC1234", "ACC1234");
    }

    @Test
    void testGetTransactionsByAccountEmpty() {
        when(transactionRepository.findByFromAccountOrToAccount("ACC9999", "ACC9999"))
                .thenReturn(Arrays.asList());

        List<Transaction> transactions = transactionService.getTransactionsByAccount("ACC9999");

        assertTrue(transactions.isEmpty());

        verify(transactionRepository, times(1))
                .findByFromAccountOrToAccount("ACC9999", "ACC9999");
    }
}
