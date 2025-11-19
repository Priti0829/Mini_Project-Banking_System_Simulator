package com.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleAccountNotFound() {
        AccountNotFoundException ex = new AccountNotFoundException("Account missing");

        ResponseEntity<Map<String, String>> response = handler.handleAccountNotFound(ex);

        assertEquals("Account Not Found", response.getBody().get("error"));
        assertEquals("Account missing", response.getBody().get("message"));
    }

    @Test
    void testHandleInsufficientBalance() {
        InsufficientBalanceException ex = new InsufficientBalanceException("Not enough balance");

        ResponseEntity<Map<String, String>> response = handler.handleInsufficientBalance(ex);

        assertEquals("Insufficient Balance", response.getBody().get("error"));
        assertEquals("Not enough balance", response.getBody().get("message"));
    }

    @Test
    void testHandleInvalidAmount() {
        InvalidAmountException ex = new InvalidAmountException("Invalid amount");

        ResponseEntity<Map<String, String>> response = handler.handleInvalidAmount(ex);

        assertEquals("Invalid Amount", response.getBody().get("error"));
        assertEquals("Invalid amount", response.getBody().get("message"));
    }

    @Test
    void testHandleValidationErrors() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("object", "field", "must not be blank");

        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(fieldError));

        ResponseEntity<Map<String, String>> response = handler.handleValidationErrors(ex);

        assertEquals("must not be blank", response.getBody().get("field"));
    }

    @Test
    void testHandleGenericException() {
        Exception ex = new Exception("Something went wrong");

        ResponseEntity<Map<String, String>> response = handler.handleGenericException(ex);

        assertEquals("Internal Server Error", response.getBody().get("error"));
        assertEquals("Something went wrong", response.getBody().get("message"));
    }
}
