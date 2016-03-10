package com.example.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Client {
	@OneToMany(mappedBy = "client")
    private Set<User> users = new HashSet<>();
	@Id 
	@GeneratedValue
	private Long id;
	private String firstName;
	private String phone;
	public Client(){
	}
	public Client(String firstName,String phone){
		this.firstName=firstName;
		this.phone=phone;
	}
	
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
}

