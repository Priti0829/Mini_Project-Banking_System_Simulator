package com.bean.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransferRequest {

    @NotBlank(message = "From account number is required")
    private String fromAccount;

    @NotBlank(message = "To account number is required")
    private String toAccount;

    @NotNull(message = "Transfer amount is required")
    @Min(value = 1, message = "Transfer amount must be greater than zero")
    private Double amount;


    public TransferRequest() {}

    public TransferRequest(String fromAccount, String toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public String getFromAccount() { return fromAccount; }
    public void setFromAccount(String fromAccount) { this.fromAccount = fromAccount; }

    public String getToAccount() { return toAccount; }
    public void setToAccount(String toAccount) { this.toAccount = toAccount; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}


