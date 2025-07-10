package com.senai.frontend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class NewBetDto {
    @NotNull(message = "User ID is required")
    private Integer userId;

    @Size(min = 1, message = "At least one number is required")
    private Set<Integer> betNumbers = new HashSet<>();
}
