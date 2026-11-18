package com.example.demo.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private Long orderId;
    private String status; // PENDING, COMPLETED, CANCELLED
    private String service; // ORDER, PAYMENT, INVENTORY, SHIPPING
    private String reason;
}
