package com.mariana.onlinestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mariana.onlinestore.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
