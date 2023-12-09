package com.example.demo.Controller;

import com.example.demo.Model.Trade;
import com.example.demo.Model.TradeResponse;
import com.example.demo.Repository.TradeRepository;
import com.example.demo.Service.TradeBookingService;
import com.example.demo.Service.TradeUpdateService;
import com.example.demo.Service.ValidationService;
import com.example.demo.constant.TRADESTATUS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class TradeBookingController {

    @Autowired
    private ValidationService validationService;

    @Autowired
    TradeBookingService tradeBookingService;

    @Autowired
    TradeUpdateService tradeUpdateService;

    @Autowired
    TradeRepository tradeRepository;


    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/handleTrade", method = RequestMethod.POST)
    public ResponseEntity<TradeResponse> handle(@RequestBody Trade trade) {

        boolean isValid = validationService.validateTrade(trade);
        if (!isValid) {
            return new ResponseEntity<>(new TradeResponse(trade.getTradeId(), TRADESTATUS.INVALID, "Invalid Trade", new Date()), HttpStatus.BAD_REQUEST);
        }
        if (trade.getTradeId() == null) {
            TradeResponse response = tradeBookingService.bookTrade(trade);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if (trade.getTradeId() != null) {
            TradeResponse response = tradeUpdateService.updateTrade(trade);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return null;
    }

    @RequestMapping(value = "/retrieveTrade", method = RequestMethod.GET)
    public Trade retrieve(String tradeId) {
        return tradeRepository.retrieveTradeById(tradeId);
    }
}
