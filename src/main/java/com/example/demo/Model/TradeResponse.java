package com.example.demo.Model;

import com.example.demo.constant.TRADESTATUS;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TradeResponse {
    private String tradeId;
    private TRADESTATUS tradeStatus;
    private String remarks;
    private Date dateTime;
}
