package com.senai.frontend.integration;

import com.senai.frontend.dto.NewBetDto;
import com.senai.frontend.dto.NewUserDto;
import com.senai.frontend.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ApiGateway", url = "http://localhost:8085")
public interface ApiGateway {

    //Bet endpoints
    @PostMapping(value = "/bets/run-individual-game", produces = "application/json")
    public ResponseEntity<ResponseDto> runIndividualGame(@RequestBody NewBetDto newBetDto);

    //User endpoints
    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseDto> getUsers();
    @GetMapping(value = "/users/{id}", produces = "application/json")
    public ResponseEntity<ResponseDto> getUser(@PathVariable Integer id);
    @PostMapping(value = "/users", produces = "application/json")
    public ResponseEntity<ResponseDto> createUser(@RequestBody NewUserDto newUserDto);
}
