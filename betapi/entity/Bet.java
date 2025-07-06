package com.senai.betapi.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class Bet {
    private Integer id;
    private User user;
    private Set<Integer> betNumbers = new HashSet<>();
    private boolean isWinner;
}
