package com.bean.dto;

import java.util.Date;

public class TransactionResponseDto {
    private String id;
    private String transactionId;
    private String type;
    private double amount;
    private Date timestamp;
    private String status;
    private String fromAccount;
    private String toAccount;

    public TransactionResponseDto() {}

    public TransactionResponseDto(String id,String transactionId, String type, double amount,
                                  Date timestamp, String status,
                                  String fromAccount, String toAccount) {
        this.id=id;
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public String getId() { return id; }
    public String getTransactionId() { return transactionId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public Date getTimestamp() { return timestamp; }
    public String getStatus() { return status; }
    public String getFromAccount() { return fromAccount; }
    public String getToAccount() { return toAccount; }

    public void setId(String id) { this.id = id; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public void setType(String type) { this.type = type; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public void setStatus(String status) { this.status = status; }
    public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }
    public void setToAccount(String toAccount) { this.toAccount = toAccount; }
}
