package com.cobee.bookstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cobee.bookstore.dao.ITbookDao;
import com.cobee.bookstore.entity.Tbook;
import com.cobee.bookstore.service.ITbookService;

@Service
@Transactional(readOnly = true)
public class TbookServiceImpl implements ITbookService {

	@Autowired
	private ITbookDao ITbookDao;
	
	public Tbook get(String isbn) {
		return ITbookDao.get(isbn);
	}

	public List<Tbook> listAll() {
		return ITbookDao.listAll();
	}

	@Override
	public void addBookPageView(String isbn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getBookPageView(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

}
