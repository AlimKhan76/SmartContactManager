package com.smart.contact.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * This class store the user that registers and the email and password of users
 * are used as credentials for spring security login
 **/
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uId;
	private String uname;
	private String password;
	@Column(unique = true)
	private String email;
	private String number;
	private String role;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Contact> contact = new ArrayList<>();

	public User() {
		super();
	}

	public User(int uId, String uname, String password, String email, String number, String role,
			List<Contact> contact) {
		super();
		this.uId = uId;
		this.uname = uname;
		this.password = password;
		this.email = email;
		this.number = number;
		this.role = role;
		this.contact = contact;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Contact> getContact() {
		return contact;
	}

	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "User [uId=" + uId + ", uname=" + uname + ", password=" + password + ", email=" + email + ", number="
				+ number + ", role=" + role + ", contact=" + contact + "]";
	}

}
