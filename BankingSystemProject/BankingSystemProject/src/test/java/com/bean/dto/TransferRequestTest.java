package com.bean.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransferRequestTest {

    @Test
    void testGettersSetters() {
        TransferRequest req = new TransferRequest();
        req.setFromAccount("A1");
        req.setToAccount("A2");
        req.setAmount(500.0);

        assertEquals("A1", req.getFromAccount());
        assertEquals("A2", req.getToAccount());
        assertEquals(500.0, req.getAmount());
    }

    @Test
    void testConstructor() {
        TransferRequest req = new TransferRequest("A1", "A2", 300.0);

        assertEquals("A1", req.getFromAccount());
        assertEquals("A2", req.getToAccount());
        assertEquals(300.0, req.getAmount());
    }
}
