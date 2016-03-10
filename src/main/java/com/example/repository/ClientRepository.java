package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>
{
	Optional<Client> findById(Long id);
	//select a from Client a where a.username = :usernam
}