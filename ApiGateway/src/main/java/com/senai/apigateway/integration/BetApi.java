package com.senai.apigateway.integration;

import com.senai.apigateway.dtos.NewBetDto;
import com.senai.apigateway.entity.Bet;
import com.senai.apigateway.entity.ResponseObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "BetApi", url = "http://localhost:8081")
public interface BetApi {
    @PostMapping(value = "/bets/run-individual-game", produces = "application/json")
    public ResponseEntity<ResponseObject> runIndividualGame(@RequestBody Bet bet);
}
