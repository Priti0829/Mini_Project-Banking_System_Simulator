package com.bean.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DepositRequest {

    @NotNull(message = "Deposit amount is required")
    @Min(value = 1, message = "Deposit amount must be greater than zero")
    private Double amount;   // Use Double instead of double

    public DepositRequest() {}

    public DepositRequest(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
