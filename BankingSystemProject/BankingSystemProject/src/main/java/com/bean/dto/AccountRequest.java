package com.bean.dto;

import jakarta.validation.constraints.NotBlank;

public class AccountRequest {

    @NotBlank(message = "Holder name is required")
    private String holderName;

    public AccountRequest() {}

    public AccountRequest(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
