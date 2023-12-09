package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Wallet {
    private String userId;
    private Double walletBalance;
    private Integer shareQuantity;
}
