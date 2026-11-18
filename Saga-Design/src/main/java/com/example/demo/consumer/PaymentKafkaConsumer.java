/*
package com.example.demo.consumer;

import com.example.demo.event.OrderEvent;
import com.example.demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentKafkaConsumer {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "order-created", groupId = "payment-group")
    public void consumeOrderCreated(OrderEvent event) {
        System.out.println("payment kafka consumer triggred:..........."+event);
        paymentService.processPayment(event.getOrderId());
    }
}
*/
