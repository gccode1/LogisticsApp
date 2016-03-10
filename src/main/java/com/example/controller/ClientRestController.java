package com.example.controller;

import com.example.entity.Client;
import com.example.entity.User;
import com.example.repository.ClientRepository;
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
@RequestMapping("/client")
public class ClientRestController { 
	@Autowired
	private ClientRepository clientRepository; 
	private UserRepository userRepository; 
	
	
	@RequestMapping(method = RequestMethod.GET) 
	public ResponseEntity< List<Client> > getClient() { 
		List<Client> clients= this.clientRepository.findBy(); 
		return new ResponseEntity< List<Client> >(clients,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{clientId}", method = RequestMethod.GET) 
	public ResponseEntity<Client> getClient(@PathVariable Long clientId) { 
		Client client1=this.clientRepository.findById(clientId); 
		if(client1!=null)
			return new ResponseEntity<Client>(client1,HttpStatus.OK);
		else
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/{clientId}", method = RequestMethod.PATCH) 
	public ResponseEntity<Client> updateClient(@PathVariable Long clientId,@RequestBody Client client ) {
		Client client1=this.clientRepository.findById(clientId);
		if(client1==null)
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		if(client.getFirstName()!=null)
			client1.setFirstName(client.getFirstName());
		if(client.getPhone()!=null)
			client1.setPhone(client.getPhone());
		client1=this.clientRepository.save(client1); 
		return new ResponseEntity<Client>(client1,HttpStatus.OK);
	} 
 
	
	@RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE) 
	public ResponseEntity<Client> deleteClient(@PathVariable Long clientId){
		Client client1=this.clientRepository.findById(clientId);
		if(client1==null)
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		else{
			this.clientRepository.delete(client1);  
			return new ResponseEntity<Client>(HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST) 
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		Client client1=this.clientRepository.save(
			new Client(
				client.getFirstName(),
				client.getPhone()
			)
		);
		if(client1!=null)
			return new ResponseEntity<Client>(client1,HttpStatus.CREATED);
		else
			return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
	}
}
