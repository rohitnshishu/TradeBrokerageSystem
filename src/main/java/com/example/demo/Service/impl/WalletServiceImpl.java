package com.example.demo.Service.impl;

import com.example.demo.Model.Wallet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl {

    public Wallet getWalletByUserId(String userId){
        List<Wallet> walletArrayList = new ArrayList<>();
        walletArrayList.add(new Wallet("user1",20000.50,100));
        walletArrayList.add(new Wallet("user2",25000.50,200));
        Optional<Wallet> wallet = walletArrayList.stream().filter(w->w.getUserId().equals(userId)).findFirst();
        if(wallet.isPresent()){
            return wallet.get();
        }
        return null;
    }
}
