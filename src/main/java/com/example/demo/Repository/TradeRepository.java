package com.example.demo.Repository;

import com.example.demo.Model.Trade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TradeRepository {

    List<Trade> tradeList = new ArrayList<>();
    public void save(Trade trade){
        tradeList.add(trade);
    }

    public Trade retrieveTradeById(String tradeId){
        Optional<Trade> trade = tradeList.stream().filter(t->t.getTradeId().equals(tradeId)).findFirst();
        return new Trade("T122","IBM",200,100.46,"user1",true);
    }
}
