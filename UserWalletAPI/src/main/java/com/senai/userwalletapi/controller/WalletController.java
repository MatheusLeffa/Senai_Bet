package com.senai.userwalletapi.controller;

import com.senai.userwalletapi.dto.ResponseDto;
import com.senai.userwalletapi.entity.User;
import com.senai.userwalletapi.entity.Wallet;
import com.senai.userwalletapi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping(value = "/wallet/{userId}", produces = "application/json")
    public ResponseEntity<ResponseDto> getWalletByUserId(@PathVariable Integer userId) {
        try {
            Wallet wallet = walletService.getWalletByUserId(userId);
            return ResponseEntity.ok(new ResponseDto(true, wallet));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseDto(false, e.getMessage()));
        }
    }

    @PostMapping(value = "/wallet", produces = "application/json")
    public ResponseEntity<ResponseDto> createWallet(@RequestBody User user) {
        try {
            Wallet wallet = walletService.createWallet(user);
            return ResponseEntity.ok(new ResponseDto(true, wallet));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseDto(false, e.getMessage()));
        }
    }
}
