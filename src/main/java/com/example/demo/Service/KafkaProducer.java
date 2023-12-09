package com.example.demo.Service;

import com.example.demo.Model.Trade;
import com.example.demo.constant.TRADESTATUS;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    public TRADESTATUS SendMessage(Trade trade){

        System.out.println("Trade sent successfully to exchange");
        return TRADESTATUS.ACCEPTED;
    }
}
