package com.mariana.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mariana.onlinestore.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
