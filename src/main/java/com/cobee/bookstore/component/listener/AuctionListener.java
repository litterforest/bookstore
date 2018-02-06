package com.cobee.bookstore.component.listener;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cobee.bookstore.component.JedisBean;
import com.cobee.bookstore.vo.AuctionResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

/**
 * Application Lifecycle Listener implementation class AuctionListener
 *
 */
public class AuctionListener implements ServletContextListener {
	
	private static final Logger logger = LoggerFactory.getLogger(AuctionListener.class);
	
//	private static final BlockingQueue<AsyncContext> queue = new LinkedBlockingQueue<>();
	public static final Map<String, AuctionResult> auctionResultMap = new HashMap<String, AuctionResult>();
	
	private volatile Thread thread;
	
	private WebApplicationContext springContext;
	private JedisBean jedisBean;
	private Jedis jedis;
	
//	public static void addAsyncContext(AsyncContext asyncContext)
//	{
//		queue.add(asyncContext);
//	}

    /**
     * Default constructor. 
     */
    public AuctionListener() {
    }

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		springContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		jedisBean = springContext.getBean(JedisBean.class);
		jedis = jedisBean.getJedis();
		logger.info("秒杀抢购功能已起动...");
		thread = new Thread(new Runnable() {
			
//			private ObjectMapper mapper = new ObjectMapper();
			
			@Override
			public void run() {
				
				
		        while(true)
		        {
//		        	try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
		        	// 1, 查找所有图书抢购队列
		        	Set<String> keys = jedis.keys("auction:book:queue:*");
		        	if (!CollectionUtils.isEmpty(keys))
		        	{
		        		for (String auctionKey : keys)
		        		{
		        			String isbn = StringUtils.substringAfterLast(auctionKey, ":");
		        			// 弹出一个userno数据
		        			String userno = "";
		        			List<String> resultList = jedis.blpop(1000, auctionKey);
		        			if (!CollectionUtils.isEmpty(resultList))
		        			{
		        				userno = resultList.get(1);
		        			}
//		        			String userno = jedis.lpop(auctionKey);
		        			if (StringUtils.isNotBlank(userno))
		        			{
		        				// 判断是否能抢到图书，auction:book:inventory
		        				Double inventoryQuantityD = jedis.zscore("auction:book:inventory", isbn);
		        				Integer inventoryQuantityI = inventoryQuantityD == null ? 0 : inventoryQuantityD.intValue();
		        				AuctionResult result = new AuctionResult();
		        				result.setIsbn(isbn);
	        					result.setUserno(userno);
		        				if (inventoryQuantityI > 0)
		        				{
		        					result.setQuality(1);
		        					result.setLeftQuality(inventoryQuantityI - 1);
		        					// 更新库存
		        					jedis.zadd("auction:book:inventory", inventoryQuantityI - 1, isbn);
		        				}
		        				else
		        				{
		        					result.setQuality(0);
		        					result.setLeftQuality(0);
		        				}
		        				auctionResultMap.put(userno + ":" + isbn, result);
		        			}
		        		}
		        	}
		        }
				
				/*while(true)
				{
					AsyncContext asyncContext = null;
					while (queue.peek() != null)
					{
						try {
							asyncContext = queue.poll();
							ServletRequest request = asyncContext.getRequest();
							ServletResponse response = asyncContext.getResponse();
							response.setContentType(MediaType.APPLICATION_JSON_VALUE);
							PrintWriter writer = response.getWriter();
							Thread.sleep(1000);
							String userno = request.getParameter("userno");
							String threadName = Thread.currentThread().getName();
							// 模拟计算时间
							long startTime = System.currentTimeMillis();
							
							long endTime = System.currentTimeMillis();
							String duration = String.valueOf((endTime - startTime) / 1000);
							
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("status", "success");
							map.put("userno", userno);
							map.put("threadName", threadName);
							map.put("duration", duration);
							String jsonStr = mapper.writeValueAsString(map);
							
							writer.println(jsonStr);
							
							writer.close();
						} catch (Exception e) {
							logger.error("", e);
						} 
						finally
						{
							if (asyncContext != null)
							{
								try {
									asyncContext.complete();
								} catch (Exception e) {
									logger.error("", e);
								}
							}
						}
					}
				}*/
			}
		});
		thread.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		if (jedis != null)
		{
			jedis.keys("auction:book:queue:*");
			jedisBean.closeJedis(jedis);
		}
	}
	
}
