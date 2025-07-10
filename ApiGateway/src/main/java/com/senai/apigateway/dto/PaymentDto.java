package com.senai.apigateway.dto;

import com.senai.apigateway.entity.User;
import lombok.Data;

@Data
public class PaymentDto {
    private Integer userId;
    private Double paymentValue;
}
