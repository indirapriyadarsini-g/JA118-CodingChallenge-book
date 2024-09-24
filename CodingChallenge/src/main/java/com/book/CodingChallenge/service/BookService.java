package com.book.CodingChallenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.CodingChallenge.entity.Book;
import com.book.CodingChallenge.exception.InvalidCredentialsException;
import com.book.CodingChallenge.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Book addBook(Book b) throws InvalidCredentialsException {		
		if(b.getTitle()==null || b.getAuthor()==null || b.getIsbn()==null) throw new InvalidCredentialsException("Values cannot be null");
		return bookRepository.save(b);
	}

	public Book getBookByIsbn(int isbn) {
		// TODO Auto-generated method stub
		return bookRepository.getBookByIsbn(isbn);
	}

}
