package com.smart.contact.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

//This Entity stores all the details of the Contacts
@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	private String cName;
	private String cnumber;
	private String description;
	// Mapping to the user for accessibility
	@ManyToOne
	@JsonIgnore
	private User user;

	public Contact() {
		super();
	}

	public Contact(int cId, String cName, String cnumber, String description, User user) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cnumber = cnumber;
		this.description = description;
		this.user = user;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getCnumber() {
		return cnumber;
	}

	public void setCnumber(String cnumber) {
		this.cnumber = cnumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}