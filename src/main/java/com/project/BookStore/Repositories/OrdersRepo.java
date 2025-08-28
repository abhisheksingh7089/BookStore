package com.project.BookStore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.BookStore.Models.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer>{
	
	
}
