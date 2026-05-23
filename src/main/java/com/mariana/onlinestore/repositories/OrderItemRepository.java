package com.mariana.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mariana.onlinestore.entities.OrderItem;
import com.mariana.onlinestore.entities.pk.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
