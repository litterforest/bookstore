package com.cobee.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.cobee.bookstore.component.JedisBean;

public abstract class AbstractController {
	
	@Autowired
	protected JedisBean jedisBean;
	
}
