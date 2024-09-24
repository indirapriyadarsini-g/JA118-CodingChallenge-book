package com.book.CodingChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.book.CodingChallenge.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{

	@Query("select b from Book b where b.isbn = ?1")
	Book getBookByIsbn(int isbn);


}
