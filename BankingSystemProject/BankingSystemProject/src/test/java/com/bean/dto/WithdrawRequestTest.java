package com.bean.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WithdrawRequestTest {

    @Test
    void testGettersSetters() {
        WithdrawRequest req = new WithdrawRequest();
        req.setAmount(200.0);

        assertEquals(200.0, req.getAmount());
    }

    @Test
    void testConstructor() {
        WithdrawRequest req = new WithdrawRequest(700.0);

        assertEquals(700.0, req.getAmount());
    }
}
