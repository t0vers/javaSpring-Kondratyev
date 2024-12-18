package org.example.module6_orderservice;

import lombok.RequiredArgsConstructor;
import org.example.module6_orderservice.dto.OrderRequest;
import org.example.module6_orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/send")
    public void sendOrderEvent(@RequestBody OrderRequest request){
        orderService.send(request);
    }
}
