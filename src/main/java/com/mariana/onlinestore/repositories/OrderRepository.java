package com.mariana.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mariana.onlinestore.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
