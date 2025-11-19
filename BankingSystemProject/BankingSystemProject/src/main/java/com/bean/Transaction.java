package com.bean;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class Transaction {
    @Id
    private String id;
    private String transactionId;
    private String type;
    private double amount;
    private String status;
    private String fromAccount;
    private String toAccount;
    private Date timestamp;

    public Transaction() {}

    // Main constructor used by your service
    public Transaction(String transactionId,
                       String type,
                       double amount,
                       Date timestamp,
                       String status,
                       String fromAccount,
                       String toAccount) {

        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    // Getters/setters
    public String getTransactionId() { return transactionId; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getFromAccount() { return fromAccount; }
    public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }

    public String getToAccount() { return toAccount; }
    public void setToAccount(String toAccount) { this.toAccount = toAccount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDestinationAccount() {
        return toAccount;
    }
}
