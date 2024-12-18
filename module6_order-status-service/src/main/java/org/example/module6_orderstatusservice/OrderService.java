package org.example.module6_orderstatusservice;

import lombok.extern.log4j.Log4j2;
import org.example.module6_orderstatusservice.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Log4j2
public class OrderService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "order-topic")
    public void listenOrder(OrderRequest request) {
        log.info("Received order: " + request);
        sendInfo(request);
    }

    public void sendInfo(OrderRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        stringBuilder.append(request.quantity() > 10 ? "CREATED" : "PROCESS");
        stringBuilder.append(" time: ");
        stringBuilder.append(format.format(LocalDate.now()));
        kafkaTemplate.send("order-status-topic", stringBuilder.toString());
    }
}
