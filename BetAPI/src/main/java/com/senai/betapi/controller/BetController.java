package com.senai.betapi.controller;

import com.senai.betapi.entity.Bet;
import com.senai.betapi.entity.ResponseObject;
import com.senai.betapi.service.BetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Bets", description = "Bets API")
public class BetController {

    private final BetService betService;

    @Autowired
    public BetController(BetService betService) {
        this.betService = betService;
    }

    @GetMapping(value = "/bets", produces = "application/json")
    public ResponseEntity<ResponseObject> getBets() {
        List<Bet> betList = betService.getBetList();
        return ResponseEntity.ok(new ResponseObject(true, betList));
    }

    @GetMapping(value = "/bets/{id}", produces = "application/json")
    public ResponseEntity<ResponseObject> getBetById(@PathVariable Integer id) {
        try {
            Bet bet = betService.getBetById(id);
            return ResponseEntity.ok(new ResponseObject(true, bet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(false, e.getMessage()));
        }
    }

    @PostMapping(value = "/bets/create", produces = "application/json")
    public ResponseEntity<ResponseObject> createBet(@RequestBody Bet bet) {
        try {
            Bet betCreated = betService.createBet(bet);
            return ResponseEntity.ok(new ResponseObject(true, betCreated));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(false, e.getMessage()));
        }
    }

    @PostMapping(value = "/bets/run-individual-game", produces = "application/json")
    public ResponseEntity<ResponseObject> runIndividualGame(@RequestBody Bet bet) {
        try {
            Bet betCreated = betService.runIndividualGame(bet);
            return ResponseEntity.ok(new ResponseObject(true, betCreated));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(false, e.getMessage()));
        }
    }

    @PostMapping(value = "/bets/run-game", produces = "application/json")
    public ResponseEntity<ResponseObject> runGame() {
        List<Bet> winningBets = betService.runGame();
        return ResponseEntity.ok(new ResponseObject(true, winningBets));
    }

    @DeleteMapping(value = "/bets/remove", produces = "application/json")
    public ResponseEntity<ResponseObject> removeBet(@RequestBody Integer id) {
        try {
            betService.removeBet(id);
            return ResponseEntity.ok(new ResponseObject(true, "Aposta removida com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject(false, e.getMessage()));
        }
    }

    @DeleteMapping(value = "/bets/clear-bet-list", produces = "application/json")
    public ResponseEntity<ResponseObject> clearBetList() {
        betService.clearBetList();
        return ResponseEntity.ok(new ResponseObject(true, "Jogo resetado com sucesso!"));
    }
}
