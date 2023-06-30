package com.smart.contact.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpSession;

@Component
/**
 * This class is used to remove the attributes from the session after showing it
 * once so the templates and return to their normal state. This class is made
 * because #session.removeAttribute() method is been deprecated in thymeleaf
 **/
public class SessionHelper {

	public void removeMessageFromSession() {
		try {
			System.out.println("removing sesssion");
			HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
					.getSession();
			session.removeAttribute("message");
		} catch (RuntimeException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
