package com.smart.contact.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.contact.dao.ContactRepository;
import com.smart.contact.dao.UserRepository;
import com.smart.contact.helper.Message;
import com.smart.contact.model.Contact;
import com.smart.contact.model.User;
import jakarta.servlet.http.HttpSession;

/* 
 * This Controller is used for Operation after the user is successfully logged in
 * Operations like Adding, Deleting, Updating, Viewing Contacts
*/
@Controller
@RequestMapping("/user")
public class AfterLoginController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ContactRepository contactRepository;

	// Controller for receiving data after logging in and redirecting to user home
	// page
	@GetMapping("/successlog")
	public String getCurrentUser(Model m) {
		m.addAttribute("title", "Home");
		return "normal/home";
	}

	// Controller for Redirecting to add Contact form
	@GetMapping("/add_contact_redirect")
	public String Redirect(Model m) {
		m.addAttribute("title", "Add Contact Form");
		return "normal/add-contact-form";
	}

	@PostMapping("/add_contact")
	public String AddingContact(@ModelAttribute Contact contact, Model m, Principal principal) {

		/*
		 * Getting the current logged in user using principal and then adding contact
		 * details to it and saving it
		 */
		System.out.println("Adding Contatc");
		User user = userRepository.findByEmail(principal.getName());
		contact.setUser(user);

		user.getContact().add(contact);
		userRepository.save(user);

		return "redirect:/user/viewpage/0";

	}

	// REdirecting to View-all-contact Page and the view of All the Contacts
	@GetMapping("/viewpage/{page}")
	public String ViewContactRedirect(@PathVariable("page") int page, Model m, Principal principal) {
		System.out.println("viewing page");
		m.addAttribute("title", "View Contacts");
		String email = principal.getName();
		User user = userRepository.findByEmail(email);
		// page contains the current page and 5 is the number of contacts per page
		Pageable pageable = PageRequest.of(page, 3);

		Page<Contact> list = contactRepository.findByUser(user, pageable);
		System.out.println(list);
		m.addAttribute("contact", list);
		m.addAttribute("currentPage", page);
		m.addAttribute("totalPages", list.getTotalPages());

		return "normal/view-all-contact";
	}

	
	/*
	 * Deleting a Specific Contact from the Database
	 */
	@GetMapping("/delete/{cId}")
	public String deleteContact(@PathVariable("cId") int cId, Model m, HttpSession session) {

		Contact contact = contactRepository.findById(cId).get();
		contact.setUser(null);
		contactRepository.save(contact);

		this.contactRepository.delete(contact);
		System.out.println("deleted");

		session.setAttribute("message",
				new Message("The Contact of " + contact.getcName() + " is deleted successfuly", " alert-success"));

		return "redirect:/user/viewpage/0";
	}

	/*
	 * To view Details of the Contact in a single Page
	 */
	@GetMapping("/view/{cId}")
	public String viewOneContact(@PathVariable("cId") int cId, Model m) {
		Contact contact = contactRepository.findById(cId).get();
		m.addAttribute("contact", contact);

		return "normal/view-contact-details";
	}

	// This controller redirects to update-form page where we can update the contact
	@GetMapping("/update/{cId}")
	public String updateRedirect(@PathVariable("cId") int cId, Model m) {

		Contact contact = contactRepository.findById(cId).get();

		m.addAttribute("contact", contact);

		return "normal/update-form";
	}

	/*
	 * This Controller updates the details of the contact
	 */
	@PostMapping("/update_contact")
	public String updateContact(@ModelAttribute Contact contact, Principal principal, HttpSession session) {
		System.out.println(contact);

		String email = principal.getName();
		User user = userRepository.findByEmail(email);
		contact.setUser(user);
		contactRepository.save(contact);
		System.out.println("Updated");
		session.setAttribute("messaage",
				new Message("The contact of " + contact.getcName() + " is updated successfully", "alert-success"));
		return "redirect:/user/viewpage/0";
	}
}
