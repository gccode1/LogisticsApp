package com.example;


import com.example.entity.Client;
import com.example.repository.ClientRepository;

import java.util.Arrays; 

import org.springframework.boot.CommandLineRunner; 
import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.context.annotation.Bean; 
 
@SpringBootApplication 
public class LogisticsAppApplication {
//	 @Bean 
//	 public CommandLineRunner init(ClientRepository clientRepository) { 
//	  return args -> { 
//	   clientRepository.deleteAll(); 
//	   Arrays.asList("pwebb,jlong".split(",")).forEach(userId -> { 
//	     Arrays.asList("Stéphane,Maldini;Dave,Syer;Juergen,Hoeller;Rob,Winch;Mark,Fisher;Arjen,Poutsma".split(";")) 
//	      .stream() 
//	      .map(name -> name.split(",")) 
//	      .map(firstName -> new Client( 
//	        "gaurav", 
//	        "Gaurav", 
//	        "9760")) 
//	      .forEach(clientRepository::save); 
//	    }); 
//	  }; 
//	 } 
	public static void main(String[] args) {
		SpringApplication.run(LogisticsAppApplication.class, args);
	}
}
