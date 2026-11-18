package com.example.demo.service;

import com.example.demo.model.Payment;
import com.example.demo.producer.PaymentProducer;
import com.example.demo.repostory.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private PaymentProducer producer;

    @Transactional
    public void processPayment(Long orderId) {

        // 🔹 Simulated business logic
        boolean paymentAllowed = orderId % 2 == 0; // demo logic

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(1000.0);
        if (paymentAllowed) {
            payment.setStatus("SUCCESS");
            repository.save(payment);

            producer.sendPaymentSuccess(orderId);

        } else {
            payment.setStatus("FAILED");
            repository.save(payment);

            producer.sendPaymentFailed(orderId, "Insufficient balance");
        }
    }
}
