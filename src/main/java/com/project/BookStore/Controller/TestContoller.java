package com.project.BookStore.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class TestContoller {
	
	
	@GetMapping("/")
	public String greet(HttpServletRequest request) {
		return "Welcome to Book Store" + request.getSession().getId() ;
	}

}
