package com.mariana.onlinestore.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/checkout")
public class CheckoutResource {

	@PostMapping(value = "/{orderId}")
	public ResponseEntity<String> checkout(@PathVariable Long orderId) {
		return ResponseEntity.ok().body("payment completed!");
	}
}
