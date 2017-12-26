package com.cobee.bookstore.component.listener;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Application Lifecycle Listener implementation class AuctionListener
 *
 */
public class AuctionListener implements ServletContextListener {
	
	private static final Logger logger = LoggerFactory.getLogger(AuctionListener.class);
	
	private static final BlockingQueue<AsyncContext> queue = new LinkedBlockingQueue<>();
	
	private volatile Thread thread;
	
	public static void addAsyncContext(AsyncContext asyncContext)
	{
		queue.add(asyncContext);
	}

    /**
     * Default constructor. 
     */
    public AuctionListener() {
    }

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("秒杀抢购功能已起动...");
		thread = new Thread(new Runnable() {
			
			private ObjectMapper mapper = new ObjectMapper();
			
			@Override
			public void run() {
				while(true)
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
				}
			}
		});
		thread.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	
}
