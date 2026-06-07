package com.mariana.onlinestore.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariana.onlinestore.entities.Order;
import com.mariana.onlinestore.entities.Payment;
import com.mariana.onlinestore.repositories.OrderRepository;
import com.mariana.onlinestore.repositories.PaymentRepository;

@Service
public class CheckoutService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	public void completePayment(Long orderId) {
		Order order = orderRepository.getReferenceById(orderId);
		Payment payment = new Payment(null, Instant.now(), order);
		paymentRepository.save(payment);
		order.setPayment(payment);
		orderRepository.save(order);
	}
}
