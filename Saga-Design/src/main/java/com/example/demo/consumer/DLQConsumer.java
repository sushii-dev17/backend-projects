package com.example.demo.consumer;

import com.example.demo.event.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DLQConsumer {

    @KafkaListener(topics = "order-created.DLT")
    public void dlqListener(OrderEvent event) {
        System.out.println("Message moved to DLQ...........................: " + event);
    }
}
