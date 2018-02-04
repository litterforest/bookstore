package com.cobee.bookstore.service;

import java.util.List;

import com.cobee.bookstore.entity.Tbook;

public interface ITbookService {
	
	Tbook get(String isbn);
	
	List<Tbook> listAll();
	
	void addBookPageView(String isbn);
	
	Integer getBookPageView(String isbn);
	
}
