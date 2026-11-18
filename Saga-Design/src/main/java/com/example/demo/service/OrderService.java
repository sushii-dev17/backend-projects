package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.producer.OrderProducer;
import com.example.demo.repostory.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProducer orderProducer;

    public Order createOrder(Order order) {

        orderRepository.save(order);

      /*  orderProducer.publishOrderCreated(
                new OrderEvent(order.getId(), "ORDER_CREATED", "ORDER",null)
        );*/
        return order;
    }

    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

}
