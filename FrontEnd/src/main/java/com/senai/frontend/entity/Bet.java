package com.senai.frontend.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Bet {
    private Integer id;
    private User user;
    private Set<Integer> betNumbers = new HashSet<>();
    private boolean isWinner;
}
