package com.bean.dto;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AccountResponseDtoTest {

    @Test
    void testGettersAndSetters() {
        Date now = new Date();
        AccountResponseDto dto = new AccountResponseDto();

        dto.setId("ID1234");
        dto.setAccountNumber("ACC1234");
        dto.setHolderName("John Doe");
        dto.setBalance(1000.0);
        dto.setStatus("ACTIVE");
        dto.setCreatedAt(now);

        assertEquals("ID1234", dto.getId());
        assertEquals("ACC1234", dto.getAccountNumber());
        assertEquals("John Doe", dto.getHolderName());
        assertEquals(1000.0, dto.getBalance());
        assertEquals("ACTIVE", dto.getStatus());
        assertEquals(now, dto.getCreatedAt());
    }

    @Test
    void testAllArgsConstructor() {
        Date now = new Date();
        AccountResponseDto dto = new AccountResponseDto(
                "ID5678",
                "ACC5678",
                "Jane",
                2000.0,
                "ACTIVE",
                now
        );

        assertEquals("ID5678", dto.getId());
        assertEquals("ACC5678", dto.getAccountNumber());
        assertEquals("Jane", dto.getHolderName());
        assertEquals(2000.0, dto.getBalance());
        assertEquals("ACTIVE", dto.getStatus());
        assertEquals(now, dto.getCreatedAt());
    }
}
