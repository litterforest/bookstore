package com.cobee.bookstore.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobee.bookstore.entity.Comment;
import com.cobee.bookstore.service.AbstractService;
import com.cobee.bookstore.service.ICommentService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

@Service("ICommentServiceImpl")
public class ICommentServiceImpl extends AbstractService implements ICommentService {

	@Override
	public void add(Comment comment) {
		if (comment == null)
			return;
		Jedis jedis = jedisBean.getJedis();
		try {

			ObjectMapper objectMapper = new ObjectMapper();
			String jsonStr = objectMapper.writeValueAsString(comment);
			jedis.lpush("list:comment:" + comment.getIsbn(), jsonStr);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			jedisBean.closeJedis(jedis);
		}
	}

	@Override
	public List<Comment> listAll(String isbn) {
		if (StringUtils.isBlank(isbn))
		{
			return Collections.emptyList();
		}
		
		return list(isbn, 0, -1);
	}

	@Override
	public List<Comment> list(String isbn, Integer start, Integer end) {
		if (StringUtils.isBlank(isbn))
		{
			return Collections.emptyList();
		}
		if (start < 0)
		{
			return Collections.emptyList();
		}
		Jedis jedis = jedisBean.getJedis();
		List<Comment> commentList = null;
		try {

			List<String> list = jedis.lrange("list:comment:" + isbn, start, end);
			if (!CollectionUtils.isEmpty(list))
			{
				commentList = new ArrayList<Comment>();
				ObjectMapper objectMapper = new ObjectMapper();
				for(String jsonStr : list)
				{
					try {
						commentList.add(objectMapper.readValue(jsonStr, Comment.class));
					} catch (JsonParseException e) {
						e.printStackTrace();
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} 
				}
			}
			else
			{
				commentList = Collections.emptyList();
			}
			
		}  finally {
			jedisBean.closeJedis(jedis);
		}
		return commentList;
	}

}
