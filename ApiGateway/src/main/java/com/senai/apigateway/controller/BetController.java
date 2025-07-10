package com.senai.apigateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.apigateway.dto.NewBetDto;
import com.senai.apigateway.dto.PaymentDto;
import com.senai.apigateway.entity.Bet;
import com.senai.apigateway.dto.MessageDto;
import com.senai.apigateway.dto.ResponseDto;
import com.senai.apigateway.entity.User;
import com.senai.apigateway.integration.BetApi;
import com.senai.apigateway.integration.UserApi;
import com.senai.apigateway.service.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BetController {

    private final BetApi betApi;
    private final UserApi userApi;
    private final ObjectMapper objectMapper;
    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public BetController(BetApi betApi, UserApi userApi, RabbitMQProducer rabbitMQProducer) {
        this.betApi = betApi;
        this.userApi = userApi;
        this.rabbitMQProducer = rabbitMQProducer;
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping(value = "/bets/run-individual-game", produces = "application/json")
    public ResponseEntity<ResponseDto> runIndividualGame(@RequestBody NewBetDto newBetDto) {
        // Busca o usuário
        ResponseDto getUserResponse = userApi.getUser(newBetDto.getUserId()).getBody();
        if (getUserResponse == null) {
            return ResponseEntity.status(500).body(new ResponseDto(false, "Erro de ao deserializar objeto."));
        }

        User user = objectMapper.convertValue(getUserResponse.getResult(), User.class);

        // Verifica se ele pode apostar
        if (!user.isAllowedToBet()) {
            return ResponseEntity.status(400).body(new ResponseDto(false, "O usuário nao pode apostar."));
        }

        // Cria a aposta e roda o jogo individual
        Bet bet = new Bet();
        bet.setUser(user);
        bet.setBetNumbers(newBetDto.getBetNumbers());

        ResponseDto runIndividualGameResponse = betApi.runIndividualGame(bet).getBody();

        if (runIndividualGameResponse == null) {
            return ResponseEntity.status(500).body(new ResponseDto(false, "Erro de ao deserializar objeto."));
        }

        // Verifica se o usuário ganhou
        bet = objectMapper.convertValue(runIndividualGameResponse.getResult(), Bet.class);
        if (!bet.isWinner()) {
            return ResponseEntity.status(200).body(new ResponseDto(true, bet));
        }

        // Envia mensagem para o Broker de pagamentos
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setUserId(user.getId());
        paymentDto.setPaymentValue(10000d);
        rabbitMQProducer.sendMessage(new MessageDto("PAYMENT", paymentDto));

        //Retorna a aposta com a propriedade: isWinner = true
        return ResponseEntity.status(200).body(new ResponseDto(true, bet));
    }
}
