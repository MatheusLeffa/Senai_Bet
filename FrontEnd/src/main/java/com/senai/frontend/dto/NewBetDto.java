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

    @Size(min = 5, message = "You must choose at least 5 distinct numbers")
    @Size(max = 5, message = "You must choose at least 5 distinct numbers")
    private Set<Integer> betNumbers = new HashSet<>();
}
