package com.cobee.bookstore.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobee.bookstore.entity.Tbook;
import com.cobee.bookstore.service.AbstractService;
import com.cobee.bookstore.service.ITbookService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

@Service
public class TbookServiceRedisImpl extends AbstractService implements ITbookService { 
	
	public Tbook get(String isbn) {
		if (StringUtils.isBlank(isbn))
		{
			return null;
		}
		Tbook tbook = null;
		Jedis jedis = jedisBean.getJedis();
		try
		{
			String bookJsonStr = jedis.hget("hash:books", isbn);
			if (StringUtils.isNotBlank(bookJsonStr))
			{
				ObjectMapper objectMapper = new ObjectMapper();
				tbook = objectMapper.readValue(bookJsonStr, Tbook.class);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			jedisBean.closeJedis(jedis);
		}
		
		
		return tbook;
	}

	public List<Tbook> listAll() {
		
		Jedis jedis = jedisBean.getJedis();
		List<Tbook> bookList = null;
		try
		{
			Set<String> booksSet = jedis.zrevrange("zset:books", 0, -1);
			if (!CollectionUtils.isEmpty(booksSet))
			{
				ObjectMapper objectMapper = new ObjectMapper();
				bookList = new ArrayList<Tbook>();
				for (String str : booksSet)
				{
					
					String bookJsonStr = jedis.hget("hash:books", str);
					if (StringUtils.isNotBlank(bookJsonStr))
					{
						try {
							Tbook tbook = objectMapper.readValue(bookJsonStr, Tbook.class);
							// 查找书本被浏览的次数
							Double pageViews = jedis.zscore("zset:bookpageview", tbook.getIsbn());
							if (pageViews != null)
							{
								tbook.setPageViews(pageViews.intValue());
							}
							else
							{
								tbook.setPageViews(0);
							}
							bookList.add(tbook);
						} catch (JsonParseException e) {
							e.printStackTrace();
						} catch (JsonMappingException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
			else
			{
				bookList = Collections.emptyList();
			}
		} 
		finally
		{
			jedisBean.closeJedis(jedis);
		}
		
		return bookList;
	}

	@Override
	public void addBookPageView(String isbn) {
		Jedis jedis = jedisBean.getJedis();
		try
		{ 
			// 如果key不存在，会自动创建
			jedis.zincrby("zset:bookpageview", 1, isbn);
		}
		finally
		{
			jedisBean.closeJedis(jedis);
		}
		
	}

	@Override
	public Integer getBookPageView(String isbn) {
		Jedis jedis = jedisBean.getJedis();
		try
		{ 
			// 如果key不存在，会自动创建
			jedis.zincrby("zset:bookpageview", 1, isbn);
		}
		finally
		{
			jedisBean.closeJedis(jedis);
		}
		return 0;
	}

}
