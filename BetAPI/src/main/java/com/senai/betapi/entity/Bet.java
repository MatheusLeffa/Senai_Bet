package com.senai.betapi.entity;

import lombok.Data;

import java.util.List;

@Data
public class Bet {
    private Integer id;
    private List<Integer> betNumbers;
    private boolean isWinner;
}
