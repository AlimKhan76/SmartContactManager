package com.smart.contact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//This Controller is used for redirecting to Home and Login Page and also Logging in 
@Controller
public class HomeController {

	// This method is used for Redirecting to Home page
	@RequestMapping("/")
	public String Home(Model model) {
		model.addAttribute("title", "Home Page");
		System.out.println("running Home");
		return "index";
	}

	// This method is used for Redirecting to Login page
	@RequestMapping("/logredirect")
	public String logRedirect(Model m) {
		m.addAttribute("title", "Login Page");
		return "login";
	}

}
