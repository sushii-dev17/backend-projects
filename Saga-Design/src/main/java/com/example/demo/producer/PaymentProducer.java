package com.example.demo.producer;

import com.example.demo.event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendPaymentSuccess(Long orderId) {
        kafkaTemplate.send(
                "payment-success",
                new OrderEvent(orderId, "PAYMENT_SUCCESS", "PAYMENT", null));

    }

    public void sendPaymentFailed(Long orderId, String reason) {
        kafkaTemplate.send(
                "payment-failed",
                new OrderEvent(orderId, "PAYMENT_FAILED", "PAYMENT", reason)
        );
    }
}
