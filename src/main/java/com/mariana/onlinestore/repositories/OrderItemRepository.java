package com.mariana.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mariana.onlinestore.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
