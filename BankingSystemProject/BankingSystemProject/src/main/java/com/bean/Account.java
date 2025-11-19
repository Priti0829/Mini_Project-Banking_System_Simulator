package com.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "accounts")
public class Account {
    @Id
    private String id;
    private String accountNumber;
    private String holderName;
    private double balance;
    private String status;
    private Date createdAt;
    private List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<>();
        this.balance = 0;
        this.status = "ACTIVE";
        this.createdAt = new Date();
    }

    public Account(String accountNumber, String holderName) {
        this();
        this.accountNumber = accountNumber;
        this.holderName = holderName;
    }

    // getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }

    public String getAccountHolderName() {
        return this.holderName;
    }
}
