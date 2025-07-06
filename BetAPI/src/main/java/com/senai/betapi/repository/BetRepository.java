package com.senai.betapi.repository;

import com.senai.betapi.entity.Bet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BetRepository {

    private final static List<Integer> WinningNumbers = new ArrayList<>();
    private final static List<Bet> BetList = new ArrayList<>();

    public void generateBetWinningNumbers() {
        WinningNumbers.clear();
        for (int i = 1; i <= 5; i++) {
            WinningNumbers.add(i);
        }
    }

    public List<Integer> getWinningNumbers() {
        return WinningNumbers;
    }

    public List<Bet> getBetList() {
        return BetList;
    }

    public boolean addBet(Bet bet) {
        return BetList.add(bet);
    }

    public boolean removeBet(Integer id) throws Exception {
        if(BetList.removeIf(bet -> bet.getId().equals(id))){
            return true;
        } else {
            throw new Exception("A aposta não foi encontrada!");
        }
    }
}
