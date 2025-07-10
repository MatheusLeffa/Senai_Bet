package com.senai.apigateway.dtos;

import com.senai.apigateway.entity.User;
import lombok.Data;

@Data
public class PaymentDto {
    private User user;
    private Double paymentValue;
}
