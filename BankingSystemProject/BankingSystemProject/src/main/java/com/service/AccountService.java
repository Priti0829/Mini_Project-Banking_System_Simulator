package com.service;

import com.bean.Account;
import com.bean.Transaction;
import com.bean.dto.*;
import com.exception.AccountNotFoundException;
import com.exception.InsufficientBalanceException;
import com.exception.InvalidAmountException;
import com.repository.AccountRepository;
import com.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public AccountResponseDto createAccount(AccountRequest request) {
        String accountNumber = generateAccountNumber(request.getHolderName());
        Account account = new Account(accountNumber, request.getHolderName());
        accountRepository.save(account);
        return mapToDTO(account);
    }

    public List<AccountResponseDto> getAllAccounts() {
        return accountRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public AccountResponseDto getAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
        return mapToDTO(account);
    }

    public AccountResponseDto updateAccount(String accountNumber, AccountRequest request) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));

        account.setHolderName(request.getHolderName());
        accountRepository.save(account);
        logger.info("Updated account: {}", accountNumber);
        return mapToDTO(account);
    }

    public void deleteAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
        accountRepository.delete(account);
    }

    public TransactionResponseDto deposit(String accountNumber, DepositRequest request) {
        if (request.getAmount() <= 0)
            throw new InvalidAmountException("Deposit amount must be greater than Zero");

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.setBalance(account.getBalance() + request.getAmount());
        accountRepository.save(account);

        Transaction txn = new Transaction(generateTransactionId(), "DEPOSIT", request.getAmount(), new Date(), "SUCCESS", accountNumber, null);
        transactionRepository.save(txn);
        account.getTransactions().add(txn);
        accountRepository.save(account);

        return mapToDTO(txn);
    }

    public TransactionResponseDto withdraw(String accountNumber, WithdrawRequest request) {
        if (request.getAmount() <= 0)
            throw new InvalidAmountException("Withdraw amount must be greater than 0");

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        if (account.getBalance() < request.getAmount())
            throw new InsufficientBalanceException("Insufficient balance");

        account.setBalance(account.getBalance() - request.getAmount());
        accountRepository.save(account);

        Transaction txn = new Transaction(generateTransactionId(), "WITHDRAW", request.getAmount(), new Date(), "SUCCESS", accountNumber, null);
        transactionRepository.save(txn);
        account.getTransactions().add(txn);
        accountRepository.save(account);

        return mapToDTO(txn);
    }

    @Transactional
    public TransactionResponseDto transfer(TransferRequest request) {
        if (request.getAmount() <= 0) throw new InvalidAmountException("Transfer amount must be greater than 0");
        if (request.getFromAccount().equals(request.getToAccount())) throw new InvalidAmountException("Cannot transfer to same account");

        Account source = accountRepository.findByAccountNumber(request.getFromAccount())
                .orElseThrow(() -> new AccountNotFoundException("Source account not found"));

        Account destination = accountRepository.findByAccountNumber(request.getToAccount())
                .orElseThrow(() -> new AccountNotFoundException("Destination account not found"));


        // Uncomment below block if you want to allow self-transfer
    /*
    if (request.getFromAccount().equals(request.getToAccount())) {
        Transaction txn = new Transaction(generateTransactionId(), "TRANSFER", request.getAmount(), new Date(), "SUCCESS", request.getFromAccount(), request.getToAccount());
        transactionRepository.save(txn);
        source.getTransactions().add(txn);
        accountRepository.save(source);
        return mapToDTO(txn);
    }
    */

        if (source.getBalance() < request.getAmount()) throw new InsufficientBalanceException("Insufficient balance");

        source.setBalance(source.getBalance() - request.getAmount());
        destination.setBalance(destination.getBalance() + request.getAmount());

        accountRepository.save(source);
        accountRepository.save(destination);

        Transaction txn = new Transaction(generateTransactionId(), "TRANSFER", request.getAmount(), new Date(), "SUCCESS", request.getFromAccount(), request.getToAccount());
        transactionRepository.save(txn);

        source.getTransactions().add(txn);
        destination.getTransactions().add(txn);

        accountRepository.save(source);
        accountRepository.save(destination);

        return mapToDTO(txn);
    }

    public List<TransactionResponseDto> getTransactions(String accountNumber) {
        return transactionRepository.findByFromAccountOrToAccount(accountNumber, accountNumber)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private String generateAccountNumber(String holderName) {
        String initials = holderName.length() >= 3 ? holderName.substring(0, 3).toUpperCase() : holderName.toUpperCase();
        return initials + (new Random().nextInt(9000) + 1000);
    }

    private String generateTransactionId() {
        return "TXN-" + new Date().getTime();
    }

    private AccountResponseDto mapToDTO(Account account) {
        return new AccountResponseDto( account.getId(),  account.getAccountNumber(), account.getHolderName(), account.getBalance(), account.getStatus(), account.getCreatedAt());
    }

    private TransactionResponseDto mapToDTO(Transaction txn) {
        return new TransactionResponseDto( txn.getId(),txn.getTransactionId(), txn.getType(), txn.getAmount(), txn.getTimestamp(), txn.getStatus(), txn.getFromAccount(), txn.getToAccount());
    }
}
