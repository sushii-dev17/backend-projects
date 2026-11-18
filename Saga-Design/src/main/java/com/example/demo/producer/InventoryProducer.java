package com.example.demo.producer;

import com.example.demo.event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class InventoryProducer {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendInventorySuccess(Long orderId) {
        kafkaTemplate.send(
                "inventory-success",
                new OrderEvent(orderId, "INVENTORY_SUCCESS","INVENTORY", null)
        );
    }
    public void sendInventoryFailed(Long orderId, String reason) {
        kafkaTemplate.send(
                "inventory-failed",
                new OrderEvent(orderId, "INVENTORY_FAILED","INVENTORY", reason)
        );
    }
}
