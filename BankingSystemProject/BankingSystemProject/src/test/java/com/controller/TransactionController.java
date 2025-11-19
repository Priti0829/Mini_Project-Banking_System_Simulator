package com.controller;

import com.bean.Transaction;
import com.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    private Transaction txn1;
    private Transaction txn2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        txn1 = new Transaction(
                "TXN-001",
                "DEPOSIT",
                500.0,
                new Date(),
                "SUCCESS",
                "ACC1234",
                null
        );

        txn2 = new Transaction(
                "TXN-002",
                "TRANSFER",
                300.0,
                new Date(),
                "SUCCESS",
                "ACC1234",
                "ACC5678"
        );
    }

    @Test
    void testGetTransactionsByAccount() {
        when(transactionService.getTransactionsByAccount("ACC1234"))
                .thenReturn(Arrays.asList(txn1, txn2));

        ResponseEntity<List<Transaction>> response = transactionController.getTransactions("ACC1234");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().contains(txn1));
        assertTrue(response.getBody().contains(txn2));

        verify(transactionService, times(1)).getTransactionsByAccount("ACC1234");
    }

    @Test
    void testGetTransactionsByAccountEmpty() {
        when(transactionService.getTransactionsByAccount("ACC9999"))
                .thenReturn(Arrays.asList());

        ResponseEntity<List<Transaction>> response = transactionController.getTransactions("ACC9999");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());

        verify(transactionService, times(1)).getTransactionsByAccount("ACC9999");
    }
}
