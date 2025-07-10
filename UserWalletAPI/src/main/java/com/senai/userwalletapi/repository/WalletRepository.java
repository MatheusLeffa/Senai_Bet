package com.senai.userwalletapi.repository;

import com.senai.userwalletapi.entity.Wallet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WalletRepository {
    private final static List<Wallet> wallets = new ArrayList<>();

    public Wallet createWallet(Wallet wallet) {
        wallets.add(wallet);
        return wallet;
    }

    public Wallet getWalletByUserId(Integer id) {
        return wallets.stream().filter(w -> w.getUser().getId().equals(id)).findFirst().orElse(null);
    }

    public Wallet updateWallet(Wallet wallet) {
        wallets.removeIf(w -> w.getUser().getId().equals(wallet.getUser().getId()));
        wallets.add(wallet);
        return wallet;
    }
}
