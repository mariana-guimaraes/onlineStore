package com.mariana.onlinestore.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mariana.onlinestore.entities.Category;
import com.mariana.onlinestore.entities.Order;
import com.mariana.onlinestore.entities.OrderItem;
import com.mariana.onlinestore.entities.Payment;
import com.mariana.onlinestore.entities.Product;
import com.mariana.onlinestore.entities.User;
import com.mariana.onlinestore.repositories.CategoryRepository;
import com.mariana.onlinestore.repositories.OrderItemRepository;
import com.mariana.onlinestore.repositories.OrderRepository;
import com.mariana.onlinestore.repositories.ProductRepository;
import com.mariana.onlinestore.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Furniture");
		Category cat2 = new Category(null, "Decoration");
		Category cat3 = new Category(null, "Utensils");
		
		Product p1 = new Product(null, "Couch", "2,4m long beige couch.", 699.0, "");
		Product p2 = new Product(null, "Table", "Round 1,2m white table.", 179.0, "");
		Product p3 = new Product(null, "Rug", "1,5x2,0m multicolour rug.", 129.0, "");
		Product p4 = new Product(null, "Painting", "50x70cm framed picture.", 29.0, "");
		Product p5 = new Product(null, "Cutlery Set", "16-piece stainless steel set.", 15.0, "");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		p1.getCategories().add(cat1);
		p2.getCategories().add(cat1);
		p3.getCategories().add(cat2);
		p4.getCategories().add(cat2);
		p5.getCategories().add(cat3);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
		User u1 = new User(null, "Maria", "maria@email.com", "912345678", "pw123");
		User u2 = new User(null, "Joao", "joao@email.com", "987654321", "pw000");

		Order o1 = new Order(null, Instant.parse("2026-05-15T06:10:00Z"), u1);
		Order o2 = new Order(null, Instant.parse("2026-05-16T12:20:00Z"), u2);
		Order o3 = new Order(null, Instant.parse("2026-05-17T18:30:00Z"), u1);

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		OrderItem oi1 = new OrderItem(o1, p1, 1, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p4, 2, p4.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice()); 
		
		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2026-05-15T06:30:00Z"), o1);
		o1.setPayment(pay1);

		orderRepository.save(o1);
	}

}
