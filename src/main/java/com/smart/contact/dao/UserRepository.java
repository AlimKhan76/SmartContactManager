package com.smart.contact.dao;

import org.springframework.data.repository.CrudRepository;
import com.smart.contact.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	/**
	 * This method will give the object of User Using Email this method will be used
	 * for the username given by principal
	 **/
	public User findByEmail(String email);
}
