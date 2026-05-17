package com.mariana.onlinestore.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mariana.onlinestore.entities.Order;
import com.mariana.onlinestore.entities.User;
import com.mariana.onlinestore.repositories.OrderRepository;
import com.mariana.onlinestore.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Maria", "maria@email.com", "912345678", "pw123");
		User u2 = new User(null, "Joao", "joao@email.com", "987654321", "pw000"); 
		
		Order o1 = new Order(null, Instant.parse("2026-05-15T06:10:00Z"), u1);
		Order o2 = new Order(null, Instant.parse("2026-05-16T12:20:00Z"), u2);
		Order o3 = new Order(null, Instant.parse("2026-05-17T18:30:00Z"), u1);

		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}

}
