package com.book.CodingChallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.book.CodingChallenge.entity.Book;
import com.book.CodingChallenge.enums.Author;
import com.book.CodingChallenge.exception.InvalidCredentialsException;
import com.book.CodingChallenge.repository.BookRepository;
import com.book.CodingChallenge.repository.UserInfoRepository;
import com.book.CodingChallenge.service.BookService;


@SpringBootTest
class CodingChallengeApplicationTests {

	@Mock
	UserInfoRepository userInfoRepository;

	@Mock
	BookRepository bookRepository;
	
	
	@InjectMocks
	BookService bookService;
	
	
	@Test
	public void testAddBook() throws InvalidCredentialsException {
		Book book = new Book();
		book.setAuthor(Author.HARISH);
		book.setId(303);
		book.setIsbn("679076");
		book.setPublicationYear(2013);
		book.setTitle("The art of playing");
		
		when(bookService.addBook(book)).thenReturn(book);
		assertNotNull(book);
	}
	
	@Test
	public void testGetBookByIsbn() throws InvalidCredentialsException {
		Book book = new Book();
		book.setAuthor(Author.HARISH);
		book.setId(303);
		book.setIsbn("4567");
		book.setPublicationYear(2013);
		book.setTitle("The art of playing");

		when(bookService.addBook(book)).thenReturn(book);
		when(bookService.getBookByIsbn("4567")).thenReturn(book);
		assertNotNull(book);
	}
	
	

}
