package com.example.demo.Service.impl;

import com.example.demo.Model.Trade;
import com.example.demo.Model.Wallet;
import com.example.demo.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private WalletServiceImpl walletService;
    @Override
    public boolean validateTrade(Trade trade) {

      return isWalletBalanceSufficient(trade) &&
              isWalletShareQuantitySufficient(trade);

    }
    private boolean isWalletBalanceSufficient(Trade trade) {
        if (trade.getIsBuy()) {
            double amountNeeded = trade.getSharePrice() * trade.getShareQuantity();
            Wallet wallet = walletService.getWalletByUserId(trade.getTraderId());
            if (wallet.getWalletBalance() < amountNeeded) {
                return false;
            }
        }
        return true;
    }

    private boolean isWalletShareQuantitySufficient(Trade trade){
        if (!trade.getIsBuy()) {
            double shareForSell = trade.getShareQuantity();
            Wallet wallet = walletService.getWalletByUserId(trade.getTraderId());
            if (wallet.getShareQuantity() < shareForSell) {
                return false;
            }
        }
        return true;
    }


}
