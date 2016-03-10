
package com.example.controller;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Client;
import com.example.repository.ClientRepository;
import com.example.repository.UserRepository;

@RestController
@RequestMapping("/client")
class ClientRestController {

	private final UserRepository userRepository1;

	private final ClientRepository clientRepository1;
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Client> createClient(@RequestBody Client client) { 
		if(client.getUsername()==null || client.getPassword()==null )
			return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
		Client client1=this.clientRepository1.save( 
				new Client( 
						client.getUsername(), 
						client.getPassword()
				)
		); 
		return new ResponseEntity<Client>(client1, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
	ResponseEntity<Client> readClient(@PathVariable("clientId") Long clientId) {
		this.validateClient(clientId);
		Client client1= this.clientRepository1.findOne(clientId);
		return new ResponseEntity<Client>(client1, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<Collection<Client> > readAllClient() {
		Collection<Client> clients= this.clientRepository1.findAll();
		return new ResponseEntity<Collection<Client> >(clients, HttpStatus.OK);
	}
	
	//updating_user
	@RequestMapping(value = "/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<Client> updateClient(@PathVariable("clientId") Long clientId, @RequestBody Client client) {
		this.validateClient(clientId);
		Client client1=clientRepository1.findOne(clientId);
	 
        client1.setUsername(client.getUsername());
        client1.setPassword(client.getPassword());
	       
	    clientRepository1.saveAndFlush(client1);
        return new ResponseEntity<Client>(client1, HttpStatus.OK);
	}
	 
	//delete_single_user
	@RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE)
	public ResponseEntity<Client> deleteUser(@PathVariable("clientId") Long clientId) {
		this.validateClient(clientId);
		Client client = clientRepository1.findOne(clientId);
		DbContext.Users.Remove(user);
		clientRepository1.delete(client);
		return new ResponseEntity<Client>(HttpStatus.OK);
	}
	
	
	

	@Autowired
	ClientRestController(UserRepository userRepository, ClientRepository clientRepository) {
		this.userRepository1 = userRepository;
		this.clientRepository1= clientRepository;
	}

	private void validateClient(Long clientId) {
		this.clientRepository1.findById(clientId).orElseThrow(
				() -> new UserNotFoundException(clientId));
	}
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class ClientNotFoundException extends RuntimeException {

	public ClientNotFoundException(Long clientId) {
		super("could not find client '" + clientId + "'.");
	}
}