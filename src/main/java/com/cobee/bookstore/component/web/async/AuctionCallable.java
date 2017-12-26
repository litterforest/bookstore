package com.cobee.bookstore.component.web.async;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.cobee.bookstore.component.JedisBean;

import redis.clients.jedis.Jedis;

public class AuctionCallable implements Callable<Map<String, Object>> {
	
	private static final Logger logger = LoggerFactory.getLogger(AuctionCallable.class);
	private static Object lock = new Object();

	private JedisBean jedisBean;
	private String userno;
	private String isbn;
	

	public AuctionCallable() {
		super();
	}

	public AuctionCallable(JedisBean jedisBean, String userno, String isbn) {
		super();
		this.jedisBean = jedisBean;
		this.userno = userno;
		this.isbn = isbn;
	}

	@Override
	public Map<String, Object> call() throws Exception {
		
		// 模拟计算时间
		long startTime = System.currentTimeMillis();
		Thread.sleep(300);
		Jedis jedis = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try
		{
			jedis = jedisBean.getJedis();
			// 1,循环查找队列
			while(true)
			{
				if (!jedis.exists("auction:book:queue"))
				{
					break;
				}
				List<String> bookQueue = jedis.lrange("auction:book:queue", 0, 0);
				if (!CollectionUtils.isEmpty(bookQueue))
				{
					if (bookQueue.get(0).equals(userno))
					{
						synchronized (lock) {
							jedis.lpop("auction:book:queue");
							String key = "auction:book:" + isbn;
							String qualityStr = jedis.get(key);
							Integer quality = Integer.valueOf(qualityStr);
							if (quality > 0)
							{
								map.put("msg", "恭喜您抢到一本书，还剩" + (quality - 1) + "本");
								jedis.decr(key);
							}
							else
							{
								map.put("msg", "书已经被抢光了，请下次再来");
							}
							break;
						}
					}
				}
				else
				{
					break;
				}
			}
		}
		catch(Exception e)
		{
			logger.error("", e);
		}
		finally
		{
			if (jedis != null)
			{
				jedis.close();
			}
		}
		
		String threadName = Thread.currentThread().getName();
		long endTime = System.currentTimeMillis();
		String duration = String.valueOf((endTime - startTime) / 1000);
		map.put("status", "success");
		map.put("userno", userno);
		map.put("threadName", threadName);
		map.put("duration", duration);
        return map;
        
	}

}
