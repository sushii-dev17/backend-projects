package com.example.demo.producer;

import com.example.demo.event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void publishOrderCreated(OrderEvent event) {
        kafkaTemplate.send("order-created", event);
    }

    public void publishOrderCancelled(OrderEvent event) {
        kafkaTemplate.send("order-cancelled", event);
    }

}
