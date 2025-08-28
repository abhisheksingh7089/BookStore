package com.project.BookStore.Models;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@Entity

public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	@ManyToOne
	@JoinColumn(name="book_id", nullable = false)
	private Books book;
	private LocalDateTime orderDate;
	private int qunatity;
	
	public Orders() {
		this.orderDate = LocalDateTime.now();
	}
	
}
