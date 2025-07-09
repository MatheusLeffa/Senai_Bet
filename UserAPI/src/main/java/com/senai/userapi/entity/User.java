package com.senai.userapi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private Integer id;
    private String name;
    private String email;
    private Integer age;
    private boolean isAllowedToBet;
}
