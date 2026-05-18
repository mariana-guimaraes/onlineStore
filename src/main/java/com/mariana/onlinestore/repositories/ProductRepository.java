package com.mariana.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mariana.onlinestore.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
