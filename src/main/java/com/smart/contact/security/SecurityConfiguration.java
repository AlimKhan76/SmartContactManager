package com.smart.contact.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Important class for the COnfiguration of spring security
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	/**
	 * Sets the username and password for login It is invokes by the Constructor in
	 * UserDetailsServiceImpl
	 **/
	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	/**
	 * It is a method provided by spring security for Encoding of password and
	 * protecting it
	 **/
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());

		return daoAuthenticationProvider;
	}

	// COnfiguration methods

	/**
	 * This beans tells which urls to autheicate and which to not and also specifies
	 * the login page and success url
	 **/
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeHttpRequests((requests) -> requests
				// Url starting from this will be authenticated
				.requestMatchers("/user/**").authenticated()
				// url staring from this will be permitted for all to see
				.requestMatchers("/**").permitAll()

		)

				.logout((logout) -> logout.logoutSuccessUrl("/logredirect"))
				// Specifying the url of the custom login page
				.formLogin().loginPage("/logredirect").loginProcessingUrl("/dologin")
				.defaultSuccessUrl("/user/successlog");

		return http.build();
	}

}
