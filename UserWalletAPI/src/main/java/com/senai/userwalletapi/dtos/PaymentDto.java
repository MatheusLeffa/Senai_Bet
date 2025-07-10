package com.senai.userwalletapi.dtos;

import com.senai.userwalletapi.entity.User;
import lombok.Data;

@Data
public class PaymentDto {
    private User user;
    private Double paymentValue;
}
