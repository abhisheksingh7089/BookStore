package com.project.BookStore.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.BookStore.Models.Books;


@Repository
public interface BookRepo extends JpaRepository<Books, Integer>{
	
	public boolean existsByTitle(String title);
	
	@Query("Select b.id from Books b where title = :title")
	public int findBookIdByTitle(@Param("title") String title);
	
	public Books findByTitle(String title);
	
	public Books findByAuthor(String author);
	

}
