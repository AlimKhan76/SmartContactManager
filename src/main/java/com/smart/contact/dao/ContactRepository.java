package com.smart.contact.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.smart.contact.model.Contact;
import com.smart.contact.model.User;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

	// This method will give the list of contacts by using User object as an
	// argument
	@Query("from Contact as c where c.user.uId=:userId")
	public List<Contact> findContactsByUser(@Param("userId") int uId);

	/**
	 * Pageable will have information regarding how many contacts per page and the
	 * current page
	**/
	public Page<Contact> findByUser(User user, Pageable pageable);
	
	
//	public List<Contact> findBycNameContainingAndUser(String cname,User user);

}
