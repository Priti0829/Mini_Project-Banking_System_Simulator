package com.service;

import com.bean.Account;
import com.bean.Transaction;
import com.bean.dto.*;
import com.exception.AccountNotFoundException;
import com.exception.InsufficientBalanceException;
import com.exception.InvalidAmountException;
import com.repository.AccountRepository;
import com.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    private Account account1;
    private Account account2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        account1 = new Account("ACC1234", "John Doe");
        account1.setBalance(1000.0);

        account2 = new Account("ACC5678", "Jane Smith");
        account2.setBalance(500.0);
    }

    // ---------------- CREATE ACCOUNT ----------------
    @Test
    void testCreateAccount() {
        AccountRequest request = new AccountRequest("Alice");

        when(accountRepository.save(any(Account.class)))
                .thenAnswer(i -> {
                    Account saved = (Account) i.getArguments()[0];
                    saved.setAccountNumber("ALI1234");
                    return saved;
                });

        AccountResponseDto response = accountService.createAccount(request);

        assertNotNull(response);
        assertEquals("Alice", response.getHolderName());
        assertEquals("ALI1234", response.getAccountNumber());
    }

    // ---------------- GET ACCOUNT ----------------
    @Test
    void testGetAccountSuccess() {
        when(accountRepository.findByAccountNumber("ACC1234"))
                .thenReturn(Optional.of(account1));

        AccountResponseDto dto = accountService.getAccount("ACC1234");

        assertEquals("John Doe", dto.getHolderName());
        assertEquals(1000.0, dto.getBalance());
    }

    @Test
    void testGetAccountNotFound() {
        when(accountRepository.findByAccountNumber("ACC9999"))
                .thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> accountService.getAccount("ACC9999"));
    }

    // ---------------- DEPOSIT ----------------
    @Test
    void testDepositSuccess() {
        DepositRequest request = new DepositRequest(500.0);

        when(accountRepository.findByAccountNumber("ACC1234"))
                .thenReturn(Optional.of(account1));

        when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        TransactionResponseDto txn = accountService.deposit("ACC1234", request);

        assertEquals("DEPOSIT", txn.getType());
        assertEquals(1500.0, account1.getBalance());
    }

    @Test
    void testDepositInvalidAmount() {
        DepositRequest request = new DepositRequest(-100.0);

        assertThrows(InvalidAmountException.class,
                () -> accountService.deposit("ACC1234", request));
    }

    // ---------------- WITHDRAW ----------------
    @Test
    void testWithdrawSuccess() {
        WithdrawRequest request = new WithdrawRequest(500.0);

        when(accountRepository.findByAccountNumber("ACC1234"))
                .thenReturn(Optional.of(account1));

        when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        TransactionResponseDto txn = accountService.withdraw("ACC1234", request);

        assertEquals("WITHDRAW", txn.getType());
        assertEquals(500.0, account1.getBalance());
    }

    @Test
    void testWithdrawInsufficientBalance() {
        WithdrawRequest request = new WithdrawRequest(2000.0);

        when(accountRepository.findByAccountNumber("ACC1234"))
                .thenReturn(Optional.of(account1));

        assertThrows(InsufficientBalanceException.class,
                () -> accountService.withdraw("ACC1234", request));
    }

    // ---------------- TRANSFER ----------------
    @Test
    void testTransferSuccess() {
        TransferRequest request = new TransferRequest("ACC1234", "ACC5678", 300.0);

        when(accountRepository.findByAccountNumber("ACC1234"))
                .thenReturn(Optional.of(account1));
        when(accountRepository.findByAccountNumber("ACC5678"))
                .thenReturn(Optional.of(account2));

        when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        TransactionResponseDto txn = accountService.transfer(request);

        assertEquals("TRANSFER", txn.getType());
        assertEquals(700.0, account1.getBalance());
        assertEquals(800.0, account2.getBalance());
    }

    @Test
    void testTransferInsufficientBalance() {
        TransferRequest request = new TransferRequest("ACC1234", "ACC5678", 2000.0);

        when(accountRepository.findByAccountNumber("ACC1234"))
                .thenReturn(Optional.of(account1));
        when(accountRepository.findByAccountNumber("ACC5678"))
                .thenReturn(Optional.of(account2));

        assertThrows(InsufficientBalanceException.class,
                () -> accountService.transfer(request));
    }

    @Test
    void testTransferAccountNotFound() {
        TransferRequest request = new TransferRequest("ACC1234", "ACC9999", 100.0);

        when(accountRepository.findByAccountNumber("ACC1234"))
                .thenReturn(Optional.of(account1));
        when(accountRepository.findByAccountNumber("ACC9999"))
                .thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> accountService.transfer(request));
    }
}

