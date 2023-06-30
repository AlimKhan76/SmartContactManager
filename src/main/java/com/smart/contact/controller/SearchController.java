/*package com.smart.contact.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smart.contact.dao.ContactRepository;
import com.smart.contact.dao.UserRepository;
import com.smart.contact.model.Contact;
import com.smart.contact.model.User;

@RestController
public class SearchController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	@GetMapping("/search/{query}")
	public ResponseEntity<?> search(@PathVariable("query")String query, Principal principal){
		System.out.println(query);
		User user=this.userRepository.findByEmail(principal.getName());
		
		List<Contact> contacts=this.contactRepository.findBycNameContainingAndUser(query, user);
		
		return ResponseEntity.ok(contacts);
				
	}

}*/
