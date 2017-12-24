package com.cobee.bookstore.component.listener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        // TODO Auto-generated constructor stub
    }

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true)
				{
					AsyncContext asyncContext = null;
					if (queue.peek() != null)
					{
						try {
							asyncContext = queue.poll();
							
							
							
						} catch (Exception e) {
							logger.error("", e);
						} 
						finally
						{
							if (asyncContext != null)
							{
								asyncContext.complete();
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
