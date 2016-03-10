package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@JsonIgnore
	@ManyToOne
	public Client client;	
	User(){
	}
	public User(Client client,String firstName, String lastName,String phone) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone =phone;
		this.client=client;
	}

	
	
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String phone;
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Long getId() {
		return id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	

}