package com.example.demo.repostory;

import com.example.demo.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

   // Optional<Inventory> findByOrderId(Long orderId);
}
