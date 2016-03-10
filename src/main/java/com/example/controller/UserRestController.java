package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.entity.User;
import com.example.repository.ClientRepository;
import com.example.repository.UserRepository;

@RestController
@RequestMapping("client/{clientId}/users")
class UserRestController {

	private final UserRepository userRepository;

	private final ClientRepository clientRepository;

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<User> add(@PathVariable Long clientId, @RequestBody User input) {
		this.validateClient(clientId);
		return this.clientRepository
				.findById(clientId)
				.map(client -> {
					User result = userRepository.save(new User(client,input.getFirstName(),input.getLastName(),input.getPhone()));

					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getId()).toUri());
					return new ResponseEntity<>(result, httpHeaders, HttpStatus.CREATED);
				}).get();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	ResponseEntity<User> readUser(@PathVariable("clientId") Long clientId, @PathVariable("userId") Long userId) {
		this.validateClient(clientId);
		this.validateUser(clientId,userId);
		User user=this.userRepository.findOne(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<Collection<User> > readUsers(@PathVariable("clientId") Long clientId) {
		this.validateClient(clientId);
		Collection<User> users=this.userRepository.findByClientId(clientId);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	
	//patch
	@RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
	public ResponseEntity<User> patchupdateUser(@PathVariable Long clientId, @PathVariable("userId") Long userId, @RequestBody User user) {
		this.validateClient(clientId);
		this.validateUser(clientId,userId);
		return this.clientRepository
    		 .findById(clientId)
    		 .map(client->{
    			 User currentUser = userRepository.findOne(userId); 
    			 if(user.getFirstName()!=null)
    				 currentUser.setFirstName(user.getFirstName());
    			 if(user.getLastName()!=null)
    				 currentUser.setLastName(user.getLastName());
    			 if(user.getPhone()!=null)
    				 currentUser.setFirstName(user.getPhone());
    			 userRepository.save(currentUser);
    			 return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    		 })
    	.get();
    }
	
	//updating_user
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable Long clientId, @PathVariable("userId") long userId, @RequestBody User user) {
		this.validateClient(clientId);
		this.validateUser(clientId,userId);
		return this.clientRepository
			.findById(clientId)
	        .map(client->{
	        	User currentUser = userRepository.findOne(userId);
	        currentUser.setFirstName(user.getFirstName());
	        currentUser.setLastName(user.getLastName());
	        currentUser.setPhone(user.getPhone());
	        userRepository.saveAndFlush(currentUser);
	        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	    }).get();
	}
	 
	//delete_single_user
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable Long clientId,@PathVariable("userId") Long userId) {
		this.validateClient(clientId);
		this.validateUser(clientId,userId);
        User user = userRepository.findOne(userId);
        userRepository.delete(userId);
        return new ResponseEntity<User>(HttpStatus.OK);
    }
	

	@Autowired
	UserRestController(UserRepository userRepository,
			ClientRepository clientRepository) {
		this.userRepository = userRepository;
		this.clientRepository = clientRepository;
	}

	private void validateClient(Long clientId) {
		this.clientRepository.findById(clientId).orElseThrow(
				() -> new ClientNotFoundException(clientId));
	}
	private void validateUser(Long clientId,Long userId) {
		this.userRepository.findByClientIdAndId(clientId,userId).orElseThrow(
				() -> new UserNotFoundException(userId));
	}
}



@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(Long userId) {
		super("could not find user '" + userId + "'.");
	}
}