package com.senai.apigateway.controller;

import com.senai.apigateway.entity.Bet;
import com.senai.apigateway.entity.ResponseObject;
import com.senai.apigateway.integration.BetApi;
import com.senai.apigateway.integration.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BetController {

    @Autowired
    private BetApi betApi;

    @Autowired
    private UserApi userApi;

    @GetMapping(value = "/bets", produces = "application/json")
    public ResponseEntity<ResponseObject> getBets() {
        return betApi.getBets();
    }

    @GetMapping(value = "/bets/{id}", produces = "application/json")
    public ResponseEntity<ResponseObject> getBetById(@PathVariable Integer id) {
        return betApi.getBetById(id);
    }

    @GetMapping(value = "/bets/create", produces = "application/json")
    public ResponseEntity<ResponseObject> createBet(@RequestBody Bet bet) {
        // Verificar se o usuário está valido para apostar
        return betApi.createBet(bet);
    }

    @DeleteMapping(value = "/bets/remove", produces = "application/json")
    public ResponseEntity<ResponseObject> removeBet(@RequestBody Integer id) {
        return betApi.removeBet(id);
    }

    @PostMapping(value = "/bets/run-individual-game", produces = "application/json")
    public ResponseEntity<ResponseObject> runIndividualGame(@RequestBody Integer id) {
        return betApi.runIndividualGame(id);
    }

}
