package com.example.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.User; 
import com.example.entity.Client; 
 
public interface UserRepository extends JpaRepository<User, Long> { 
	List<User> findBy(); 
	Client findByClientId(Long id);
}