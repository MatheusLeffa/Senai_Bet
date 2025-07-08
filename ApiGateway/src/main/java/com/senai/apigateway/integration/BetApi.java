package com.senai.apigateway.integration;

import com.senai.apigateway.entity.Bet;
import com.senai.apigateway.entity.ResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "BetApi", url = "http://localhost:8081")
public interface BetApi {
    @GetMapping(value = "/bets", produces = "application/json")
    public ResponseEntity<ResponseObject> getBets();

    @GetMapping(value = "/bets/{id}", produces = "application/json")
    public ResponseEntity<ResponseObject> getBetById(@PathVariable Integer id);

    @PostMapping(value = "/bets/create", produces = "application/json")
    public ResponseEntity<ResponseObject> createBet(@RequestBody Bet bet);

    @DeleteMapping(value = "/bets/remove", produces = "application/json")
    public ResponseEntity<ResponseObject> removeBet(@RequestBody Integer id);

    @PostMapping(value = "/bets/run-individual-game", produces = "application/json")
    public ResponseEntity<ResponseObject> runIndividualGame(@RequestBody Integer id);

    @PostMapping(value = "/bets/run-game", produces = "application/json")
    public ResponseEntity<ResponseObject> runGame();

    @PostMapping(value = "/bets/reset-game", produces = "application/json")
    public ResponseEntity<ResponseObject> resetGame();
}
