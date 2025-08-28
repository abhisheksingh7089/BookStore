package com.project.BookStore.Controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.BookStore.Models.Books;
import com.project.BookStore.Services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Operations related to Books")
public class BookController {
	
	private BookService service;
	
	public BookController(BookService service) {
		this.service = service;
	}
	
	@GetMapping("/getBooks")
	@Operation(summary = "Get All Books" , description = "Fetching All Books")
	public ResponseEntity<List<Books>> getAllBooks(){
		
		List<Books> book = service.getAllBook();
		if(book.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(book);
		else
			return ResponseEntity.ok(book);
	}
	
	
	@PostMapping("/addBook")
	@Operation(summary = "Add Book" , description = "Add One Book At A Time")
	public ResponseEntity<Books> addBook(@RequestBody Books book) {
		Books book1 = service.addBook(book);
		if(book1 == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(book1);
		else
			return ResponseEntity.status(HttpStatus.OK).body(book1);
	}
	
	
	@PutMapping("/updateBook/{id}")
	@Operation(summary = "Update Book" , description = "Update Book By Passing Book ID And Book Details")
	public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Books book) {
		Books book1 = service.updateBook(id, book);
		if(book1 == null)
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error while updating...");
		else
			return ResponseEntity.status(HttpStatus.OK).body("Book Updated Succesfully!!!");
	}
	
	@DeleteMapping("/deleteBook/{id}")
	@Operation(summary = "Delete The Book", description = "Delete The Specific Book By Passing ID")
	public ResponseEntity<String> deleteBook(@PathVariable int id) {
		boolean check = service.deleteBook(id);
		if(check)
			return ResponseEntity.status(HttpStatus.OK).body("Deleted Succesfully!!!");
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong..");
	}
	
	@GetMapping("/getBookByTtitle/{title}")
	@Operation(summary = "Get The Book Details By Title" , description = "Get The Book Details By Passing Title Of The Book")
	public ResponseEntity<Object> getBook(@PathVariable String title) {   // OBJECT HERE IS BEACUSE IF BOOK IS NULL RETURN STRING ELSE RETURN OBJECT
		Books book =  service.getBook(title);
		if(book == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found...");
		else
			return ResponseEntity.status(HttpStatus.OK).body(book);
			
	}
	
	@GetMapping("/getBookByAuthor/{author}")
	@Operation(summary = "Get The Book Details By Author" , description = "Get The Book Details By Passing Author Name Of The Book")
	public ResponseEntity<Object> getBookByAuthor(@PathVariable String author) {
		Books book =  service.getBookbyAuthor(author);
		if(book == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found....");
		else
			return ResponseEntity.status(HttpStatus.OK).body(book);
	}

}
