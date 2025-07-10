package com.senai.userwalletapi.dto;

import com.senai.userwalletapi.entity.User;
import lombok.Data;

@Data
public class PaymentDto {
    private User user;
    private Double paymentValue;
}
