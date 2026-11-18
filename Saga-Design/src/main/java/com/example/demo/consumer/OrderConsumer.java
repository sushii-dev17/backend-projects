package com.example.demo.consumer;

import com.example.demo.event.OrderEvent;
import com.example.demo.model.Order;
import com.example.demo.repostory.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "order-cancelled", groupId = "order-group")
    public void cancelOrder(OrderEvent event) {
        System.out.println("order kafka consumer  cancelOrder triggred:..........." + event);
        orderService.cancelOrder(event.getOrderId());
    }

    @KafkaListener(topics = "inventory-success", groupId = "order-group")
    public void onInventorySuccess(OrderEvent event) {
        System.out.println("order kafka consumer  onInventorySuccess triggred:..........." + event);
        Order order = orderRepository.findById(event.getOrderId()).orElseThrow();
        order.setStatus("COMPLETED");
        orderRepository.save(order);
    }

    @KafkaListener(topics = "payment-failed", groupId = "order-group")
    public void handlePaymentFailure(OrderEvent event) {
        System.out.println("order kafka consumer handlePaymentFailure triggred:..........." + event);
        orderService.cancelOrder(event.getOrderId());
    }

    @KafkaListener(topics = "inventory-failed", groupId = "order-group")
    public void handleInventoryFailure(OrderEvent event) {
        System.out.println("order kafka consumer handleInventoryFailure triggred:..........." + event);
        orderService.cancelOrder(event.getOrderId());
    }


    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(
                    delay = 2000,
                    multiplier = 2.0,
                    maxDelay = 10000
            ),
            dltTopicSuffix = ".DLT"
    )
    @KafkaListener(topics = "order-created", groupId = "order-group",containerFactory = "kafkaListenerContainerFactory")
    public void consumeOrderCreated(OrderEvent event) {

        System.out.println("Processing order..................."+ event.getOrderId());

        if (event.getOrderId() % 2 != 0) {
            System.out.println("error occures..................");
            throw new RuntimeException("Temporary failure");
        }

        System.out.println("Payment successful for order..............."+event.getOrderId());
    }
}
