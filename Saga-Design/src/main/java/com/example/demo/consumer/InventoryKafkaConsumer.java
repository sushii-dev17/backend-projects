package com.example.demo.consumer;

import com.example.demo.event.OrderEvent;
import com.example.demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InventoryKafkaConsumer {

    @Autowired
    private InventoryService inventoryService;

    @KafkaListener(topics = "payment-success", groupId = "inventory-group")
    public void consumePaymentSuccess(OrderEvent event) {
        System.out.println("inventory kafka consumer triggred:..........."+event);
        inventoryService.reserveInventory(event.getOrderId());
    }
}
