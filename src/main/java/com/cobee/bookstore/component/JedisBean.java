package com.cobee.bookstore.component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisBean {

	private JedisPool jedisPool;

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public Jedis getJedis()
	{
		return jedisPool.getResource();
	}

	public void closeJedis(Jedis jedis)
	{
		if (jedis != null)
		{
			jedis.close();
		}
	}

}
