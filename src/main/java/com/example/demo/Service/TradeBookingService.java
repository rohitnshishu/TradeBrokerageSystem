package com.example.demo.Service;

import com.example.demo.Model.Trade;
import com.example.demo.Model.TradeResponse;

public interface TradeBookingService {

    TradeResponse bookTrade(Trade trade);
}
