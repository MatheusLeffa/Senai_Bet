package com.senai.apigateway.dto;

import lombok.Data;

@Data
public class NewUserDto {
    private String name;
    private String email;
    private Integer age;
    private boolean isAllowedToBet;
}
