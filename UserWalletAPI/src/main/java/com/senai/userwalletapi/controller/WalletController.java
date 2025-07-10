package com.senai.userwalletapi.controller;

import com.senai.userwalletapi.dtos.PaymentDto;
import com.senai.userwalletapi.entity.ResponseObject;
import com.senai.userwalletapi.entity.User;
import com.senai.userwalletapi.entity.Wallet;
import com.senai.userwalletapi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping(value = "/wallets", produces = "application/json")
    public ResponseEntity<ResponseObject> getWalletByUser(User user) {
        try {
            Wallet wallet = walletService.getWalletByUser(user);
            return ResponseEntity.ok(new ResponseObject(true, wallet));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseObject(false, e.getMessage()));
        }
    }

    @PostMapping(value = "/wallets", produces = "application/json")
    public ResponseEntity<ResponseObject> createWallet(User user) {
        try {
            Wallet wallet = walletService.createWallet(user);
            return ResponseEntity.ok(new ResponseObject(true, wallet));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseObject(false, e.getMessage()));
        }
    }

    @PostMapping(value = "/wallets/winner-payment", produces = "application/json")
    public ResponseEntity<ResponseObject> winnerPayment(PaymentDto paymentDto) {
        User user = paymentDto.getUser();
        Double paymentValue = paymentDto.getPaymentValue();
        try {
            Wallet wallet = walletService.addCreditToWallet(user, paymentValue);
            return ResponseEntity.ok(new ResponseObject(true, wallet));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ResponseObject(false, e.getMessage()));
        }
    }
}
