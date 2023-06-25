package com.example.demo1.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BankAccountVerificationRequest {
    private String memberKey;
    private String password;
    private String accountNumber;
    private Long idNumber;
    private String accountType;
    private String branchCode;
    private String yourReference;
}
