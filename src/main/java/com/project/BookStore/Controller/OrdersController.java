package com.project.BookStore.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.BookStore.Services.OdersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/order")
@Tag(name = "Orders", description = "Operations related to Orders")
public class OrdersController {
	
	private OdersService service;
	
	public OrdersController (OdersService service) {
		this.service = service;
	}
	
	@PostMapping("/orderBook/{userId}/{title}/{quantity}")
	@Operation(summary="Order A Book", description = "User Can Order Book By Passing Its User ID, Book Title And The Book Quantity")
	public ResponseEntity<String> orderbook(@PathVariable int userId, @PathVariable String title, @PathVariable int quantity) {
			boolean check = service.placeOrder(userId, title, quantity);
			if(check)
				return ResponseEntity.status(HttpStatus.OK).body("Sucessfully Orderd..");
			else
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Unable to place order");
		
		
	}

}
