package com.example.demo.Model;

import lombok.*;

@Data
@AllArgsConstructor
public class Trade {

    private String tradeId;
    private String shareName;
    private Integer shareQuantity;
    private Double sharePrice;
    private String traderId;
    private Boolean isBuy;

}
