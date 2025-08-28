package com.project.BookStore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.BookStore.Models.User;


@Repository
public interface UsersRepo extends JpaRepository<User, Integer>{
	
	public User findByUsername(String username);
	
	public boolean existsByUsername(String username);

}
