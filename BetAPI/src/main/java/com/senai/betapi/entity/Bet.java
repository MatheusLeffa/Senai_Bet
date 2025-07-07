package com.senai.betapi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
public class Bet {
    private Integer id;
    private User user;
    private Set<Integer> betNumbers = new HashSet<>();
    private boolean isWinner;
}
