package com.senai.userwalletapi.service;

import com.senai.userwalletapi.entity.User;
import com.senai.userwalletapi.entity.Wallet;
import com.senai.userwalletapi.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(User user) throws Exception {
        if (user == null) {
            throw new Exception("O usuário da carteira nao pode ser nulo!");
        }
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.createWallet(wallet);
    }

    public Wallet getWalletByUser(User user) throws Exception {
        Wallet wallet = walletRepository.getWalletByUserId(user.getId());
        if (wallet == null) {
            throw new Exception("A carteira nao foi encontrada!");
        }
        return wallet;
    }

    public Wallet addCreditToWallet(User user, Double addValue) throws Exception {
        Wallet walletToUpdate = walletRepository.getWalletByUserId(user.getId());
        if (walletToUpdate == null) {
            throw new Exception("A carteira nao foi encontrada!");
        }
        walletToUpdate.setBalance(walletToUpdate.getBalance() + addValue);
        return walletRepository.updateWallet(walletToUpdate);
    }
}
