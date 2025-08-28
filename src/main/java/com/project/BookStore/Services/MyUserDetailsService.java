package com.project.BookStore.Services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.BookStore.Models.User;
import com.project.BookStore.Models.UserPrincipal;
import com.project.BookStore.Repositories.UsersRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	private UsersRepo repo;
	
	public MyUserDetailsService (UsersRepo repo) {
		this.repo = repo;
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
		User user = repo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found with This Username: "+username);
		}
		return new UserPrincipal(user);
		}
		catch(Exception e) {
	        e.printStackTrace();
	        throw new UsernameNotFoundException("Error fetching user: " + username, e);
	    }
	}

}
