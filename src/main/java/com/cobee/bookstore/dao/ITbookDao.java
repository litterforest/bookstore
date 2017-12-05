package com.cobee.bookstore.dao;

import java.util.List;

import com.cobee.bookstore.entity.Tbook;

public interface ITbookDao {
	
	Tbook get(String isbn);
	
	List<Tbook> listAll();
	
}
