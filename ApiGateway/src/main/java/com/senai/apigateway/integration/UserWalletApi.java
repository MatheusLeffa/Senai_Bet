package com.senai.apigateway.integration;

import com.senai.apigateway.dtos.PaymentDto;
import com.senai.apigateway.entity.ResponseObject;
import com.senai.apigateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "UserWallet", url = "http://localhost:8083")
public interface UserWalletApi {

    @GetMapping(value = "/wallets", produces = "application/json")
    public ResponseEntity<ResponseObject> getWalletByUser(User user);

    @PostMapping(value = "/wallets", produces = "application/json")
    public ResponseEntity<ResponseObject> createWallet(User user);

    @PostMapping(value = "/wallets/winner-payment", produces = "application/json")
    public ResponseEntity<ResponseObject> winnerPayment(PaymentDto paymentDto);
}
