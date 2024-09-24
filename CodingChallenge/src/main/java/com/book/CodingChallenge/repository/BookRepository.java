package com.book.CodingChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.book.CodingChallenge.entity.Book;

import jakarta.transaction.Transactional;

public interface BookRepository extends JpaRepository<Book,Integer>{

	@Query("select b from Book b where b.isbn = ?1")
	Book getBookByIsbn(String isbn);

	@Transactional
	@Modifying
	@Query("update Book b set b.title = ?2 where b.id = ?1")
	int updateBook(int bId,String title);

	
	@Transactional
	@Modifying
	@Query("delete Book b where b.isbn = ?1")
	int deleteByIsbn(String isbn);




}
