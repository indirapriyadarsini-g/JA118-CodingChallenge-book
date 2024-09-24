package com.book.CodingChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.CodingChallenge.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer>{

}
