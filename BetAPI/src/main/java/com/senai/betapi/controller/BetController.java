package com.senai.betapi.controller;

import com.senai.betapi.entity.Bet;
import com.senai.betapi.service.BetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Bets", description = "Bets API")
@Controller
//@CrossOrigin(origins = "http://localhost:9005")
public class BetController {

    @Autowired
    private BetService betService;

    @GetMapping("/bets")
    public ResponseEntity<List<Bet>> getBets() {
        return ResponseEntity.ok(betService.getBetList());
    }

    @PostMapping("/create-bet")
    public ResponseEntity<String> createBet(@RequestBody Bet bet) throws Exception {
        try {
            Integer id = betService.createBet(bet);
            return ResponseEntity.ok("Aposta criada com sucesso! ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/remove-bet")
    public ResponseEntity<String> removeBet(@RequestBody Integer id) throws Exception {
        try {
            betService.removeBet(id);
            return ResponseEntity.ok("Aposta removida com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/run-game")
    public ResponseEntity<List<Bet>> runGame() {
        List<Bet> winningBets = betService.runGame();
        return ResponseEntity.ok(winningBets);
    }

    @PostMapping("/reset-game")
    public ResponseEntity<String> resetGame() {
        betService.resetGame();
        return ResponseEntity.ok("Jogo resetado com sucesso!");
    }
}
