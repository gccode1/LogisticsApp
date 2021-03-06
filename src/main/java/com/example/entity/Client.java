package com.example.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client {

	@Id
	@GeneratedValue
	private Long id;
	
	
	private String username;
	private String password;
	
	@OneToMany(mappedBy="client")
	private Set<User> user = new HashSet<>();
	
	Client(){
		//JPA
		
	}
	
	public Client(String username, String pass) {
		super();
		
		this.username=username;
		this.password=pass;
	}
	
	public Long getId() {
		return id;
	}
	
	public Set<User> getUser() {
		return user;
	}
	public void setUser(Set<User> user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}