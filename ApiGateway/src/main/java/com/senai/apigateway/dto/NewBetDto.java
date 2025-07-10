package com.senai.apigateway.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class NewBetDto {
    private Integer userId;
    private Set<Integer> betNumbers = new HashSet<>();
}
