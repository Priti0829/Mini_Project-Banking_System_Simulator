package com.bean;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testAccount() {
        Account acc = new Account("ACC1", "John");
        acc.setBalance(500.0);

        assertEquals("ACC1", acc.getAccountNumber());
        assertEquals("John", acc.getAccountHolderName());
        assertEquals(500.0, acc.getBalance());
    }
}
