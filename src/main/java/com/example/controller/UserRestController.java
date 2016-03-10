package com.example.controller;

import com.example.entity.Client;
import com.example.repository.UserRepository;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RestController; 
import java.util.List;
@RestController 
@RequestMapping("/client/{clientId}")
public class UserRestController { 
	@Autowired
	private UserRepository userRepository; 


	
//	@RequestMapping(value = "/{userId}", method = RequestMethod.GET) 
//	public ResponseEntity< Client > getUser(@PathVariable Long clientId,@PathVariable Long userId) { 
//		Client clients= this.userRepository.findByClientId(clientId); 
//		return new ResponseEntity< Client >(clients,HttpStatus.OK);
//	}
}
