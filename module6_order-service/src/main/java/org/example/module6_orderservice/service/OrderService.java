package org.example.module6_orderservice.service;

import org.example.module6_orderservice.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private KafkaTemplate<String, OrderRequest> kafkaTemplate;

    public void send(OrderRequest request) {
        kafkaTemplate.send("order-topic",request);
    }
}
