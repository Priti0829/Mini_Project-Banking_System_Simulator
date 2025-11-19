package com.bean.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepositRequestTest {

    @Test
    void testGettersSetters() {
        DepositRequest req = new DepositRequest();
        req.setAmount(500.0);

        assertEquals(500.0, req.getAmount());
    }

    @Test
    void testConstructor() {
        DepositRequest req = new DepositRequest(300.0);

        assertEquals(300.0, req.getAmount());
    }
}
