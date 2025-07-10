package com.senai.userwalletapi.entity;

import lombok.Data;

@Data
public class Wallet {
    private User user;
    private Double balance;
}
