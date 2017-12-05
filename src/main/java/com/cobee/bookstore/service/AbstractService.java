package com.cobee.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cobee.bookstore.component.JedisBean;

public abstract class AbstractService {

	@Autowired
	protected JedisBean jedisBean;

}
