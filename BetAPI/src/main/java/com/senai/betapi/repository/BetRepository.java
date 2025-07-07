package com.senai.betapi.repository;

import com.senai.betapi.entity.Bet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BetRepository {
    private final static List<Bet> betList = new ArrayList<>();
    private static Integer id = 0;

    public Bet addBet(Bet bet) {
        bet.setId(++id);
        betList.add(bet);
        return bet;
    }

    public void removeBet(Integer id) throws Exception {
        if (!betList.removeIf(bet -> bet.getId().equals(id))) {
            throw new Exception("A aposta não foi encontrada!");
        }
    }

    public List<Bet> getBetList() {
        return betList;
    }

    public void resetBetList() {
        betList.clear();
    }
}
