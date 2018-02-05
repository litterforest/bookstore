package com.cobee.bookstore.service;

import java.util.List;

import com.cobee.bookstore.entity.Comment;

public interface ICommentService {
	
	void add(Comment comment);
	
	List<Comment> listAll(String isbn);
	
	List<Comment> list(String isbn, Integer start, Integer end);
	
	Integer dianzhang(String commentID);
	
}
