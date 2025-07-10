package com.senai.userwalletapi.dto;

import com.senai.userwalletapi.entity.User;
import lombok.Data;

@Data
public class PaymentDto {
    private Integer userId;
    private Double paymentValue;
}
