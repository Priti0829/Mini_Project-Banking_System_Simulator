package com.bean;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    @Test
    void testNoArgsConstructor() {
        Transaction transaction = new Transaction();
        assertNull(transaction.getTransactionId());
        assertNull(transaction.getType());
        assertEquals(0, transaction.getAmount());
        assertNull(transaction.getStatus());
        assertNull(transaction.getFromAccount());
        assertNull(transaction.getToAccount());
        assertNull(transaction.getTimestamp());
    }

    @Test
    void testAllArgsConstructor() {
        String id = "TXN123";
        String type = "TRANSFER";
        double amount = 500.0;
        Date timestamp = new Date(); // pass timestamp explicitly
        String status = "SUCCESS";
        String from = "ACC1001";
        String to = "ACC1002";

        Transaction transaction = new Transaction(id, type, amount, timestamp, status, from, to);

        assertEquals(id, transaction.getTransactionId());
        assertEquals(type, transaction.getType());
        assertEquals(amount, transaction.getAmount());
        assertEquals(status, transaction.getStatus());
        assertEquals(from, transaction.getFromAccount());
        assertEquals(to, transaction.getToAccount());
        assertEquals(timestamp, transaction.getTimestamp()); // check timestamp
    }

    @Test
    void testSettersAndGetters() {
        Transaction transaction = new Transaction();

        transaction.setTransactionId("TXN999");
        transaction.setType("DEPOSIT");
        transaction.setAmount(1000);
        transaction.setStatus("PENDING");
        transaction.setFromAccount("ACC2001");
        transaction.setToAccount("ACC2002");
        Date now = new Date();
        transaction.setTimestamp(now);

        assertEquals("TXN999", transaction.getTransactionId());
        assertEquals("DEPOSIT", transaction.getType());
        assertEquals(1000, transaction.getAmount());
        assertEquals("PENDING", transaction.getStatus());
        assertEquals("ACC2001", transaction.getFromAccount());
        assertEquals("ACC2002", transaction.getToAccount());
        assertEquals(now, transaction.getTimestamp());
    }

    @Test
    void testGetDestinationAccount() {
        Transaction transaction = new Transaction();
        transaction.setToAccount("ACC3001");
        assertEquals("ACC3001", transaction.getDestinationAccount());
    }
}
