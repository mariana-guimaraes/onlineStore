package com.mariana.onlinestore.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariana.onlinestore.entities.Order;
import com.mariana.onlinestore.entities.OrderItem;
import com.mariana.onlinestore.entities.Product;
import com.mariana.onlinestore.entities.User;
import com.mariana.onlinestore.repositories.OrderItemRepository;
import com.mariana.onlinestore.repositories.OrderRepository;
import com.mariana.onlinestore.repositories.ProductRepository;
import com.mariana.onlinestore.repositories.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public List<Order> findAll(){
		return repository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}

	public Order insert(Order obj) {
		obj.setMoment(Instant.now());
		User user = userRepository.getReferenceById(obj.getClient().getId());
		obj.setClient(user);
		return repository.save(obj);
	}

	public OrderItem addItem(Long orderId, OrderItem item) {
		Order order = repository.getReferenceById(orderId);
		Product product = productRepository.getReferenceById(item.getProduct().getId());
		item.setOrder(order);
		item.setProduct(product);
		item.setPrice(product.getPrice());
		return orderItemRepository.save(item);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
