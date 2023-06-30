package com.smart.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.contact.dao.UserRepository;
import com.smart.contact.helper.Message;
import com.smart.contact.model.User;
import jakarta.servlet.http.HttpSession;

// Controller Used for User Operations
@Controller
public class UserRegisterationController {
	
	// Used for Password encryption
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	// Controller for redirecting to user Registration page
	@RequestMapping("/reg")
	public String reDirect(Model m) {
		m.addAttribute("title", "Registration Page");
		return "user-reg";
	}

	// Controller for Registering the User
	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model m, HttpSession session) {

		try {
			// Encoding the password to store in database
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			// Setting the Role of the User
			user.setRole("ROLE_USER");
			// SAving the Use
			userRepository.save(user);
			// Sending the session with success message to login page
			session.setAttribute("message", new Message(user.getUname() + " registered successfully", "alert-success"));
			return "redirect:/logredirect";

		}
		// Sending email already registered error message through session
		catch (org.springframework.dao.DataIntegrityViolationException e) {
			session.setAttribute("message", new Message("Email Address is already Registered  ", "alert-danger"));
			return "redirect:/reg";
		}

		// handling any other type of Error
		catch (Exception e) {
			session.setAttribute("message", new Message("Something went Wrong " + e.getMessage(), "alert-danger"));
			return "redirect:/reg";
		}

	}

}
