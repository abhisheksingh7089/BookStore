package com.project.BookStore.Services;


import org.springframework.stereotype.Service;
import com.project.BookStore.Models.Books;
import com.project.BookStore.Models.Orders;
import com.project.BookStore.Models.User;
import com.project.BookStore.Repositories.BookRepo;
import com.project.BookStore.Repositories.OrdersRepo;
import com.project.BookStore.Repositories.UsersRepo;

@Service
public class OdersService {

	private OrdersRepo repo;
	private BookRepo bookRepo;
	private UsersRepo userRepo;

	public OdersService(OrdersRepo repo, BookRepo bookRepo, UsersRepo userRepo) {
		this.repo = repo;
		this.bookRepo = bookRepo;
		this.userRepo = userRepo;
	}

	public boolean placeOrder(int userId, String bookName, int quantity) {

		Orders order = new Orders();
		User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
		try {
				Books book = bookRepo.findByTitle(bookName);
				if (book.getStock() <= 0) { // THIS CHECK IF BOOKS ARE IN STOCKS OR NOT
					return false;
				}
				else {
					book.setId(book.getId());
					System.out.println(book);
					order.setBook(book);
					order.setUser(user);
					order.setQunatity(quantity);
					book.setStock(book.getStock() - 1); // THIS LINE REDUCE THE STOCK BY 1 AS PER ORDER
					bookRepo.save(book);
					repo.save(order);
					return true;
				}
			} 
		catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}

	}

}
