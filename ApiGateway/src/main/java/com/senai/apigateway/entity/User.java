package com.senai.apigateway.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;
    private String nome;
    private String email;
}
