package com.senai.betapi.service;

import com.senai.betapi.entity.Bet;
import com.senai.betapi.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BetService {

    @Autowired
    private BetRepository betRepository;
    private final Set<Integer> winningNumbers = new HashSet<>();

    public Bet createBet(Bet bet) throws Exception {
        if (bet.getBetNumbers().size() != 5) {
            throw new Exception("A aposta deve conter 5 números!");
        }
        for (Integer number : bet.getBetNumbers()) {
            if (number <= 0) {
                throw new Exception("Os números da aposta devem ser maiores que 0!");
            }
            if (number > 100) {
                throw new Exception("Os números da aposta devem ser menores que 100!");
            }
            if (bet.getUser() == null) {
                throw new Exception("O usuário da aposta não pode ser nulo!");
            }
        }
        return betRepository.addBet(bet);
    }

    public void removeBet(Integer id) throws Exception {
        try {
            betRepository.removeBet(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Bet> getBetList() {
        return betRepository.getBetList();
    }

    public Bet getBetById(Integer id) throws Exception {
        Bet bet = betRepository.getBetById(id);
        if (bet == null) {
            throw new Exception("A aposta não foi encontrada!");
        }
        return bet;
    }

    public Bet runIndividualGame(Bet bet) throws Exception {
        this.generateBetWinningNumbers();
        try {
            Bet betCreated = this.createBet(bet);
            boolean isWinner = betCreated.getBetNumbers().containsAll(winningNumbers);
            betCreated.setWinner(isWinner);
            return betCreated;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Bet> runGame() {
        this.generateBetWinningNumbers();
        for (Bet bet : betRepository.getBetList()) {
            if (checkIfBetIsWinner(bet)) {
                bet.setWinner(true);
            }
        }
        return betRepository.getBetList().stream().filter(Bet::isWinner).toList();
    }

    public void clearBetList() {
        betRepository.clearBetList();
    }

    private void generateBetWinningNumbers() {
        winningNumbers.clear();
        winningNumbers.add(1);
        winningNumbers.add(2);
        winningNumbers.add(3);
        winningNumbers.add(4);
        winningNumbers.add(5);
//        java.util.Random random = new java.util.Random();
//        while (winningNumbers.size() < 5) {
//            int randomNumber = random.nextInt(10) + 1; // Generates number between 1-10
//            winningNumbers.add(randomNumber);
//        }
    }

    private boolean checkIfBetIsWinner(Bet bet) {
        Set<Integer> betNumbers = bet.getBetNumbers();
        return betNumbers.containsAll(winningNumbers);
    }
}
