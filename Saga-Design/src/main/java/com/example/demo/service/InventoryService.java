package com.example.demo.service;

import com.example.demo.model.Inventory;
import com.example.demo.producer.InventoryProducer;
import com.example.demo.repostory.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    @Autowired
    private InventoryProducer producer;

    @Transactional
    public void reserveInventory(Long orderId) {

        // 🔹 Simulate inventory check
        boolean stockAvailable = orderId % 2 != 0; // demo logic

        Inventory inventory = new Inventory();
        inventory.setProductId(orderId);
        inventory.setAvailableQty(9);
        inventory.setStatus("RESERVED");

        if (stockAvailable) {
            repository.save(inventory);
            producer.sendInventorySuccess(orderId);

        } else {
            inventory.setStatus("RELEASED");
            repository.save(inventory);
            producer.sendInventoryFailed(orderId, "Out of stock");
        }
    }
}
