package com.senai.frontend.dto;

import lombok.Data;

@Data
public class NewUserDto {
    private String name;
    private String email;
    private Integer age;
    private boolean isAllowedToBet;
}
