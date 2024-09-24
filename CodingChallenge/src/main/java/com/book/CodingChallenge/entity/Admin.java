package com.book.CodingChallenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String adminName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", adminName=" + adminName + "]";
	}

	public Admin(int id, String adminName) {
		super();
		this.id = id;
		this.adminName = adminName;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
