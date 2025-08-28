

package com.project.BookStore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.BookStore.Models.Books;
import com.project.BookStore.Repositories.BookRepo;

@Service
public class BookService {
	
	private BookRepo repo;
	
	public BookService(BookRepo repo) {
		this.repo = repo;
		
	}

	public Books addBook(Books book) {
		try {
			return repo.save(book);
		}
		catch(Exception e) {
			throw new RuntimeException("Error saving book: "+ e.getMessage(), e);
		}
	}
	
	public List<Books> getAllBook(){
		try {
		return repo.findAll();
		}
		catch(Exception e) {
			throw new RuntimeException("Error While Fetching Data: "+ e.getMessage(), e);
		}
	}
	
	public Books updateBook(int id ,Books book) {
		Optional<Books> checkBookExist = repo.findById(id);
		try {
			if(checkBookExist.isPresent())
				return repo.save(book);
		}
		catch(Exception e) {
			throw new RuntimeException("Error updating book: "+ e.getMessage(), e);
		}
		return book;
		
	}
	
	public boolean deleteBook(int id) {
		try {
			if(repo.existsById(id)) {
				repo.deleteById(id);
				return true;
			}
			else
				return false;
		}
		catch(Exception e) {
			throw new RuntimeException("Data Not Exist: "+ e.getMessage(),e);
			
		}
	}
	
	public Books getBook(String title) {
		try {
			return repo.findByTitle(title);
		}
		catch(Exception e) {
			throw new RuntimeException("Please Enter Correct Data or Error While Fetching Data: "+ e.getMessage(),e);
		}
	}
	
	public Books getBookbyAuthor(String author) {
		try {
			return repo.findByAuthor(author);
		}
		catch(Exception e) {
			throw new RuntimeException("Error While Fetching Data: "+e.getMessage(),e);
		}
	}
}
