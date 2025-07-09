package com.senai.apigateway.controller;

import com.senai.apigateway.dtos.NewBetDto;
import com.senai.apigateway.entity.Bet;
import com.senai.apigateway.entity.ResponseObject;
import com.senai.apigateway.entity.User;
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

    @PostMapping(value = "/bets/run-individual-game", produces = "application/json")
    public ResponseEntity<ResponseObject> runIndividualGame(@RequestBody NewBetDto newBetDto) {
        // Busca o usuário
        ResponseObject getUserResponse = userApi.getUser(newBetDto.getUserId()).getBody();
        if (getUserResponse == null) {
            return ResponseEntity.status(500).body(new ResponseObject(false, "Erro de ao deserializar objeto."));
        }
        User user = (User) getUserResponse.getResult();
        // Verifica se ele pode apostar
        if (!user.isAllowedToBet()) {
            return ResponseEntity.status(400).body(new ResponseObject(false, "O usuário nao pode apostar."));
        }

        // Cria a aposta e roda o jogo individual
        Bet bet = new Bet();
        bet.setUser(user);
        bet.setBetNumbers(newBetDto.getBetNumbers());

        ResponseObject runIndividualGameResponse = betApi.runIndividualGame(bet).getBody();

        if (runIndividualGameResponse == null) {
            return ResponseEntity.status(500).body(new ResponseObject(false, "Erro de ao deserializar objeto."));
        }

        // Verifica se o usuário ganhou
        bet = (Bet) runIndividualGameResponse.getResult();
        if (!bet.isWinner()) {
            return ResponseEntity.status(200).body(new ResponseObject(true, bet));
        }

        // TODO: Implementar produção de mensagem para pagamento

        return ResponseEntity.status(200).body(new ResponseObject(true, bet));
    }

    @GetMapping(value = "/bets/create", produces = "application/json")
    public ResponseEntity<ResponseObject> createBet(@RequestBody NewBetDto newBetDto) {
        // Busca o usuário
        ResponseObject getUserResponse = userApi.getUser(newBetDto.getUserId()).getBody();
        if (getUserResponse == null) {
            return ResponseEntity.status(500).body(new ResponseObject(false, "Erro de ao deserializar objeto."));
        }
        User user = (User) getUserResponse.getResult();
        // Verifica se ele pode apostar
        if (!user.isAllowedToBet()) {
            return ResponseEntity.status(400).body(new ResponseObject(false, "O usuário nao pode apostar."));
        }

        // Cria a aposta
        Bet bet = new Bet();
        bet.setUser(user);
        bet.setBetNumbers(newBetDto.getBetNumbers());

        ResponseObject createBetResponse = betApi.createBet(bet).getBody();
        if (createBetResponse == null) {
            return ResponseEntity.status(500).body(new ResponseObject(false, "Erro de ao deserializar objeto."));
        }
        Bet betCreated = (Bet) createBetResponse.getResult();
        return ResponseEntity.status(201).body(new ResponseObject(true, betCreated));
    }

    @PostMapping(value = "/bets/run-game", produces = "application/json")
    public ResponseEntity<ResponseObject> runGame() {
        return betApi.runGame();
    }

    @DeleteMapping(value = "/bets/remove", produces = "application/json")
    public ResponseEntity<ResponseObject> removeBet(@RequestBody Integer id) {
        return betApi.removeBet(id);
    }

    @DeleteMapping(value = "/bets/clear-bet-list", produces = "application/json")
    public ResponseEntity<ResponseObject> clearBetList() {
        return betApi.clearBetList();
    }
}
