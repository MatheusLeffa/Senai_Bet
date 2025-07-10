package com.senai.userwalletapi.service;

import com.senai.userwalletapi.entity.User;
import com.senai.userwalletapi.entity.Wallet;
import com.senai.userwalletapi.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(User user) throws Exception {
        if (user == null) {
            throw new Exception("O usuário da carteira nao pode ser nulo!");
        }
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(0.0d);
        return walletRepository.createWallet(wallet);
    }

    public Wallet getWalletByUserId(Integer userId) throws Exception {
        Wallet wallet = walletRepository.getWalletByUserId(userId);
        if (wallet == null) {
            throw new Exception("A carteira nao foi encontrada!");
        }
        return wallet;
    }

    public void addCreditToWallet(Integer userId, Double addValue) throws Exception {
        Wallet walletToUpdate = walletRepository.getWalletByUserId(userId);
        if (walletToUpdate == null) {
            throw new Exception("A carteira nao foi encontrada!");
        }
        walletToUpdate.setBalance(walletToUpdate.getBalance() + addValue);
        walletRepository.updateWallet(walletToUpdate);
    }
}
