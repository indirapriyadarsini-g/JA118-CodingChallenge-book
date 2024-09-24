package com.book.CodingChallenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.CodingChallenge.entity.Book;
import com.book.CodingChallenge.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public Optional<Book> addBook(Book b) {
		// TODO Auto-generated method stub
		return null;
	}

}
