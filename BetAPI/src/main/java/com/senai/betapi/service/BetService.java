package com.senai.betapi.service;

import com.senai.betapi.entity.Bet;
import com.senai.betapi.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetService {

    @Autowired
    private BetRepository betRepository;

    public boolean createBet(Bet bet) throws Exception {
        if (bet.getBetNumbers().size() != 5) {
            throw new Exception("A aposta deve conter 5 números!");
        }
        for (Integer number : bet.getBetNumbers()) {
            if (number <= 0) {
                throw new Exception("Os números da aposta devem ser maiores que 0!");
            }
        }
        return this.saveBet(bet);
    }

    public boolean removeBet(Integer id) throws Exception {
        try {
            return betRepository.removeBet(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean saveBet(Bet bet) throws Exception {
        try {
            return betRepository.addBet(bet);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
