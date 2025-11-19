package com.bean.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class WithdrawRequest {

    @NotNull(message = "Withdraw amount is required")
    @Min(value = 1, message = "Withdraw amount must be greater than zero")
    private Double amount;

    public WithdrawRequest() {}

    public WithdrawRequest(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
