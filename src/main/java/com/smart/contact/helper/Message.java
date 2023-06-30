package com.smart.contact.helper;

/**
 * This class is used to send error or success message through session in the
 * controller to the thymeleaf templates
 **/
public class Message {

	// This will contain the message whether it be a error or success
	private String content;

	// This will contain the type of Bootstrap class that will be appended in the
	// templates
	private String type;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Message(String content, String type) {
		super();
		this.content = content;
		this.type = type;
	}

	public Message() {
		super();
	}

}
