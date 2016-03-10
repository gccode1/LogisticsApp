package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class User {
	@JsonIgnore
    @ManyToOne
    private Client client;
	
	@Id 
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	User() { // jpa only
    }
	public User(Client client,String firstName, String phone) {
		this.client=client;
		this.firstName = firstName;
		this.phone = phone;
	}

	@JsonIgnore
	private String firstName;
	private String phone;
	
	
}
