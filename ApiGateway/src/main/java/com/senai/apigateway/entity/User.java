package com.senai.apigateway.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private boolean isAllowedToBet;
}
