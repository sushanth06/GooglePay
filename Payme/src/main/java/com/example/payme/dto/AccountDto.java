package com.example.payme.dto;

import lombok.Data;

@Data
public class AccountDto {
    private long accountBalance;
    private String accountType;
    private Long accountID;

    public AccountDto(long accountBalance, String accountType, Long accountID) {
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.accountID = accountID;
    }
}
