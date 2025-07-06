package com.senai.apigateway.integration;

import com.senai.apigateway.entity.Bet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "BetApi", url = "http://localhost:8081")
public interface BetApi {
    @GetMapping("/bets")
    public ResponseEntity<List<Bet>> getBets();

    @PostMapping("/create-bet")
    public ResponseEntity<String> createBet(@RequestBody Bet bet);

    @DeleteMapping("/remove-bet")
    public ResponseEntity<String> removeBet(@RequestBody Integer id);

    @PostMapping("/run-game")
    public ResponseEntity<List<Bet>> runGame();

    @PostMapping("/reset-game")
    public ResponseEntity<String> resetGame();
}
