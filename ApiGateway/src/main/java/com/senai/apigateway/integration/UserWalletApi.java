package com.senai.apigateway.integration;

import com.senai.apigateway.dto.PaymentDto;
import com.senai.apigateway.dto.ResponseDto;
import com.senai.apigateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "UserWallet", url = "http://localhost:8083")
public interface UserWalletApi {

    @GetMapping(value = "/wallet/{userId}", produces = "application/json")
    public ResponseEntity<ResponseDto> getWalletByUserId(@PathVariable Integer userId);

    @PostMapping(value = "/wallet", produces = "application/json")
    public ResponseEntity<ResponseDto> createWallet(@RequestBody User user);
}
