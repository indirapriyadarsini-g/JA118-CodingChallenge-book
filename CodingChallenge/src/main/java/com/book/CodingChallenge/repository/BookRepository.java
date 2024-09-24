package com.book.CodingChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.CodingChallenge.entity.Book;

public interface BookRepository extends JpaRepository<Book,Integer>{

}
