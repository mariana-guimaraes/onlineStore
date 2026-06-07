package com.mariana.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mariana.onlinestore.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
