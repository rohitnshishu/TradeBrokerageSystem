package com.example.demo.Service.impl;

import com.example.demo.Model.Trade;
import com.example.demo.Model.TradeResponse;
import com.example.demo.Model.Wallet;
import com.example.demo.Repository.TradeRepository;
import com.example.demo.Service.KafkaProducer;
import com.example.demo.Service.TradeBookingService;
import com.example.demo.constant.TRADESTATUS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TradeBookingServiceImpl implements TradeBookingService {

    @Autowired
    private WalletServiceImpl walletService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    TradeRepository tradeRepository;

    /*
    Handles new Trade booking
     */
    @Override
    public TradeResponse bookTrade(Trade trade) {
        if (trade.getTradeId() == null) {

            if (trade.getIsBuy()) {
                Wallet wallet = walletService.getWalletByUserId(trade.getTraderId());
                Double amountNeeded = trade.getSharePrice() * trade.getShareQuantity();
                wallet.setWalletBalance(wallet.getWalletBalance() - amountNeeded);
            }

            if (!trade.getIsBuy()) {
                Wallet wallet = walletService.getWalletByUserId(trade.getTraderId());
                Integer shareToSell = trade.getShareQuantity();
                wallet.setShareQuantity(wallet.getShareQuantity() - shareToSell);
            }

            trade.setTradeId("TID" + System.currentTimeMillis());
            TRADESTATUS tradestatus = kafkaProducer.SendMessage(trade);
            tradeRepository.save(trade);
            return new TradeResponse(trade.getTradeId(),tradestatus,"succesfull",new Date());

        }
        return null;
    }
}
