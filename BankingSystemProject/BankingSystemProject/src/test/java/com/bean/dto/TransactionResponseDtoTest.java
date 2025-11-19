package com.bean.dto;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionResponseDtoTest {

    @Test
    void testGettersSetters() {
        Date now = new Date();

        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setId("ID1"); // set id
        dto.setTransactionId("TXN1");
        dto.setType("DEPOSIT");
        dto.setAmount(500.0);
        dto.setTimestamp(now);
        dto.setStatus("SUCCESS");
        dto.setFromAccount("ACC1");
        dto.setToAccount("ACC2");

        assertEquals("ID1", dto.getId());
        assertEquals("TXN1", dto.getTransactionId());
        assertEquals("DEPOSIT", dto.getType());
        assertEquals(500.0, dto.getAmount());
        assertEquals(now, dto.getTimestamp());
        assertEquals("SUCCESS", dto.getStatus());
        assertEquals("ACC1", dto.getFromAccount());
        assertEquals("ACC2", dto.getToAccount());
    }

    @Test
    void testAllArgsConstructor() {
        Date now = new Date();

        TransactionResponseDto dto = new TransactionResponseDto(
                "ID10",          // id
                "T10",
                "TRANSFER",
                900.0,
                now,
                "SUCCESS",
                "ACC1",
                "ACC2"
        );

        assertEquals("ID10", dto.getId());
        assertEquals("T10", dto.getTransactionId());
        assertEquals("TRANSFER", dto.getType());
        assertEquals(900.0, dto.getAmount());
        assertEquals(now, dto.getTimestamp());
        assertEquals("SUCCESS", dto.getStatus());
        assertEquals("ACC1", dto.getFromAccount());
        assertEquals("ACC2", dto.getToAccount());
    }
}
