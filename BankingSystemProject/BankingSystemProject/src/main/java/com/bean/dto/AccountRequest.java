//package com.bean.dto;
//
//import jakarta.validation.constraints.NotBlank;
//
//public class AccountRequest {
//
//    @NotBlank(message = "Holder name is required")
//    private String holderName;
//
//    public AccountRequest() {}
//
//    public AccountRequest(String holderName) {
//        this.holderName = holderName;
//    }
//
//    public String getHolderName() {
//        return holderName;
//    }
//
//    public void setHolderName(String holderName) {
//        this.holderName = holderName;
//    }
//}



package com.bean.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AccountRequest {

    @NotBlank(message = "Holder name cannot be empty")
    @Size(min = 2, message = "Holder name must be at least 2 characters long")
    @Pattern(
            regexp = "^[A-Za-z ]+$",
            message = "Holder name must contain only alphabets and spaces"
    )
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
