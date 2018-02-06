package com.cobee.bookstore.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cobee.bookstore.vo.AuctionResult;

import redis.clients.jedis.Jedis;

//@Service
public class AuctionListHandler {
	
	public static final Map<String, AuctionResult> auctionResultMap = new HashMap<String, AuctionResult>();
	
	@Autowired
	protected JedisBean jedisBean;
	
	public AuctionListHandler() {
		super();
	}

	@PostConstruct
	public void start()
	{
//		System.out.println("======================================");
		new Thread() {
			
			public void run() {
				Jedis jedis = jedisBean.getJedis();
		        while(true)
		        {
		        	try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		        	// 1, 查找所有图书抢购队列
		        	Set<String> keys = jedis.keys("auction:book:queue:*");
		        	if (!CollectionUtils.isEmpty(keys))
		        	{
		        		for (String auctionKey : keys)
		        		{
		        			String isbn = StringUtils.substringAfterLast(auctionKey, ":");
		        			// 弹出一个userno数据
		        			jedis.lpop(auctionKey);
		        			String userno = jedis.lpop(auctionKey);
		        			if (StringUtils.isNotBlank(userno))
		        			{
		        				// 判断是否能抢到图书，auction:book:inventory
		        				Double inventoryQuantityD = jedis.zscore("auction:book:inventory", isbn);
		        				Integer inventoryQuantityI = inventoryQuantityD == null ? 0 : inventoryQuantityD.intValue();
		        				if (inventoryQuantityI > 0)
		        				{
		        					AuctionResult result = new AuctionResult();
		        					result.setIsbn(isbn);
		        					result.setUserno(userno);
		        					result.setQuality(1);
		        					result.setLeftQuality(inventoryQuantityI - 1);
		        					auctionResultMap.put(userno + ":" + isbn, result);
		        					// 更新库存
		        					jedis.zadd("auction:book:inventory", inventoryQuantityI - 1, isbn);
		        				}
		        			}
		        		}
		        	}
		        }
		        
		    }
			
		}.start();
	}
}
