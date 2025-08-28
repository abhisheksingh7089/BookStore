package com.project.BookStore.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BookStore.Models.User;
import com.project.BookStore.Services.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name="Users", description = "Operations Related to Users")
public class UsersController {
	
	private UsersService service;
	
	public UsersController(UsersService service) {
		this.service = service;
	}
	
	@PostMapping("/register")
	@Operation(summary="Register User", description = "User Can Register/Signup By Passing Username and Password")
	public ResponseEntity<String> signupUser(@RequestBody User user) {
		boolean check = service.signupUser(user);
		if(check)
			return ResponseEntity.status(HttpStatus.OK).body("Registered Sucessfully..");
		else
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Something went wrong while registering user");
		
	}

}
