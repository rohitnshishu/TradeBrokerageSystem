package com.example.demo.Service.impl;

import com.example.demo.Model.Trade;
import com.example.demo.Model.TradeResponse;
import com.example.demo.Model.Wallet;
import com.example.demo.Repository.TradeRepository;
import com.example.demo.Service.KafkaProducer;
import com.example.demo.Service.TradeUpdateService;
import com.example.demo.constant.TRADESTATUS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TradeUpdateServiceImpl implements TradeUpdateService {

    @Autowired
    WalletServiceImpl walletService;

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    KafkaProducer kafkaProducer;

    @Override
    public TradeResponse updateTrade(Trade trade) {

        if(trade.getTradeId()!=null){
            Wallet wallet = walletService.getWalletByUserId(trade.getTraderId());
            Trade existingTrade = tradeRepository.retrieveTradeById(trade.getTradeId());
            if(trade.getIsBuy()){
                Double updatedTradeAmount = trade.getSharePrice() * trade.getShareQuantity();
                Double walletBalance = wallet.getWalletBalance();
                Double existingTradeAmount = existingTrade.getShareQuantity()*existingTrade.getSharePrice();
                wallet.setWalletBalance(walletBalance-(updatedTradeAmount-existingTradeAmount));
            }

            if(!trade.getIsBuy()){
                Integer updatedShareQuantity =  trade.getShareQuantity();
                Integer walletShareQuantity = wallet.getShareQuantity();
                Integer existingShareQuantity = existingTrade.getShareQuantity();
                wallet.setShareQuantity(walletShareQuantity-(updatedShareQuantity-existingShareQuantity));
            }

            TRADESTATUS tradestatus = kafkaProducer.SendMessage(trade);
            tradeRepository.save(trade);
            return new TradeResponse(trade.getTradeId(),tradestatus,"",new Date());
        }

        return null;
    }
}
