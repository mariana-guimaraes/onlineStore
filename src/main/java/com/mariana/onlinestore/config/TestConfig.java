package com.mariana.onlinestore.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mariana.onlinestore.entities.User;
import com.mariana.onlinestore.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Maria", "maria@email.com", "912345678", "pw123");
		User u2 = new User(null, "Joao", "joao@email.com", "987654321", "pw000"); 
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}

}
