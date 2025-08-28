package com.project.BookStore.Services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.BookStore.Models.User;
import com.project.BookStore.Repositories.UsersRepo;

@Service
public class UsersService {
	
	UsersRepo repo;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);  // THIS LINE CREATE OBJECT OF BCRYPT PASSWORD ENCODER WITH STRENGTH 12 WHICH IS 2^12
	
	public UsersService(UsersRepo repo) {
		this.repo = repo;
	}
	
	public boolean signupUser(User user) {
		try {
			if(!repo.existsByUsername(user.getUsername())) {
				user.setRoles("USER");
				user.setPassword(encoder.encode(user.getPassword()));  // THIS LINE ENCRYPT PASSWORD BEFORE SAVING IT TO DB. THIS USE SHA256 ENCRYPTION
				repo.save(user);
				return true;
			}
			else
				return false;
		}
		catch(Exception e){
			throw new RuntimeException("User Not Regstered: "+ e.getMessage(),e);
		}
	}

}
