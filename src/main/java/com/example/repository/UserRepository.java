package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.User;
import com.example.entity.Client;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Collection<User> findByClientId(Long clientId);
	Optional<User> findByClientIdAndId(Long clientId,Long userId);
	void deleteByClientId(Long clientId);
	//SELECT b from User b WHERE b.client.username = :username
	
	
	

}