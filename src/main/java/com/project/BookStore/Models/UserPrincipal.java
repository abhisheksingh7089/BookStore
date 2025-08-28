package com.project.BookStore.Models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{


	private static final long serialVersionUID = 1L;
	private User user;
	
	public UserPrincipal(User user) {
		this.user = user;
		
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String userRole = user.getRoles();
		if(userRole == null || userRole.trim().isEmpty()) 
			userRole = "USER";
		
//		String prefix = userRole.startsWith("ROLE_") ? userRole : "ROLE_" + userRole.toUpperCase();
		return Collections.singleton(new SimpleGrantedAuthority(userRole)) ;
		
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

}
