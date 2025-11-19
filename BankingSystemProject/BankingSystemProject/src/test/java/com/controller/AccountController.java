package com.controller;

import com.bean.dto.*;
import com.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private AccountResponseDto accountDto;
    private TransactionResponseDto txnDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        accountDto = new AccountResponseDto(

                "ID1234",
                "ACC1234",
                "John Doe",
                1000.0,
                "ACTIVE",
                null
        );

        txnDto = new TransactionResponseDto(
                "IDTXN1",
                "TXN1",
                "DEPOSIT",
                500.0,
                null,
                "SUCCESS",
                "ACC1234",
                null
        );
    }

    // -------------------- CREATE ACCOUNT --------------------
    @Test
    void testCreateAccount() {
        AccountRequest request = new AccountRequest("John Doe");
        when(accountService.createAccount(request)).thenReturn(accountDto);

        ResponseEntity<AccountResponseDto> response = accountController.createAccount(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
    }

    // -------------------- GET ACCOUNT --------------------
    @Test
    void testGetAccount() {
        when(accountService.getAccount("ACC1234")).thenReturn(accountDto);

        ResponseEntity<AccountResponseDto> response = accountController.getAccount("ACC1234");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
    }

    // -------------------- UPDATE ACCOUNT --------------------
    @Test
    void testUpdateAccount() {
        AccountRequest request = new AccountRequest("John Updated");
        when(accountService.updateAccount("ACC1234", request)).thenReturn(accountDto);

        ResponseEntity<AccountResponseDto> response = accountController.updateAccount("ACC1234", request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accountDto, response.getBody());
    }

    // -------------------- DELETE ACCOUNT --------------------
    @Test
    void testDeleteAccount() {
        doNothing().when(accountService).deleteAccount("ACC1234");

        ResponseEntity<Void> response = accountController.deleteAccount("ACC1234");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    // -------------------- DEPOSIT --------------------
    @Test
    void testDeposit() {
        DepositRequest request = new DepositRequest(500.0);
        when(accountService.deposit("ACC1234", request)).thenReturn(txnDto);

        ResponseEntity<TransactionResponseDto> response = accountController.deposit("ACC1234", request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(txnDto, response.getBody());
    }

    // -------------------- WITHDRAW --------------------
    @Test
    void testWithdraw() {
        WithdrawRequest request = new WithdrawRequest(200.0);
        when(accountService.withdraw("ACC1234", request)).thenReturn(txnDto);

        ResponseEntity<TransactionResponseDto> response = accountController.withdraw("ACC1234", request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(txnDto, response.getBody());
    }

    // -------------------- TRANSFER --------------------
    @Test
    void testTransfer() {
        TransferRequest request = new TransferRequest("ACC1234", "ACC5678", 300.0);
        when(accountService.transfer(request)).thenReturn(txnDto);

        ResponseEntity<TransactionResponseDto> response = accountController.transfer(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(txnDto, response.getBody());
    }

    // -------------------- GET TRANSACTIONS --------------------
    @Test
    void testGetTransactions() {
        List<TransactionResponseDto> list = List.of(txnDto);

        when(accountService.getTransactions("ACC1234")).thenReturn(list);

        ResponseEntity<List<TransactionResponseDto>> response = accountController.getTransactions("ACC1234");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }
}
